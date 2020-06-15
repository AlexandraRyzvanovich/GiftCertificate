package com.epam.mjc.service.config;

import com.epam.mjc.dao.config.AppConfig;
import com.epam.mjc.dao.dao.impl.CertificateDaoImpl;
import com.epam.mjc.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan("com.epam.mjc.service")
public class ServiceConfig {
    private ApplicationContext applicationContext;


    @Autowired
    public ServiceConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    CertificateService certificateService() {
        return new CertificateService();
    }

}
