package com.waa.AmazonMini.annotation;

import com.waa.AmazonMini.annotation.validators.NotZeroValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotZeroValidator.class)
public @interface NotZero {
    String message() default "Cannot be Zero";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
