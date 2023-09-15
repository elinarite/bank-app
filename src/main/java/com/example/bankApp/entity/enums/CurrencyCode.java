package com.example.bankApp.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CurrencyCode {

    USD("Dollar"),
    EUR("euro");

    private final String currencyName;


}
