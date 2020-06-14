package com.epam.mjc.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


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