package com.example.bankApp.controller;

import com.example.bankApp.dto.AccountForClientDto;
import com.example.bankApp.dto.AccountForManagerDto;
import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.Client;
import com.example.bankApp.entity.enums.CurrencyCode;
import com.example.bankApp.service.AccountService;
import com.example.bankApp.util.ErrorMessage;
import com.example.bankApp.validation.Balance;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    //todo OwnerId
    @GetMapping("/user/accounts/{clientId}")
    public ResponseEntity<List<AccountForClientDto>> getAllByClientId(@PathVariable UUID clientId) {
        List<AccountForClientDto> accountDtoList = accountService.findAllByClientId(clientId);
        return ResponseEntity.ok(accountDtoList);
    }

    @GetMapping("/user/account/{clientId}")
    public ResponseEntity<AccountForClientDto> getByIdAndClientID(@RequestBody UUID accountId, @PathVariable UUID clientId) {
        AccountForClientDto accountDto = accountService.findByIdAndClientId(accountId, clientId).getBody();
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/manager/id")
    public ResponseEntity<AccountForManagerDto> findById(@RequestBody UUID id) {
        AccountForManagerDto accountDto = accountService.findById(id).getBody();
        return ResponseEntity.ok(accountDto);
    }

    // todo
    @GetMapping("/manager/all/by-currency")
    public ResponseEntity<List<AccountForManagerDto>> findAlLByCurrencyCode(@RequestParam String currencyCode) {
        List<AccountForManagerDto> accountDto = accountService.findAlLByCurrencyCode(currencyCode);
        return ResponseEntity.ok(accountDto);
    }

    //
    @GetMapping("/user/all/short")
    public ResponseEntity<List<AccountForManagerDto>> findAllShort() {
        return ResponseEntity.ok(accountService.findAllShort());
    }

    @GetMapping("/manager/all/full")
    public ResponseEntity<List<Account>> findAll() {
        return accountService.findAll();
    }

    @PostMapping("/manager/all/sorted-by-balance")
    public ResponseEntity<List<AccountForManagerDto>> findAllOrderedByBalanceAsc() {

        return ResponseEntity.ok(accountService.findAllOrderedByBalanceAsc());
    }

    @PostMapping("/manager/balance-list-by-client")
    public ResponseEntity<List<Object[]>> getTotalBalanceByClientId() {
        return accountService.getTotalBalanceByClientId();
    }

    @PostMapping("/manager/total-balance")
    public ResponseEntity<Map<String, Object>> getBalanceSummary() {
        return accountService.getBalanceSummary();
    }

    @PostMapping("/manager/balance-by-currency")
    public ResponseEntity<List<Object[]>> getTotalBalanceByCurrencyData() {
        return accountService.getTotalBalanceByCurrencyData();
    }

    @PostMapping("/manager/balance-client-id")
    public ResponseEntity<Map<String, Object>> getBalanceSummaryByClientId(@RequestParam UUID clientId) {
        return accountService.getBalanceSummaryByClientId(clientId);
    }

    @PutMapping("manager/add")
    public ResponseEntity<Account> add(@Valid @RequestBody Account account) {
        return new ResponseEntity<>(accountService.add(account), HttpStatus.CREATED);
    }

    @PutMapping("manager/update")
    public ResponseEntity<Account> updateAccountByParam(@RequestBody UUID id,
                                                        @RequestParam(required = false) Integer type,
                                                        @Balance @RequestParam(required = false) BigDecimal balance) {

        accountService.updateAccountByParam(id, type, balance);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/manager/update/active")
    public ResponseEntity<Account> updateAccountByIsActive(@RequestBody UUID id,
                                                           @RequestParam(required = false) boolean isActive) {

        accountService.updateAccountByIsActive(id, isActive);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("manager/delete")
    public ResponseEntity<String> delete(@RequestBody UUID id) {
        accountService.deleteById(id);
        return new ResponseEntity<>(ErrorMessage.DELETE_BY_ID + id, HttpStatus.OK);
    }

}
