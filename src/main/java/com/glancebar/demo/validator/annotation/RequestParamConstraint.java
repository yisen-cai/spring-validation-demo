package com.glancebar.demo.validator.annotation;

import com.glancebar.demo.validator.RequestParamValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotate on class, constraint class field.
 *
 * @author YISHEN CAI
 */
@Documented
@Constraint(validatedBy = RequestParamValidator.class)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParamConstraint {

    /**
     * 这里也可以指定消息模版
     */
    String message() default "{RequestParamObj.default.msg}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
