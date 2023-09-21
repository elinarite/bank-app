package com.example.bankApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountForManagerDto {

    UUID id;
    String clientFirstName;
    String clientLastName;
    String cardAccountNumber;
    Integer type;
    boolean isActive;
    BigDecimal balance;
    String currencyCode;
    String managerFirstName;
    String managerLastName;

}