package com.example.bankApp.entity;

import com.example.bankApp.entity.enums.CurrencyCode;
import com.example.bankApp.validation.Balance;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "client_id", referencedColumnName = "id", updatable = false, nullable = false)
    private Client clientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "manager_id", referencedColumnName = "id", nullable = false)
    private Manager managerId;

    @NotEmpty(message = "Account Number cant be empty")
    @Column(name = "account_number")
    @Size(min = 12, max = 20, message = "Account number must be between 12 and 20 characters long")
    private String accountNumber;

    @NotNull(message = "type cant be empty")
    @Column(name = "type")
    private Integer type;

    @Column(name = "isActive")
    private boolean isActive;

    @Balance
    @Column(name = "balance", precision = 2, scale = 2)
    private BigDecimal balance;

    @NotNull(message = "Currency code cant be empty")
    @Column(name = "currency_code")
    private CurrencyCode currencyCode;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private Timestamp updateAt;


//
//    @OneToMany(mappedBy = "debitAccountId", fetch = FetchType.LAZY)
//    private List<Transaction> debitTransactions;
//
//    @OneToMany(mappedBy = "creditAccountId", fetch = FetchType.LAZY)
//    private List<Transaction> creditTransactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", accountNumber='" + accountNumber + '\'' +
                ", type=" + type +
                ", isActive=" + isActive +
                ", balance=" + balance +
                ", currencyCode=" + currencyCode +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
//todo Enum String or Integer, or int
//todo @oneToMany better biDirection, @oneToOne better unidirectional
//todo Entity Annotation not to match NotEmpty, Unique=true, usw
//todo spring validation bigDecimal
//todo Base Entity
//todo @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//todo test @Email Client, Manager


