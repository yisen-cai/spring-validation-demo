package com.glancebar.demo.converter;

import com.glancebar.demo.exceptions.ParamException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class LocalDateTimeConverterTest {
    private LocalDateTimeConverter converter = new LocalDateTimeConverter();

    @Test
    void convert() {
        LocalDateTime localDateTime = converter.convert("2020-01-01 12:12:12");
        Assertions.assertNotNull(localDateTime);
        System.out.println(localDateTime);
    }


    @Test
    void convertInvalid() {
        Assertions.assertThrows(ParamException.class, () -> converter.convert("2020-01-01 12:12:61"));
    }
}