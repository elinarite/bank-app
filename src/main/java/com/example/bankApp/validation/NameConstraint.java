package com.example.bankApp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class NameConstraint implements ConstraintValidator<Name, String> {

    private static final String NAME_PATTERN = "^[a-zA-Z]{2,50}$";

    @Override
    public void initialize(Name constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(NAME_PATTERN))
                .orElse(false);
    }
}
