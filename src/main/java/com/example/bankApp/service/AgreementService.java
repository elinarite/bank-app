package com.example.bankApp.service;


import com.example.bankApp.entity.Agreement;
import com.example.bankApp.repository.AgreementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AgreementService {

    private final AgreementRepository agreementRepository;

    public ResponseEntity<Agreement> findById(Long id) {

        Agreement agreement = agreementRepository.findById(id).get();

        return ResponseEntity.ok(agreement);
    }

    public ResponseEntity<List<Agreement>> findAll(){

        List<Agreement> agreements = agreementRepository.findAll();

        if (agreements.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(agreements);
    }

    public Agreement add(Agreement agreement){
        agreement.setId(null);
        return agreementRepository.save(agreement);
    }

}