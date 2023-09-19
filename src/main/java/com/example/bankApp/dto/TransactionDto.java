package com.example.bankApp.dto;

import com.example.bankApp.entity.Account;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class TransactionDto {

    String debitAccountLastName;
    UUID debitAccountId;
    Integer type;
    BigDecimal amount;
    String creditAccountLastName;
    UUID creditAccountId;
    String description;
}