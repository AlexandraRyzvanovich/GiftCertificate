package com.epam.mjc.service.config;

import com.epam.mjc.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.epam.mjc.service")
public class ServiceConfig {

    ApplicationContext applicationContext;
    @Autowired
    public ServiceConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    CertificateService certificateService() {
        return new CertificateService();
    }

}
