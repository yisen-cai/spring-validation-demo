package com.glancebar.demo.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import com.glancebar.demo.converter.LocalDateConverter;
import com.glancebar.demo.converter.LocalDateTimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 基于Jackson序列化反序列化相关设置
 *
 * @author YISHEN CAI
 */
@Configuration
public class JacksonConfig {


    /**
     * 创建基于Jackson的配置Bean
     *
     * @param localDateTimeConverter 定义好的LocalDateTime类型转换Bean
     * @param localDateConverter     定义好的LocalDate类型转换Bean
     * @return Jackson配置组件
     */
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder(LocalDateTimeConverter localDateTimeConverter,
                                                           LocalDateConverter localDateConverter) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new KotlinModule());
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(localDateTimeConverter));
        builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(localDateConverter));
        return builder;
    }

    /**
     * 基于LocalDateTime类型的反序列化器
     */
    static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        private final LocalDateTimeConverter localDateTimeConverter;

        LocalDateTimeDeserializer(LocalDateTimeConverter localDateTimeConverter) {
            this.localDateTimeConverter = localDateTimeConverter;
        }

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return localDateTimeConverter.convert(p.getText());
        }
    }

    /**
     * 基于LocalDate类型的反序列化器
     */
    static class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

        private final LocalDateConverter localDateConverter;

        LocalDateDeserializer(LocalDateConverter localDateConverter) {
            this.localDateConverter = localDateConverter;
        }

        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return localDateConverter.convert(p.getText());
        }
    }
}
