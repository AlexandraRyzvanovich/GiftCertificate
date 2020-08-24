package com.epam.mjc.web;

import com.epam.mjc.web.controller.SortResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Locale;

@SpringBootApplication
@ComponentScan("com.epam.mjc")
@EntityScan(basePackages = "com.epam.mjc.dao.entity")
public class Initializer {

    public static void main(String[] args) {
        SpringApplication.run(Initializer.class, args);
    }

}