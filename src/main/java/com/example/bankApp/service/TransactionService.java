package com.example.bankApp.service;

import com.example.bankApp.dto.AccountForClientDto;
import com.example.bankApp.dto.AccountForManagerDto;
import com.example.bankApp.dto.ManagerForClientDto;
import com.example.bankApp.dto.TransactionDto;
import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.Manager;
import com.example.bankApp.entity.Transaction;
import com.example.bankApp.mapper.TransactionMapper;
import com.example.bankApp.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;
    private final AccountService accountService;


    public ResponseEntity<TransactionDto> findById(UUID id) {
        Transaction transaction = transactionRepository.findById(id).get();
        TransactionDto dto = transactionMapper.toTransactionDtoDto(transaction);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<TransactionDto>> findByDebitAccountId(UUID id) {
        List<Transaction> transaction = transactionRepository.findByDebitAccountId(id);
        if (transaction.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(transaction.stream()
                .map(transactionMapper::toTransactionDtoDto)
                .collect(Collectors.toList()));
    }

    public ResponseEntity<List<TransactionDto>> findByDCreditAccountId(UUID id) {
        List<Transaction> transaction = transactionRepository.findByCreditAccountId(id);
        if (transaction.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(transaction.stream()
                .map(transactionMapper::toTransactionDtoDto)
                .collect(Collectors.toList()));
    }

    public ResponseEntity<List<TransactionDto>> findAll() {
        List<Transaction> allTransactions = transactionRepository.findAll();
        if (allTransactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allTransactions.stream()
                .map(transactionMapper::toTransactionDtoDto)
                .collect(Collectors.toList()));
    }

    @Transactional
    public Transaction addTransaction(Transaction transaction) {
        UUID debitAccountId = transaction.getDebitAccountId().getId();
        UUID creditAccountId = transaction.getCreditAccountId().getId();
        BigDecimal amount = transaction.getAmount();

        Optional<Account> debitAccount = accountService.findByIdForTransaction(debitAccountId);
        Optional<Account> creditAccount = accountService.findByIdForTransaction(creditAccountId);

        Transaction newTransaction = null;
        if (debitAccount.isPresent() && creditAccount.isPresent()) {
            Account debitAccountEntity = debitAccount.get();
            Account creditAccountEntity = creditAccount.get();

            BigDecimal newDebitBalance = debitAccountEntity.getBalance().add(amount);
            BigDecimal newCreditBalance = creditAccountEntity.getBalance().subtract(amount);

            debitAccountEntity.setBalance(newDebitBalance);
            creditAccountEntity.setBalance(newCreditBalance);

            newTransaction = new Transaction();
            newTransaction.setType(transaction.getType());
            newTransaction.setAmount(amount);
            newTransaction.setDescription(transaction.getDescription());
            newTransaction.setDebitAccountId(debitAccountEntity);
            newTransaction.setCreditAccountId(creditAccountEntity);


        } else {
            throw new TransactionException("One or both accounts do not exist.");
        }
        return transactionRepository.save(newTransaction);
    }
}