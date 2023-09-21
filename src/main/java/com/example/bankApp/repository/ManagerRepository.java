
package com.example.bankApp.repository;

import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.Manager;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManagerRepository extends JpaRepository<Manager, Long> {


    @Query("SELECT m FROM Manager m" +
            " ORDER BY m.lastName ASC")
    List<Manager> findAll();

    @Query("SELECT m.accounts FROM Manager m WHERE m.id = :managerId")
    List<Account> findAccountsByManagerId(@Param("managerId") Long managerId);

    @Query("SELECT m.id,  m.firstName, m.lastName, COUNT(c) as numAccounts " +
            "FROM Manager m " +
            "LEFT JOIN m.accounts c " +
            "GROUP BY m.id " +
            "ORDER BY numAccounts")
    List<Object[]> findManagerWithCountAccounts();

    @Query("SELECT m FROM Manager m WHERE m.isActive = true")
    List<Manager> findAllActiveManagers();

    @Query("SELECT m FROM Manager m WHERE m.isActive = false")
    List<Manager> findAllInactiveManagers();

    @Query("SELECT m FROM Manager m WHERE " +
            "(:firstName is null or :firstName='' or lower(m.firstName) LIKE lower(concat('%', :firstName, '%'))) and" +
            "(:lastName is null or :lastName='' or lower(m.lastName) LIKE lower(concat('%', :lastName, '%')))")
    List<Manager> findManagerByParam(@Param("firstName") String firstName,
                                             @Param("lastName") String lastName);

    @Modifying
    @Query("UPDATE Manager m " +
            "SET m.firstName = CASE WHEN :firstName IS " +
            "NULL OR :firstName='' THEN m.firstName ELSE :firstName END, " +
            "m.lastName = CASE WHEN :lastName IS NULL OR :lastName='' THEN m.lastName ELSE :lastName END, " +
            "m.email = CASE WHEN :email IS NULL OR :email='' THEN m.email ELSE :email END, " +
            "m.isActive = CASE WHEN :isActive IS NOT NULL THEN :isActive ELSE m.isActive END " +
            "WHERE m.id = :id")
    void updateManagerByParam(@Param("id") Long id,
                              @Param("firstName") String firstName,
                              @Param("lastName") String lastName,
                              @Param("email") String email,
                              @Param("isActive") boolean isActive);

}

