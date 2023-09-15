package com.example.bankApp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class AddressConstraint implements ConstraintValidator<Address, String> {

    private static final String ADDRESS_PATTERN = "^[a-zA-Z0-9]{10,80}$";

    @Override
    public void initialize(Address constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(ADDRESS_PATTERN))
                .orElse(false);
    }
}
