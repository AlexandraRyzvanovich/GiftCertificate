package com.epam.mjc.web.config;

import com.epam.mjc.web.controller.SortResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.epam.mjc")
public class Initializer implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(Initializer.class, args);
    }

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SortResolver());
    }

}