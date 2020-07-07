package com.epam.mjc.dao;

import com.epam.mjc.dao.connection.DataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
@ComponentScan("com.epam.mjc.dao")
public class AppConfig {

    @Bean
    HikariDataSource dataSource() {
        return DataSource.getDataSource();
    }

    @Bean
    JdbcTemplate jdbcTemplate(HikariDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
