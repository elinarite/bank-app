package com.example.bankApp.service;

import com.example.bankApp.dto.AccountForClientDto;
import com.example.bankApp.dto.AccountForManagerDto;
import com.example.bankApp.entity.Account;
import com.example.bankApp.mapper.AccountMapper;
import com.example.bankApp.repository.AccountRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public List<AccountForClientDto> findAllByClientId(UUID clientId) {
        List<Account> accountsList = accountRepository.findAllByClientId(clientId);
        return accountsList.stream()
                .map(accountMapper::toAccountForClientDto)
                .collect(Collectors.toList());
    }

    public Optional<Account> findByIdForTransaction(UUID id){
        return accountRepository.findById(id);
    }

    public ResponseEntity<AccountForClientDto> findByIdAndClientId(UUID accountId, UUID clientId) {
        Account account = accountRepository.findByIdAndClientId(accountId, clientId);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AccountForClientDto dto = accountMapper.toAccountForClientDto(account);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<AccountForManagerDto> findById(UUID id) {
        Account account = accountRepository.findById(id).get();

        AccountForManagerDto dto = accountMapper.toAccountforManagerDto(account);
        return ResponseEntity.ok(dto);
    }

    public List<AccountForManagerDto> findAlLByCurrencyCode(String currencyCode) {
        List<Account> allAccounts = accountRepository.findByCurrencyCode(currencyCode);
        return allAccounts.stream()
                .map(accountMapper::toAccountforManagerDto)
                .collect(Collectors.toList());
    }

    public List<AccountForManagerDto> findAllShort() {
        List<Account> allCardAccountData = accountRepository.findAll();
        return allCardAccountData.stream().map(accountMapper::toAccountforManagerDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<List<Account>> findAll() {
        List<Account> allCardAccountData = accountRepository.findAll();
        if (allCardAccountData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allCardAccountData);
    }

    public List<AccountForManagerDto> findAllOrderedByBalanceAsc() {
        List<Account> allCardAccountData = accountRepository.findAllOrderByCardAccountBalanceAsc();
        return allCardAccountData.stream().map(accountMapper::toAccountforManagerDto)
                .collect(Collectors.toList());
    }


    /**
     * The method retrieves aggregated financial information for each client from the database.
     * The query selects the client's identifier, total balance of their card accounts,
     * average balance, and the count of their existing card accounts. This data is grouped by clients.
     */

    public ResponseEntity<List<Object[]>> getTotalBalanceByClientId() {
        return ResponseEntity.ok(accountRepository.getTotalBalanceByClientId());
    }

    /**
     * The method displays aggregated financial information for all card accounts.
     *
     * @totalBalanceSum - Sum of all balances of all card accounts.
     * @totalBlockedBalanceSum - Sum of balances of blocked card accounts.
     * @totalUnblockedBalanceSum - Sum of balances of unblocked card accounts.
     * @getTotalAccountCount - Total number of card accounts.
     * @getCountOfBlockedAccounts -  Number of blocked card accounts.
     * @getCountOfActiveAccounts - Number of unblocked card accounts.
     */

    public ResponseEntity<Map<String, Object>> getBalanceSummary() {
        Map<String, Object> result = new ConcurrentHashMap<>();

        BigDecimal totalBalanceSum = accountRepository.getTotalBalanceSum();
        BigDecimal totalBlockedBalanceSum = accountRepository.getTotalBlockedBalanceSum();
        BigDecimal totalUnblockedBalanceSum = accountRepository.getTotalUnblockedBalanceSum();
        BigDecimal getTotalAccountCount = accountRepository.getTotalAccountCount();
        BigDecimal getCountOfBlockedAccounts = accountRepository.getCountOfBlockedAccounts();
        BigDecimal getCountOfActiveAccounts = accountRepository.getCountOfActiveAccounts();

        result.put("Sum of all balances of all accounts", totalBalanceSum);
        result.put("Sum of balances of blocked accounts", totalBlockedBalanceSum);
        result.put("Sum of balances of unblocked accounts", totalUnblockedBalanceSum);
        result.put("Total number of accounts", getTotalAccountCount);
        result.put("Number of blocked accounts", getCountOfBlockedAccounts);
        result.put("Number of unblocked accounts", getCountOfActiveAccounts);

        return ResponseEntity.ok(result);
    }

    /**
     * This provided method retrieves aggregated financial information for each currency from the database.
     * The query selects the currency code, total balance across card accounts, average balance, and the count of existing accounts.
     * These data are grouped by currency code.
     */

    public ResponseEntity<List<Object[]>> getTotalBalanceByCurrencyData() {
        return ResponseEntity.ok(accountRepository.getTotalBalanceByCurrencyData());
    }


    /**
     * This method provides aggregated financial information based on the client's identifier.
     *
     * @Param totalBalanceSum - Sum of all balances of the client's card accounts.
     * @totalBlockedBalanceSum - Sum of balances of the client's blocked card accounts.
     * @totalUnblockedBalanceSum - Sum of balances of the client's unblocked card accounts.
     * @getTotalAccountCount - Total number of card accounts belonging to the client.
     * @getCountOfBlockedAccounts - Number of blocked card accounts belonging to the client.
     * @getCountOfActiveAccounts - Number of unblocked card accounts belonging to the client.
     */

    public ResponseEntity<Map<String, Object>> getBalanceSummaryByClientId(UUID clientId) {
        Map<String, Object> clientBalance = new ConcurrentHashMap<>();

        BigDecimal totalBalanceSum = accountRepository.getTotalBalanceForUser(clientId);
        BigDecimal totalBlockedBalanceSum = accountRepository.getTotalBlockedBalanceSumForUser(clientId);
        BigDecimal totalActiveBalanceSum = accountRepository.getTotalUnblockedBalanceSumForUser(clientId);

        BigDecimal getTotalAccountCountForUser = accountRepository.getTotalAccountCountForUser(clientId);
        BigDecimal getTotalBlockedAccountCountForUser = accountRepository.getTotalBlockedAccountCountForUser(clientId);
        BigDecimal getTotalActiveAccountCountForUser = accountRepository.getTotalActiveAccountCountForUser(clientId);
        if (totalBlockedBalanceSum == null) {
            clientBalance.put("Sum of balances of the client's blocked accounts", 0);

        } else {
            clientBalance.put("Sum of balances of the client's blocked accounts", totalBlockedBalanceSum);
            ;
        }
        if (totalActiveBalanceSum == null) {
            clientBalance.put("Sum of balances of the client's unblocked accounts", 0);

        } else {
            clientBalance.put("Sum of balances of the client's unblocked accounts", totalActiveBalanceSum);
        }

        if (getTotalBlockedAccountCountForUser == null) {
            clientBalance.put("Number of blocked accounts belonging to the client", 0);

        } else {
            clientBalance.put("Number of blocked accounts belonging to the client", getTotalBlockedAccountCountForUser);
        }

        if (getTotalActiveAccountCountForUser == null) {
            clientBalance.put("Number of unblocked accounts belonging to the client", 0);

        } else {
            clientBalance.put("Number of unblocked accounts belonging to the client", getTotalActiveAccountCountForUser);
        }

        clientBalance.put("Sum of all balances of the client's accounts", totalBalanceSum);
        clientBalance.put("Total number of accounts belonging to the client", getTotalAccountCountForUser);

        return ResponseEntity.ok(clientBalance);
    }


    @Transactional
    public Account add(@NotNull Account account) {
        account.setId(null);
        return accountRepository.save(account);
    }

    @Transactional
    public void updateAccountByParam(UUID id, Integer type, BigDecimal balance) {
        accountRepository.updateAccountByParam(id, type, balance);
    }

    @Transactional
    public void updateAccountByIsActive(UUID id, boolean isActive) {
        accountRepository.updateAccountByIsActive(id, isActive);
    }

    @Transactional
    public void deleteById(UUID id) {
        accountRepository.deleteById(id);
    }
}