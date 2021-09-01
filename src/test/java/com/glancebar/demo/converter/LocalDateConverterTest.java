package com.glancebar.demo.converter;

import com.glancebar.demo.exceptions.ParamException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class LocalDateConverterTest {
    private final LocalDateConverter converter = new LocalDateConverter();
    private final Pattern pattern = Pattern.compile("####(.*)####");

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