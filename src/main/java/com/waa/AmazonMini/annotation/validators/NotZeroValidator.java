package com.waa.AmazonMini.annotation.validators;

import com.waa.AmazonMini.annotation.NotZero;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotZeroValidator implements ConstraintValidator<NotZero, Integer> {
    @Override
    public void initialize(NotZero constraintAnnotation) {}

    @Override
    public boolean isValid(Integer i, ConstraintValidatorContext constraintValidatorContext) {
        return i != 0;
    }
}
