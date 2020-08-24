package com.epam.mjc.web.config;

import com.epam.mjc.dao.TestConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
@Import(TestConfig.class)
@ComponentScan({"com.epam.mjc"})
public class TestAppConfig {

}
