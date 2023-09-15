package com.example.bankApp.service;

import com.example.bankApp.dto.ManagerForClientDto;
import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.Manager;
import com.example.bankApp.entity.Transaction;
import com.example.bankApp.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;


    public ResponseEntity<Transaction> findById(UUID id) {
        Transaction transaction = transactionRepository.findById(id).get();
        return ResponseEntity.ok(transaction);
    }

//    @Cacheable(cacheNames = {"managerDataAll"})

//    public List<ManagerForClientDto> findAllShort() {
//        List<Manager> allManagers = managerRepository.findAll();
//        return allManagers.stream()
//                .map(managerMapper::toManagerForClientDto)
//                .collect(Collectors.toList());
//    }
    public ResponseEntity<List<Transaction>> findAll() {
        List<Transaction> allTransactions = transactionRepository.findAll();
        if (allTransactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allTransactions);
    }
//
//    //    @Cacheable(cacheNames = {"clientDataByManager"})
//    public ResponseEntity<List<Account>> findAccountsByManagerId(Long managerId) {
//        List<Account> accounts = managerRepository.findAccountsByManagerId(managerId);
//        if (accounts.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(accounts);
//    }
//    //    @Cacheable(cacheNames = {"clientDataByManager"})
//    public ResponseEntity<List<Object[]>> findByManagerWithCountAccounts() {
//        return ResponseEntity.ok(managerRepository.findManagerWithCountAccounts());
//    }
//
//    //    @Cacheable(cacheNames = {"managerDataActive"})
//    public ResponseEntity<List<Manager>> findAllActiveManagers() {
//        List<Manager> activeManagers = managerRepository.findAllActiveManagers();
//
//        if (activeManagers.isEmpty()) {
//            new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(activeManagers);
//    }
//
//    public ResponseEntity<List<Manager>> findAllInactiveManagers() {
//        List<Manager> inactiveManagers = managerRepository.findAllInactiveManagers();
//
//        if (inactiveManagers.isEmpty()) {
//            new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(inactiveManagers);
//    }
//    public ResponseEntity<List<Manager>> findManagerByParam(String firstName, String lastName) {
//        List<Manager> managerByParam = managerRepository.findManagerByParam(firstName, lastName);
//        if (managerByParam.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return ResponseEntity.ok(managerByParam);
//    }
//
    @Transactional
    public Transaction add(Transaction transaction){
        transaction.setId(null);
        return transactionRepository.save(transaction);
    }
//
//    @Transactional
//    public void updateManagerByParam(Long id, String firstName, String lastName, String email, boolean isActive) {
//        managerRepository.updateManagerByParam(id, firstName, lastName, email, isActive);
//    }
//

}
