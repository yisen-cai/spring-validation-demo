package com.glancebar.demo.converter;

import com.glancebar.demo.exceptions.ParamException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Datetime string converter
 *
 * @author YISHEN CAI
 */
@Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        try {
            return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("uuuu-M-d HH:mm:ss").withResolverStyle(ResolverStyle.STRICT));
        } catch (Exception e) {
            throw new ParamException("Parameter " + source + " is not valid!");
        }
    }

    @Override
    public <U> Converter<String, U> andThen(Converter<? super LocalDateTime, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
