package com.epam.mjc.web.config;

import com.epam.mjc.web.controller.SortResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Locale;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SortResolver());
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(){
        Locale.setDefault(Locale.ENGLISH);
        return new LocalValidatorFactoryBean();
    }
}
