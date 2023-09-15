package com.example.bankApp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class EmailConstraint implements ConstraintValidator<Email, String> {

    private static final String EMAIL_PATTERN = "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @Override
    public void initialize(Email constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(EMAIL_PATTERN))
                .orElse(false);
    }
}
