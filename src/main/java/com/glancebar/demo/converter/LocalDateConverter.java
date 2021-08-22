package com.glancebar.demo.converter;


import com.glancebar.demo.exceptions.ParamException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Date string converter.
 *
 * @author YISHEN CAI
 */
@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT));
        } catch (Exception e) {
            throw new ParamException("Parameter " + source + " is not valid!");
        }
    }

    @Override
    public <U> Converter<String, U> andThen(Converter<? super LocalDate, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
