package com.glancebar.demo.config;

import com.glancebar.demo.binding.AuthenticationResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Web相关配置信息
 *
 * @author YISHEN CAI
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(new AuthenticationResolver());
    }


    /**
     * 配置消息相关源
     */
    @Bean
    public MessageSource validationMessageResource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:validation-messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }


    /**
     * 本地化配置
     *
     * @param validationMessageResource 消息源
     */
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(MessageSource validationMessageResource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(validationMessageResource);
        return bean;
    }
}
