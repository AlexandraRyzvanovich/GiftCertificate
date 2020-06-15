package com.epam.mjc.web.config;

import com.epam.mjc.service.config.ServiceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
@ComponentScan("com.epam.mjc.web")
// Указываем где искать контроллеры и остальные компоненты
@Import(ServiceConfig.class)
public class WebAppConfig implements WebMvcConfigurer {


}