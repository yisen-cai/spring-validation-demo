package com.glancebar.demo.converter;

import com.glancebar.demo.exceptions.ParamException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


class LocalDateConverterTest {
    private final LocalDateConverter converter = new LocalDateConverter();


    @Test
    void convert() {
        LocalDate localDate = converter.convert("2021-01-01");
        System.out.println(localDate);
        Assertions.assertNotNull(localDate);
    }

    @Test
    void convertWithException() throws ParseException {
        Assertions.assertThrows(ParamException.class, () -> converter.convert("2021-02-29"));
    }
}