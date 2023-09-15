package com.example.bankApp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class TaxCodeConstraint implements ConstraintValidator<TaxCode, String> {

    private static final String TAX_CODE_PATTERN = "^[a-zA-Z0-9]{12,20}$";

    @Override
    public void initialize(TaxCode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(TAX_CODE_PATTERN))
                .orElse(false);
    }
}
