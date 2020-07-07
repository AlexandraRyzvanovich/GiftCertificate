package com.epam.mjc.web.config;

import com.epam.mjc.service.config.ServiceConfig;
import com.epam.mjc.web.controller.SortResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
@ComponentScan("com.epam.mjc")
@Import(ServiceConfig.class)
public class Initializer implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(Initializer.class, args);
    }

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SortResolver());
    }
}