package com.glancebar.demo.validator.annotation;

import com.glancebar.demo.validator.RequestParamValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author YISHEN CAI
 */
@Documented
@Constraint(validatedBy = RequestParamValidator.class)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParamConstraint {

    String message() default "非法参数";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
