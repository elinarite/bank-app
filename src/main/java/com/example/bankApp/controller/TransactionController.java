package com.example.bankApp.controller;

import com.example.bankApp.dto.TransactionDto;
import com.example.bankApp.entity.Transaction;
import com.example.bankApp.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;


    @GetMapping("/manager/id")
    public ResponseEntity<TransactionDto> findById1(@RequestBody UUID id) {
        TransactionDto transactionDto = transactionService.findById(id).getBody();
        return ResponseEntity.ok(transactionDto);
    }

    @GetMapping("/manager/debit-account-id")
    public ResponseEntity<List<TransactionDto>> getByDebitAccountId(@RequestBody UUID debitAccountId) {
        List<TransactionDto> transactionDto = transactionService.findByDebitAccountId(debitAccountId).getBody();
        return ResponseEntity.ok(transactionDto);
    }

    @GetMapping("/manager/credit-account-id")
    public ResponseEntity<List<TransactionDto>> getByCreditAccountId(@RequestBody UUID creditAccountId) {
        List<TransactionDto> transactionDto = transactionService.findByDCreditAccountId(creditAccountId).getBody();
        return ResponseEntity.ok(transactionDto);
    }

    @GetMapping("/manager/short/all")
    public ResponseEntity<List<TransactionDto>> findAll() {
        List<TransactionDto> transactionDto = transactionService.findAll().getBody();
        return ResponseEntity.ok(transactionDto);
    }

    @PutMapping("/manager/add")
    public ResponseEntity<Transaction> add(@RequestBody @Valid Transaction transaction) {
        return new ResponseEntity<>(transactionService.addTransaction(transaction), HttpStatus.CREATED);
    }
}