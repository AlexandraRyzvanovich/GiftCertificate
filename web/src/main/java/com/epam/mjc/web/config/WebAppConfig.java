package com.epam.mjc.web.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
// Указываем где искать контроллеры и остальные компоненты
@ComponentScan("com.epam.mjc.web")
public class WebAppConfig implements WebMvcConfigurer {
    ApplicationContext applicationContext;

    public WebAppConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}