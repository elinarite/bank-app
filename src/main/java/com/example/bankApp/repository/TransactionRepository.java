package com.example.bankApp.repository;

import com.example.bankApp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT c FROM Transaction c WHERE c.debitAccountId.id = :debitAccountId")
    List<Transaction> findByDebitAccountId(@Param("debitAccountId") UUID debitAccountId);

    @Query("SELECT c FROM Transaction c WHERE c.creditAccountId.id = :creditAccountId")
    List<Transaction> findByCreditAccountId(@Param("creditAccountId") UUID creditAccountId);


}