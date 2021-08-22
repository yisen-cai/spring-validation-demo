package com.glancebar.demo.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Date string converter.
 *
 * @author YISHEN CAI
 */
@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public <U> Converter<String, U> andThen(Converter<? super LocalDate, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
