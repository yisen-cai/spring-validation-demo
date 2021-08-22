package com.glancebar.demo.vo;

import com.glancebar.demo.validator.annotation.ContactNumberConstraint;

import javax.validation.constraints.NotEmpty;

/**
 * @author YISHEN CAI
 */
public class Contact {
    @ContactNumberConstraint(message = "号码格式不正确")
    @NotEmpty(message = "号码不可为空")
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
