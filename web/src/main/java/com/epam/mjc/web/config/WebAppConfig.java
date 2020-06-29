package com.epam.mjc.web.config;

import com.epam.mjc.service.config.ServiceConfig;
import com.epam.mjc.web.controller.SortResolver;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;


@Configuration
@EnableWebMvc
@ComponentScan("com.epam.mjc.web")
@Import(ServiceConfig.class)
public class WebAppConfig extends DelegatingWebMvcConfiguration {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SortResolver());
    }
}