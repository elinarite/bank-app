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

    //    @Cacheable(cacheNames = {"creditOfferById"}, key = "#id")
    public ResponseEntity<Agreement> findById(Long id) {

        Agreement agreement = agreementRepository.findById(id).get();

        return ResponseEntity.ok(agreement);
    }

//    @Cacheable(cacheNames = {"creditOfferAll"})
    public ResponseEntity<List<Agreement>> findAll(){

        List<Agreement> agreements = agreementRepository.findAll();

        if (agreements.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(agreements);
    }
//
//    @Cacheable(cacheNames = {"creditOfferByName"})
//    public ResponseEntity<List<CreditOfferDto>> findByName(String name){
//
//        List<CreditOffer>creditOfferByName = repository.findByName(name);
//
//        if (creditOfferByName.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return ResponseEntity.ok(mapper.toCreditReq(creditOfferByName));
//    }
//
//    @Cacheable(cacheNames = {"creditOfferAllValid"})
//    public ResponseEntity<List<CreditOfferDto>> findAllValid(){
//        List<CreditOffer>creditOfferAllValid = repository.findAllValid();
//
//        if (creditOfferAllValid.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return ResponseEntity.ok(mapper.toCreditReq(creditOfferAllValid));
//    }
//
//    @Cacheable(cacheNames = {"creditOfferAllExpired"})
//    public ResponseEntity<List<CreditOfferDto>> findAllExpired(){
//        List<CreditOffer>creditOfferAllExpired = repository.findAllExpired();
//
//        if (creditOfferAllExpired.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return ResponseEntity.ok(mapper.toCreditReq(creditOfferAllExpired));
//    }
//
//    public ResponseEntity<List<CreditOfferDto>> findByParams(CreditOfferSearchValues values){
//
//        String creditName = values.getCreditName();
//        Short creditTerm = values.getCreditTerm();
//        BigDecimal creditAmount = values.getCreditAmount();
//        String creditCurrency = values.getCreditCurrency();
//
//        List<CreditOffer>creditOfferAllValid = repository.findByParams(creditName, creditTerm, creditAmount, creditCurrency);
//
//        if (creditOfferAllValid.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return ResponseEntity.ok(mapper.toCreditReq(creditOfferAllValid));
//    }
//
//    @Caching(evict = {
//            @CacheEvict(cacheNames = {"creditOfferAll"}, allEntries = true),
//            @CacheEvict(cacheNames = {"creditOfferByName"}, allEntries = true),
//            @CacheEvict(cacheNames = {"creditOfferAllValid"}, allEntries = true),
//            @CacheEvict(cacheNames = {"creditOfferAllExpired"}, allEntries = true)})
    public Agreement add(Agreement agreement){
        agreement.setId(null);
        return agreementRepository.save(agreement);
    }
//
//    @Caching(evict = {
//            @CacheEvict(cacheNames = {"creditOfferById"}, key = "#id"),
//            @CacheEvict(cacheNames = {"creditOfferAll"}, allEntries = true),
//            @CacheEvict(cacheNames = {"creditOfferByName"}, allEntries = true),
//            @CacheEvict(cacheNames = {"creditOfferAllValid"}, allEntries = true),
//            @CacheEvict(cacheNames = {"creditOfferAllExpired"}, allEntries = true)})
//    public void update(CreditOffer creditOffer, Long id){
//
//        Optional<CreditOffer> origin = repository.findById(id);
//
//        // Unchangeable credit parameters will be rewritten from origin credit offer
//        creditOffer.setCreditInterest(origin.get().getCreditInterest());
//        creditOffer.setCreditFine(origin.get().getCreditFine());
//        creditOffer.setCurrencyData(origin.get().getCurrencyData());
//
//        repository.save(creditOffer);
//    }
//
//}
}