package com.glancebar.demo.validator;

import com.glancebar.demo.vo.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 自定义实现的一个验证器
 *
 * @author YISHEN CAI
 */
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        Person p = (Person) target;
        if (p.getAge() < 0) {
            errors.rejectValue("age", "negative age value");
        } else if (p.getAge() > 110) {
            errors.rejectValue("age", "too old age!");
        }
    }
}
