package com.example.bankApp.service;

import com.example.bankApp.dto.AccountForClientDto;
import com.example.bankApp.dto.AccountForManagerDto;
import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.enums.CurrencyCode;
import com.example.bankApp.mapper.AccountMapper;
import com.example.bankApp.repository.AccountRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;


    //    @Cacheable(cacheNames = {"cardAccountDataDtoForUser"})
    public List<AccountForClientDto> findAllByClientId(UUID clientId) {
        List<Account> accountsList = accountRepository.findAllByClientId(clientId);
        return accountsList.stream()
                .map(accountMapper::toAccountForClientDto)
                .collect(Collectors.toList());
    }
//

//    @Cacheable(cacheNames = {"cardAccountDataDtoForUser"})

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

    //
//
//    @Cacheable(cacheNames = {"cardAccountDataById"}, key = "#id")
//    public CardAccountData findById(Long id) {
//        return cardAccountDataRepository.findById(id).get();
//
//    }
//
//    @Cacheable(cacheNames = {"cardAccountDataAll"})
    public List<AccountForManagerDto> findAlLByCurrencyCode(CurrencyCode currencyCode) {
        List<Account> allAccounts = accountRepository.findByCurrencyCode(currencyCode);
        return allAccounts.stream()
                .map(accountMapper::toAccountforManagerDto)
                .collect(Collectors.toList());
    }

    //
//    @Cacheable(cacheNames = {"cardAccountDataAllShort"})
    public List<AccountForManagerDto> findAllShort() {
        List<Account> allCardAccountData = accountRepository.findAll();
        return allCardAccountData.stream().map(accountMapper::toAccountforManagerDto)
                .collect(Collectors.toList());
    }

    //
//    @Cacheable(cacheNames = {"cardAccountDataAllFull"})
    public ResponseEntity<List<Account>> findAll() {
        List<Account> allCardAccountData = accountRepository.findAll();
        if (allCardAccountData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allCardAccountData);
    }

    //
//    @Cacheable(cacheNames = {"cardAccountDataAll"})
    public List<AccountForManagerDto> findAllOrderedByBalanceAsc() {
        List<Account> allCardAccountData = accountRepository.findAllOrderByCardAccountBalanceAsc();
        return allCardAccountData.stream().map(accountMapper::toAccountforManagerDto)
                .collect(Collectors.toList());
    }

    //
//    @Cacheable(cacheNames = {"cardAccountDataSearchByParam"})
//    public Page<CardAccountData> search(CardAccountDataSearchValue cardAccountDataSearchValue) {
//        String cardNumber = cardAccountDataSearchValue.getCardNumber();
//        String cardAccountNumber = cardAccountDataSearchValue.getCardAccountNumber();
//        Date issueDateFrom = cardAccountDataSearchValue.getIssueDateFrom();
//        Date issueDateTo = cardAccountDataSearchValue.getIssueDateTo();
//        Date expDateFrom = cardAccountDataSearchValue.getExpDateFrom();
//        Date expDateTo = cardAccountDataSearchValue.getExpDateTo();
//        BigDecimal balanceFrom = cardAccountDataSearchValue.getBalanceFrom();
//        BigDecimal balanceTo = cardAccountDataSearchValue.getBalanceTo();
//        boolean isBlocked = cardAccountDataSearchValue.isBlocked();
//        String clientDataName = cardAccountDataSearchValue.getClientDataName();
//        String clientDataSurname = cardAccountDataSearchValue.getClientDataSurname();
//        String sortDirection = cardAccountDataSearchValue.getSortDirection();
//        String sortColumn = cardAccountDataSearchValue.getSortColumn();
//        Integer pageSize = cardAccountDataSearchValue.getPageSize();
//        Integer pageNumber = cardAccountDataSearchValue.getPageNumber();
//
//        Sort.Direction direction = sortDirection == null ||
//                sortDirection.trim().length() == 0 ||
//                sortDirection.equals("asc")
//                ? Sort.Direction.ASC : Sort.Direction.DESC;
//        Sort sort = Sort.by(direction, sortColumn, "cardNumber");
//        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
//        return cardAccountDataRepository.search(
//                cardNumber,
//                cardAccountNumber,
//                issueDateFrom,
//                issueDateTo,
//                expDateFrom,
//                expDateTo,
//                balanceFrom,
//                balanceTo,
//                isBlocked,
//                clientDataName,
//                clientDataSurname,
//                pageRequest);
//    }
//
//    @Transactional
//    @Caching(evict = {
//            @CacheEvict(cacheNames = {"cardAccountDataDtoForUser"}, allEntries = true),
//            @CacheEvict(cacheNames = {"cardAccountDataAllShort"}, allEntries = true),
//            @CacheEvict(cacheNames = {"cardAccountDataFull"}, allEntries = true),
//            @CacheEvict(cacheNames = {"cardAccountDataAll"}, allEntries = true),
//            @CacheEvict(cacheNames = {"cardAccountDataSearchByParam"}, allEntries = true)})
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
}
