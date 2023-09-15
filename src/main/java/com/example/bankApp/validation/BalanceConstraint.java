package com.example.bankApp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.util.Optional;

public class BalanceConstraint implements ConstraintValidator<Balance, BigDecimal> {

    private static final String BALANCE_PATTERN = "\\d{0,15}(\\.\\d{0,2})?";

    @Override
    public void initialize(Balance constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .filter(s -> !s.toString().isBlank())
                .map(s -> s.toString().matches(BALANCE_PATTERN))
                .orElse(false);
    }
}
