package com.glancebar.demo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Datetime string converter
 *
 * @author YISHEN CAI
 */
@Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public <U> Converter<String, U> andThen(Converter<? super LocalDateTime, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
