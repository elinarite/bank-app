package com.example.bankApp.controller;

import com.example.bankApp.dto.AccountForClientDto;
import com.example.bankApp.dto.AccountForManagerDto;
import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.Client;
import com.example.bankApp.entity.enums.CurrencyCode;
import com.example.bankApp.service.AccountService;
import com.example.bankApp.validation.Balance;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

//    //todo with auth
    @GetMapping("/{ownerId}/accounts")
    public ResponseEntity<List<AccountForClientDto>> getAllByClientId(@PathVariable UUID ownerId) {
        List<AccountForClientDto> accountDtoList = accountService.findAllByClientId(ownerId);
        return ResponseEntity.ok(accountDtoList);
    }

    //todo with auth
    @GetMapping("/{ownerId}/account")
    public ResponseEntity<AccountForClientDto> getByIdAndClient(@RequestBody UUID accountId, @PathVariable UUID ownerId) {
        AccountForClientDto accountDto = accountService.findByIdAndClientId(accountId, ownerId).getBody();
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/id")
    public ResponseEntity<AccountForManagerDto> findById(@RequestBody UUID id) {
        AccountForManagerDto accountDto = accountService.findById(id).getBody();
        return ResponseEntity.ok(accountDto);
    }

   // todo

    @GetMapping("/all/by-currency")
    public ResponseEntity<List<AccountForManagerDto>> findAlLByCurrencyCode(@RequestBody CurrencyCode currencyCode) {
       List <AccountForManagerDto> accountDto = accountService.findAlLByCurrencyCode(currencyCode);
        return ResponseEntity.ok(accountDto);
    }
//
    @GetMapping("/all/short")
    public ResponseEntity<List<AccountForManagerDto>> findAllShort() {
        return ResponseEntity.ok(accountService.findAllShort());
    }

    @GetMapping("/all/full")
    public ResponseEntity<List<Account>> findAll() {

        return accountService.findAll();
    }

    @GetMapping("/all/sorted-by-balance")
    public ResponseEntity<List<AccountForManagerDto>> findAllOrderedByBalanceAsc() {

        return ResponseEntity.ok(accountService.findAllOrderedByBalanceAsc());
    }
//
//  todo
//    @PostMapping("/search")
//    public ResponseEntity<Page<CardAccountData>> searchByParam(@RequestBody CardAccountDataSearchValue cardAccountDataSearchValue) {
//        Page<CardAccountData> result = cardAccountDataService.search(cardAccountDataSearchValue);
//        if (result.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(result);
//    }
//
    @PostMapping("/add")
    public ResponseEntity<Account> add(@Valid @RequestBody Account account) {
        return new ResponseEntity<>(accountService.add(account), HttpStatus.CREATED);
    }
//

    //todo

        @PostMapping("/update")
        public ResponseEntity<Account> updateAccountByParam(@RequestBody UUID id,
                @RequestParam(required = false) Integer type,
                @Balance @RequestParam(required = false) BigDecimal balance){


            accountService.updateAccountByParam(id, type, balance);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    @PostMapping("/update/active")
    public ResponseEntity<Account> updateAccountByIsActive(@RequestBody UUID id,
                                                        @RequestParam(required = false) boolean isActive){


        accountService.updateAccountByIsActive(id, isActive);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//
//    @PutMapping("{id}/update-is-blocked") //todo
//    public ResponseEntity<String> updateIsBlockedByID(
//            @PathVariable Long id,
//            @RequestParam boolean isBlocked) {
//        cardAccountDataService.updateIsBlockedByID(id, isBlocked);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("/balance-list-by-client")
//    public ResponseEntity<List<Object[]>> getTotalBalanceByClient() {
//        return cardAccountDataService.getTotalBalanceByClient();
//    }
//
//    @GetMapping("/total-balance")
//    public ResponseEntity<Map<String, Object>> getBalanceSummary() {
//        return cardAccountDataService.getBalanceSummary();
//    }
//
//    @GetMapping("/balance-by-currency")
//    public ResponseEntity<List<Object[]>> getTotalBalanceByCurrencyData() {
//        return cardAccountDataService.getTotalBalanceByCurrencyData();
//    }
//
//    @GetMapping("/balance-client-id")
//    public ResponseEntity<Map<String, Object>> getBalanceSummaryByClientId(@RequestBody Long clientId) {
//        return cardAccountDataService.getBalanceSummaryByClientId(clientId);
//    }
}
