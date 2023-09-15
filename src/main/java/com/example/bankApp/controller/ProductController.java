package com.example.bankApp.controller;

import com.example.bankApp.entity.Manager;
import com.example.bankApp.entity.Product;
import com.example.bankApp.entity.enums.CurrencyCode;
import com.example.bankApp.service.ProductService;
import com.example.bankApp.util.ErrorMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/id")
    public ResponseEntity<Product> findById(@RequestBody Long id){
        return productService.findById(id);
    }

//    @Operation(
//            summary = "Get all CREDIT OFFER`s from DB",
//            description = "Returning collection of objects CREDIT OFFER with full details")
    @GetMapping("/all/full")
    public ResponseEntity<List<Product>> findAll(){
        return productService.findAll();
    }
//
//    @Operation(
//            summary = "Get CREDIT OFFER`s by name",
//            description = "Returning collection of objects CREDIT OFFER where requested parameter is country code")
    @GetMapping("/product-typ")
    public ResponseEntity<List<Product>> findByProductTyp(@RequestParam String productTyp){
        return productService.findByProductTyp(productTyp);
    }

    @GetMapping("/all-active")
    public ResponseEntity<List<Product>> findAllActiveManagers() {
        return productService.findAllActiveProducts();

    }

    @GetMapping("/all-inactive")
    public ResponseEntity<List<Product>> findAllInactiveManagers() {
        return productService.findAllInactiveProducts();
    }

    @PostMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByParams(@RequestParam (required = false) BigDecimal interestRateFrom,
                                                                @RequestParam (required = false) BigDecimal interestRateTo,
                                                                @RequestParam (required = false) Integer limitFrom,
                                                                @RequestParam (required = false) Integer limitTo
                                                                ){
        return productService.searchProductsByParams(interestRateFrom, interestRateTo, limitFrom, limitTo);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> add (@RequestBody @Valid Product product){
        return new ResponseEntity<>(productService.add(product), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<Product> updateManagerByParam(@PathVariable Long id,@RequestParam(required = false) String productTyp,
                                                        @RequestParam(required = false) boolean isActive,
                                                        @RequestParam(required = false) CurrencyCode currencyCode,
                                                        @RequestParam(required = false) BigDecimal interestRate,
                                                        @RequestParam(required = false) Integer limit){

        productService.updateProductByParam(id, isActive, currencyCode, interestRate, limit );
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>(ErrorMessage.DELETE_BY_ID + id, HttpStatus.OK);
    }


}
