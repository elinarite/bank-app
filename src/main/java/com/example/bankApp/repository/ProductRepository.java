package com.example.bankApp.repository;

import com.example.bankApp.entity.Manager;
import com.example.bankApp.entity.Product;
import com.example.bankApp.entity.enums.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.productTyp) LIKE lower(concat('%', :productTyp, '%')) " +
            "ORDER BY p.productTyp ASC")
    List<Product> findByProductTyp(@Param("productTyp") String productTyp);

    @Query("SELECT p FROM Product p WHERE p.isActive = true")
    List<Product> findAllActiveProducts();

    @Query("SELECT p FROM Product p WHERE p.isActive = false")
    List<Product> findAllInactiveProducts();

    @Query("SELECT p FROM Product p WHERE " +
            "(:interestRateFrom is null or p.interestRate>=:interestRateFrom) and " +
            "(:interestRateTo is null or p.interestRate<=:interestRateTo) and " +
            "(:limitFrom is null or p.limit>=:limitFrom) and " +
            "(:limitTo is null or p.limit<=:limitTo) ")
    List<Product> searchProductsByParams(@Param("interestRateFrom") BigDecimal interestRateFrom,
                                   @Param("interestRateTo") BigDecimal interestRateTo,
                                   @Param("limitFrom") Integer limitFrom,
                                   @Param("limitTo") Integer limitTo);

    @Modifying
    @Query("UPDATE Product p " +
            "SET p.isActive = CASE WHEN :isActive IS " +
            "NULL THEN p.isActive ELSE :isActive END, " +
            "p.currencyCode = CASE WHEN :currencyCode IS NULL THEN p.currencyCode ELSE :currencyCode END, " +
            "p.interestRate = CASE WHEN :interestRate IS NULL THEN p.interestRate ELSE :interestRate END, " +
            "p.limit = CASE WHEN :limit IS NOT NULL THEN :limit ELSE p.limit END " +
            "WHERE p.id = :id")
    void updateProductByParam(@Param("id") Long id,
                              @Param("isActive") Boolean isActive,
                              @Param("currencyCode") CurrencyCode currencyCode,
                              @Param("interestRate") BigDecimal interestRate,
                              @Param("limit") Integer limit);
}
