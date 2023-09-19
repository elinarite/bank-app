package com.example.bankApp.entity;

import com.example.bankApp.entity.enums.CurrencyCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "product_typ", nullable = false, unique = true)
    private String productTyp;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "currency_code", nullable = false)
    private String currencyCode;

    @Column(name = "interest_rate", nullable = false)
    private BigDecimal interestRate;

    @Column(name = "product_limit", nullable = false)
    private Integer limit;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "update_at")
    private Timestamp updateAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(id, product.id) && Objects.equals(productTyp, product.productTyp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productTyp);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productTyp='" + productTyp + '\'' +
                ", isActive=" + isActive +
                ", currencyCode=" + currencyCode +
                ", interestRate=" + interestRate +
                ", limit=" + limit +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                '}';
    }
}