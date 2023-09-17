
package com.example.bankApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.lang.NonNullApi;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "managers")
public class Manager {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", updatable = false)
    private Long id;

    @NotEmpty(message = "First name cant be empty")
    @Column(name = "first_name", nullable = false)
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters long")
    private String firstName;

    @NotEmpty(message = "Last name cant be empty")
    @Column(name = "last_name", nullable = false)
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters long")
    private String lastName;

    @NotEmpty(message = "Email cant be empty")
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull(message = "Status cant be empty")
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private Timestamp updateAt;

    @OneToMany(mappedBy = "managerId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Account> accounts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager manager)) return false;
        return Objects.equals(id, manager.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", firsName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
//todo BaseEntity
