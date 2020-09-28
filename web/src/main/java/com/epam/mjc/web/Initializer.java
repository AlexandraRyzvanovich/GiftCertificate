package com.epam.mjc.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.epam.mjc")
@EntityScan(basePackages = "com.epam.mjc.dao.entity")
public class Initializer {

    public static void main(String[] args) {
        SpringApplication.run(Initializer.class, args);
    }

}
