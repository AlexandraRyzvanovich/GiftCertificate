package com.epam.mjc.dao.config;

import com.epam.mjc.dao.dao.connection.DataSource;
import com.epam.mjc.dao.dao.impl.CertificateDaoImpl;
import com.epam.mjc.dao.dao.impl.CertificateTaggedDtoDaoImpl;
import com.epam.mjc.dao.dao.impl.TagDaoImpl;
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

    @Bean
    CertificateDaoImpl certificateDaoImpl(JdbcTemplate jdbcTemplate) {
        return new CertificateDaoImpl(jdbcTemplate);
    }

    @Bean
    TagDaoImpl tagDaoImpl(JdbcTemplate jdbcTemplate) {
        return new TagDaoImpl(jdbcTemplate);
    }

    @Bean
    CertificateTaggedDtoDaoImpl certificateTaggedDtoDaoImpl(JdbcTemplate jdbcTemplate) {
        return new CertificateTaggedDtoDaoImpl(jdbcTemplate);
    }
}
