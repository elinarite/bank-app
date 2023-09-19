package com.example.bankApp.controller;

import com.example.bankApp.entity.Agreement;
import com.example.bankApp.entity.Manager;
import com.example.bankApp.entity.enums.CurrencyCode;
import com.example.bankApp.service.AgreementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agreement")
public class AgreementController {

    private final AgreementService agreementService;

    @GetMapping("/manager/id")
    public ResponseEntity<Agreement> findById(@RequestBody Long id){
        return agreementService.findById(id);
    }

    @GetMapping("/manager/all/full")
    public ResponseEntity<List<Agreement>> findAll(){
        return agreementService.findAll();
    }

    @PutMapping("/manager/add")
    public ResponseEntity<Agreement> add (@RequestBody @Valid Agreement agreement){
        return new ResponseEntity<>(agreementService.add(agreement), HttpStatus.CREATED);
    }
}
