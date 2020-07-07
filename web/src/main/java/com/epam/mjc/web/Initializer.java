package com.epam.mjc.web;


import com.epam.mjc.service.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan("com.epam.mjc")
@Import(ServiceConfig.class)
public class Initializer {

    public static void main(String[] args) {
        SpringApplication.run(Initializer.class, args);
    }

//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(new SortResolver());
//    }
}