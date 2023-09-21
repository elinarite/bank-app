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

    @GetMapping("/manager/id")
    public ResponseEntity<Product> findById(@RequestBody Long id) {
        return productService.findById(id);
    }

    @GetMapping("/manager/all/full")
    public ResponseEntity<List<Product>> findAll() {
        return productService.findAll();
    }

    @GetMapping("/manager/product-typ")
    public ResponseEntity<List<Product>> findByProductTyp(@RequestParam String productTyp) {
        return productService.findByProductTyp(productTyp);
    }

    @GetMapping("/manager/all-active")
    public ResponseEntity<List<Product>> findAllActiveManagers() {
        return productService.findAllActiveProducts();

    }

    @GetMapping("/manager/all-inactive")
    public ResponseEntity<List<Product>> findAllInactiveManagers() {
        return productService.findAllInactiveProducts();
    }

    @PostMapping("/manager/search")
    public ResponseEntity<List<Product>> searchProductsByParams(@RequestParam(required = false) BigDecimal interestRateFrom,
                                                                @RequestParam(required = false) BigDecimal interestRateTo,
                                                                @RequestParam(required = false) Integer limitFrom,
                                                                @RequestParam(required = false) Integer limitTo
    ) {
        return productService.searchProductsByParams(interestRateFrom, interestRateTo, limitFrom, limitTo);
    }

    @PutMapping("/manager/add")
    public ResponseEntity<Product> add(@RequestBody @Valid Product product) {
        return new ResponseEntity<>(productService.add(product), HttpStatus.CREATED);
    }

    @PutMapping("/manager/{id}/update")
    public ResponseEntity<Product> updateManagerByParam(@PathVariable Long id, @RequestParam(required = false) String productTyp,
                                                        @RequestParam(required = false) boolean isActive,
                                                        @RequestParam(required = false) CurrencyCode currencyCode,
                                                        @RequestParam(required = false) BigDecimal interestRate,
                                                        @RequestParam(required = false) Integer limit) {

        productService.updateProductByParam(id, isActive, currencyCode, interestRate, limit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/manager/delete")
    public ResponseEntity<String> delete(@RequestBody Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>(ErrorMessage.DELETE_BY_ID + id, HttpStatus.OK);
    }
}