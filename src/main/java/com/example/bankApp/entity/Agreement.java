package com.example.bankApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "agreements")
public class Agreement {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "id", updatable = false, unique = true)
    private Account accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "product_id", referencedColumnName = "id", updatable = false)
    private Product productId;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "update_at")
    private Timestamp updateAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agreement agreement)) return false;
        return Objects.equals(id, agreement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Agreement{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", productId=" + productId +
                ", interestRate=" + interestRate +
                ", isActive=" + isActive +
                ", sum=" + sum +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                '}';
    }
}