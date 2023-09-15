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

    @GetMapping("/id")
    public ResponseEntity<Agreement> findById(@RequestBody Long id){
        return agreementService.findById(id);
    }

//    @Operation(
//            summary = "Get CREDIT OFFER by Id",
//            description = "Returning object with full details where requested parameter is ID of credit offer")
//    @GetMapping("/id")
//    public ResponseEntity<CreditOfferDto> findById(@RequestBody Long id){
//        return service.findById(id);
//    }
//

    @GetMapping("/all/full")
    public ResponseEntity<List<Agreement>> findAll(){
        return agreementService.findAll();
    }
//
//    @Operation(
//            summary = "Get CREDIT OFFER`s by name",
//            description = "Returning collection of objects CREDIT OFFER where requested parameter is country code")
//    @GetMapping("/name")
//    public ResponseEntity<List<CreditOfferDto>> findByName(@RequestBody String name){
//        return service.findByName(name);
//    }
//
//    @Operation(
//            summary = "Get valid CREDIT OFFER`s",
//            description = "Returning collection of objects CREDIT OFFER which still valid for NOW")
//    @GetMapping("/valid")
//    public ResponseEntity<List<CreditOfferDto>> findAllValid(){
//        return service.findAllValid();
//    }
//
//    @Operation(
//            summary = "Get expired CREDIT OFFER`s",
//            description = "Returning collection of objects CREDIT OFFER which expired")
//    @GetMapping("/expired")
//    public ResponseEntity<List<CreditOfferDto>> findAllExpired(){
//        return service.findAllExpired();
//    }
//
//    @Operation(
//            summary = "Get CREDIT OFFER`s by parameters",
//            description = "Returning collection of objects CREDIT OFFER which still valid for NOW")
//    @GetMapping("/params")
//    public ResponseEntity<List<CreditOfferDto>> findByParams(@RequestBody CreditOfferSearchValues values){
//        return service.findByParams(values);
//    }
//
//    @Operation(
//            summary = "Add CREDIT OFFER",
//            description = "Sending object CREDIT OFFER to DB for save. ID must be NULL (autoincrement in DB)")
    @PostMapping("/add")
    public ResponseEntity<Agreement> add (@RequestBody @Valid Agreement agreement){
        return new ResponseEntity<>(agreementService.add(agreement), HttpStatus.CREATED);
    }
//    @PostMapping("/{id}/update")
//    public ResponseEntity<Agreement> updateAgreementByParam(@PathVariable Long id, @RequestParam(required = false) boolean isActive,
//                                                        @RequestParam(required = false) BigDecimal sum){
//
//        agreementService.updateProductByParam(id, isActive, isActive, sum);
//        return new ResponseEntity<>(HttpStatus.OK);
}
