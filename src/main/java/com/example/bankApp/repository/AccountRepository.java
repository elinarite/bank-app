package com.example.bankApp.repository;

import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.enums.CurrencyCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {


    @Query("SELECT a FROM Account a WHERE a.clientId.id = :clientId")
    List<Account> findAllByClientId(@Param("clientId") UUID clientId);


    @Query("SELECT a From Account a WHERE a.id = :accountId AND a.clientId.id = :clientId")
    Account findByIdAndClientId(@Param("accountId") UUID accountId, @Param("clientId") UUID clientId);

    //
    @Query("SELECT a FROM Account a WHERE a.currencyCode = :currencyCode")
    List<Account> findByCurrencyCode(@Param("currencyCode") String currencyCode);

    @Query("SELECT c From Account c" +
            " ORDER BY c.balance Asc")
    List<Account> findAllOrderByCardAccountBalanceAsc();

    @Modifying
    @Query("UPDATE Account a " +
            "SET a.type = CASE WHEN :type IS NULL THEN a.type ELSE :type END, " +
            "a.balance = CASE WHEN :balance IS NULL THEN a.balance ELSE :balance END " +
            "WHERE a.id = :id")
    void updateAccountByParam(@Param("id") UUID id,
                              @Param("type") Integer type,
                              @Param("balance") BigDecimal balance);

    @Modifying
    @Query("UPDATE Account a " +
            "SET a.isActive = CASE WHEN :isActive IS NULL THEN a.isActive ELSE :isActive END " +
            "WHERE a.id = :id")
    void updateAccountByIsActive(@Param("id") UUID id,
                                 @Param("isActive") boolean isActive);


    @Query("SELECT c.clientId.id, SUM(c.balance) AS total_balance, AVG(c.balance) AS average_balace, COUNT(c.accountNumber) AS count_account " +
            "FROM Account c " +
            "GROUP BY c.clientId")
    List<Object[]> getTotalBalanceByClientId();

    @Query("SELECT SUM(c.balance) FROM Account c")
    BigDecimal getTotalBalanceSum();

    @Query("SELECT SUM(c.balance) FROM Account c WHERE c.isActive = true")
    BigDecimal getTotalBlockedBalanceSum();

    @Query("SELECT SUM(c.balance) FROM Account c WHERE c.isActive = false")
    BigDecimal getTotalUnblockedBalanceSum();

    @Query("SELECT c.currencyCode,  SUM(c.balance) AS total_balance, AVG(c.balance) AS average_balace, COUNT(c.currencyCode) AS count_by_currency " +
            "FROM Account c " +
            "GROUP BY c.currencyCode")
    List<Object[]> getTotalBalanceByCurrencyData();

    @Query("SELECT SUM(c.balance) FROM Account c WHERE c.isActive = true AND c.clientId.id = :clientId")
    BigDecimal getTotalBlockedBalanceSumForUser(@Param("clientId") UUID clientId);

    @Query("SELECT SUM(c.balance) FROM Account c WHERE c.isActive = false AND c.clientId.id = :clientId")
    BigDecimal getTotalUnblockedBalanceSumForUser(@Param("clientId") UUID clientId);

    @Query("SELECT SUM(c.balance) FROM Account c WHERE c.clientId.id = :clientId")
    BigDecimal getTotalBalanceForUser(@Param("clientId") UUID clientId);

    @Query("SELECT COUNT(c) FROM Account c WHERE c.clientId.id = :clientId")
    BigDecimal getTotalAccountCountForUser(@Param("clientId") UUID clientId);

    @Query("SELECT COUNT(c) FROM Account c WHERE c.isActive = true AND c.clientId.id = :clientId")
    BigDecimal getTotalBlockedAccountCountForUser(@Param("clientId") UUID clientId);

    @Query("SELECT COUNT(c) FROM Account c WHERE c.isActive = false AND c.clientId.id = :clientId")
    BigDecimal getTotalActiveAccountCountForUser(@Param("clientId") UUID clientId);

    @Query("SELECT COUNT(c) FROM Account c WHERE c.isActive = true")
    BigDecimal getCountOfBlockedAccounts();

    @Query("SELECT COUNT(c) FROM Account c WHERE c.isActive = false")
    BigDecimal getCountOfActiveAccounts();


    @Query("SELECT COUNT(c) FROM Account c")
    BigDecimal getTotalAccountCount();
}