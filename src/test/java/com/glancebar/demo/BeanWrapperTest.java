package com.glancebar.demo;

import com.glancebar.demo.vo.Company;
import com.glancebar.demo.vo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

/**
 * @author YISHEN CAI
 */
public class BeanWrapperTest {

    @Test
    void basicUsage() {
        BeanWrapper company = new BeanWrapperImpl(new Company());
        company.setPropertyValue("name", "Some Company Inc.");
        PropertyValue value = new PropertyValue("name", "Some Company Inc.");
        company.setPropertyValue(value);

        BeanWrapper jim = new BeanWrapperImpl(new Employee());
        company.setPropertyValue("managingDirector", jim.getWrappedInstance());

        Float salary = (Float) company.getPropertyValue("managingDirector.salary");

        System.out.println(company.getWrappedInstance());
        System.out.println(jim.getWrappedInstance());
    }
}
