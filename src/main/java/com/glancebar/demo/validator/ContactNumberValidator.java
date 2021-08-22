package com.glancebar.demo.validator;

import com.glancebar.demo.validator.annotation.ContactNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author YISHEN CAI
 */
public class ContactNumberValidator implements ConstraintValidator<ContactNumberConstraint, String> {
    @Override
    public void initialize(ContactNumberConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("[0-9]+") && (value.length() > 8) && (value.length() < 14);
    }
}
