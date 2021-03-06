package com.glancebar.demo.validator;

import com.glancebar.demo.validator.annotation.RequestParamConstraint;
import com.glancebar.demo.vo.RequestParamObj;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Class field validation and add validation message.
 * @author YISHEN CAI
 */
public class RequestParamValidator implements ConstraintValidator<RequestParamConstraint, RequestParamObj> {
    @Override
    public void initialize(RequestParamConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(RequestParamObj value, ConstraintValidatorContext context) {
        boolean valid = true;
        if (value.getPage() > value.getSize()) {
            valid = false;
            context.disableDefaultConstraintViolation();
            // 此处可以接口Spring提供的i18n设置多国语言，消息模版等
            context.buildConstraintViolationWithTemplate("{RequestParamObj.invalid.msg}").addConstraintViolation();
            context.buildConstraintViolationWithTemplate("{RequestParamObj.invalid.msg}")
                    .addPropertyNode("page").addConstraintViolation()
                    .buildConstraintViolationWithTemplate("{RequestParamObj.invalid.msg}")
                    .addPropertyNode("size").addConstraintViolation();
        }
        return valid;
    }
}
