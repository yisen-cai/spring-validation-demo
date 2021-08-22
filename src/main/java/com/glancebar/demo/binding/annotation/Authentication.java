package com.glancebar.demo.binding.annotation;

import java.lang.annotation.*;

/**
 * Used to inject authentication principal.
 *
 * @author YISHEN CAI
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Authentication {
    Class<?> clazz() default String.class;
}
