package com.epam.mjc.dao.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "params.datasource")
@ComponentScan(basePackages = "com.epam.mjc.dao")
public class AppConfig {

}
