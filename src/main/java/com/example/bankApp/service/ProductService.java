package com.example.bankApp.service;

import com.example.bankApp.entity.Manager;
import com.example.bankApp.entity.Product;
import com.example.bankApp.entity.enums.CurrencyCode;
import com.example.bankApp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ResponseEntity<Product> findById(Long id) {

        Product product = productRepository.findById(id).get();

        return ResponseEntity.ok(product);
    }

    public ResponseEntity<List<Product>> findAll() {

        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<List<Product>> findByProductTyp(String productTyp) {

        List<Product> products = productRepository.findByProductTyp(productTyp);

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(products);
    }

    public ResponseEntity<List<Product>> findAllActiveProducts() {
        List<Product> activeProducts = productRepository.findAllActiveProducts();

        if (activeProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(activeProducts);
    }

    public ResponseEntity<List<Product>> findAllInactiveProducts() {
        List<Product> inactiveProducts = productRepository.findAllInactiveProducts();

        if (inactiveProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(inactiveProducts);
    }

    public ResponseEntity<List<Product>> searchProductsByParams(BigDecimal interestRateFrom,
                                                                BigDecimal interestRateTo,
                                                                Integer limitFrom,
                                                                Integer limitTo) {

        List<Product> products = productRepository.searchProductsByParams(interestRateFrom, interestRateTo, limitFrom, limitTo);

        if (products.isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(products);
    }

    @Transactional
    public Product add(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    @Transactional
    public void updateProductByParam(Long id, boolean isActive, CurrencyCode currencyCode, BigDecimal interestRate, Integer limit) {
        productRepository.updateProductByParam(id, isActive, currencyCode, interestRate, limit);
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}