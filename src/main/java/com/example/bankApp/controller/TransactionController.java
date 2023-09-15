package com.example.bankApp.controller;

import com.example.bankApp.dto.ManagerForClientDto;
import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.Manager;
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

    @GetMapping("/id")
    public ResponseEntity<Transaction> findById(@RequestBody UUID id) {
        return transactionService.findById(id);
    }

//    @GetMapping("/all/short")
//    public ResponseEntity<List<ManagerForClientDto>> findAllShort() {
//        return ResponseEntity.ok(managerService.findAllShort());
//
//    }
//
    @GetMapping("/all/full")
    public ResponseEntity<List<Transaction>> findAllFull() {
        return transactionService.findAll();
    }
//
    //todo search byParam
//    @GetMapping("/accounts")
//    public ResponseEntity<List<Account>> findAccountsByManagerId(@RequestBody Long managerId) {
//        return managerService.findAccountsByManagerId(managerId);
//    }

//
//
//

//

//
    @PostMapping("/add")
    public ResponseEntity<Transaction> add (@RequestBody @Valid Transaction transaction){
        return new ResponseEntity<>(transactionService.add(transaction), HttpStatus.CREATED);
    }
//
//
}
