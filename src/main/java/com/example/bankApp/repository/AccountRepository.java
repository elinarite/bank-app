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
    List<Account> findByCurrencyCode(@Param("currencyCode") CurrencyCode currencyCode);

//
//    @Query("SELECT cad FROM CardAccountData cad " +
//            "WHERE cad.currencyData.id = :currencyId")
//    List<CardAccountData> findByCurrencyId(@Param("currencyId") Long currencyId);
//
    @Query("SELECT c From Account c" +
            " ORDER BY c.balance Asc")
    List<Account> findAllOrderByCardAccountBalanceAsc();
//
//    @Query("SELECT c FROM CardAccountData c WHERE" +
//            "(:cardNumber is null or :cardNumber='' or c.cardNumber= :cardNumber) and " +
//            "(:cardAccountNumber is null or :cardNumber='' or c.cardAccountNumber= :cardAccountNumber) and " +
//            "(cast(:issueDateFrom as timestamp ) is null or c.cardIssueDate>=:issueDateFrom) and " +
//            "(cast(:issueDateTo as timestamp ) is null or c.cardIssueDate>=:issueDateFrom) and " +
//            "(cast(:expDateFrom as timestamp ) is null or c.cardExpirationDate>=:expDateFrom) and " +
//            "(cast(:expDateTo as timestamp ) is null or c.cardExpirationDate>=:expDateTo) and " +
//            "(:balanceFrom is null or c.cardAccountBalance>=:balanceFrom) and " +
//            "(:balanceTo is null or c.cardAccountBalance<=:balanceTo) and " +
//            "(:isBlocked is null or c.isBlocked = :isBlocked) and " +
//            "(:clientDataName is null or :clientDataName='' or lower(c.clientData.clientName) like lower (concat('%', :clientDataName, '%'))) and " +
//            "(:clientDataSurname is null or :clientDataSurname='' or lower(c.clientData.clientSurname) like lower (concat('%', :clientDataSurname, '%')))"
//    )
//    Page<CardAccountData> search(
//            @Param("cardNumber") String cardNumber,
//            @Param("cardAccountNumber") String cardAccountNumber,
//            @Param("issueDateFrom") Date issueDateFrom,
//            @Param("issueDateTo") Date issueDateTo,
//            @Param("expDateFrom") Date expDateFrom,
//            @Param("expDateTo") Date expDateTo,
//            @Param("balanceFrom") BigDecimal balanceFrom,
//            @Param("balanceTo") BigDecimal balanceTo,
//            @Param("isBlocked") Boolean isBlocked,
//            @Param("clientDataName") String clientDataName,
//            @Param("clientDataSurname") String clientDataSurname,
//            Pageable page);
//
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

//    @Modifying
//    @Query("UPDATE Account a " +
//            " SET a.type = CASE WHEN :type IS NULL THEN a.type ELSE :type END, " +
//            "a.isActive = CASE WHEN :isActive IS NOT NULL THEN :isActive ELSE a.isActive END, " +
//            "a.balance = CASE WHEN :balance IS NULL THEN a.balance ELSE :balance END " +
//            "WHERE a.id = :id")
//    void updateAccountByParam(@Param("id") UUID id,
//                              @Param("type") Integer type,
//                              @Param("isActive") boolean isActive,
//
//                              @Param("balance") BigDecimal balance);

//
//    @Query("SELECT c.clientData.id, SUM(c.cardAccountBalance) AS total_balance, AVG(c.cardAccountBalance) AS average_balace, COUNT(c.cardAccountNumber) AS count_account " +
//            "FROM CardAccountData c " +
//            "GROUP BY c.clientData")
//    List<Object[]> getTotalBalanceByClient();
//
//    @Query("SELECT SUM(c.cardAccountBalance) FROM CardAccountData c")
//    BigDecimal getTotalBalanceSum();
//
//    @Query("SELECT SUM(c.cardAccountBalance) FROM CardAccountData c WHERE c.isBlocked = true")
//    BigDecimal getTotalBlockedBalanceSum();
//
//    @Query("SELECT SUM(c.cardAccountBalance) FROM CardAccountData c WHERE c.isBlocked = false")
//    BigDecimal getTotalUnblockedBalanceSum();
//
//    @Query("SELECT c.currencyData.currencyCode,  SUM(c.cardAccountBalance) AS total_balance, AVG(c.cardAccountBalance) AS average_balace, COUNT(c.currencyData) AS count_by_currency " +
//            "FROM CardAccountData c " +
//            "GROUP BY c.currencyData.currencyCode")
//    List<Object[]> getTotalBalanceByCurrencyData();
//
//    @Query("SELECT SUM(c.cardAccountBalance) FROM CardAccountData c WHERE c.isBlocked = true AND c.clientData.id = :clientId")
//    BigDecimal getTotalBlockedBalanceSumForUser(@Param("clientId") Long clientId);
//
//    @Query("SELECT SUM(c.cardAccountBalance) FROM CardAccountData c WHERE c.isBlocked = false AND c.clientData.id = :clientId")
//    BigDecimal getTotalUnblockedBalanceSumForUser(@Param("clientId") Long clientId);
//
//    @Query("SELECT SUM(c.cardAccountBalance) FROM CardAccountData c WHERE c.clientData.id = :clientId")
//    BigDecimal getTotalBalanceForUser(@Param("clientId") Long clientId);
//
//    @Query("SELECT COUNT(c) FROM CardAccountData c WHERE c.clientData.id = :clientId")
//    BigDecimal getTotalAccountCountForUser(@Param("clientId") Long clientId);
//
//    @Query("SELECT COUNT(c) FROM CardAccountData c WHERE c.isBlocked = true AND c.clientData.id = :clientId")
//    BigDecimal getTotalBlockedAccountCountForUser(@Param("clientId") Long clientId);
//
//    @Query("SELECT COUNT(c) FROM CardAccountData c WHERE c.isBlocked = false AND c.clientData.id = :clientId")
//    BigDecimal getTotalActiveAccountCountForUser(@Param("clientId") Long clientId);
//
//
//    @Query("SELECT COUNT(c) FROM CardAccountData c WHERE c.isBlocked = true")
//    BigDecimal getCountOfBlockedAccounts();
//
//    @Query("SELECT COUNT(c) FROM CardAccountData c WHERE c.isBlocked = false")
//    BigDecimal getCountOfActiveAccounts();
//
//
//    @Query("SELECT COUNT(c) FROM CardAccountData c")
//    BigDecimal getTotalAccountCount();
}
