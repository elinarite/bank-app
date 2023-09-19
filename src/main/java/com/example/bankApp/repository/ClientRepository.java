package com.example.bankApp.repository;

import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.Client;
import com.example.bankApp.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    @Query("SELECT c FROM Account c " +
            "WHERE c.clientId.id = :clientId ")
    List<Account> findAccountsByClientId(@Param("clientId") UUID clientId);

    @Query("SELECT c FROM Client c WHERE " +
            "(:firstName is null or :firstName='' or lower(c.firstName) LIKE lower(concat('%', :firstName, '%'))) and" +
            "(:lastName is null or :lastName='' or lower(c.lastName) LIKE lower(concat('%', :lastName, '%'))) and " +
            "(:email is null or :email='' or lower(c.email) LIKE lower(concat('%', :email, '%')))")
    List<Client> findClientByParam(@Param("firstName") String firstName,
                                     @Param("lastName") String lastName,
                                     @Param("email") String email);
    @Modifying
    @Query("UPDATE Client c " +
            "SET c.taxCode = CASE WHEN :taxCode IS " +
            "NULL OR :taxCode='' THEN c.taxCode ELSE :taxCode END, " +
            "c.firstName = CASE WHEN :firstName IS NULL OR :firstName='' THEN c.firstName ELSE :firstName END, " +
            "c.lastName = CASE WHEN :lastName IS NULL OR :lastName='' THEN c.lastName ELSE :lastName END, " +
            "c.email = CASE WHEN :email IS NULL OR :email='' THEN c.email ELSE :email END, " +
            "c.address = CASE WHEN :address IS NULL OR :address='' THEN c.address ELSE :address END, " +
            "c.phone = CASE WHEN :phone IS NULL OR :phone=''THEN c.phone ELSE :phone END " +
            "WHERE c.id = :id")
    void updateClientByParam(@Param("id") UUID id,
                              @Param("taxCode") String taxCode,
                              @Param("firstName") String firstName,
                              @Param("lastName") String lastName,
                              @Param("email") String email,
                              @Param("address") String address,
                              @Param("phone") String phone);
}