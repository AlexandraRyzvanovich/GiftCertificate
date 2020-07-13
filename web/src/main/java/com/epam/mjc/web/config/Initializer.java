package com.epam.mjc.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@ComponentScan(basePackages = "com.epam.mjc")
public class Initializer extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Initializer.class, args);
    }

//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(new SortResolver());
//    }

}