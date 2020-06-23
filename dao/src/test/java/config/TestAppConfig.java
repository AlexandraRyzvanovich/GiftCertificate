package config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan("com.epam.mjc.dao")
public class TestAppConfig {

    @Bean
    HikariDataSource testDataSource() {
        return TestDataSource.getDataSource();
    }

    @Bean
    JdbcTemplate jdbcTemplate(HikariDataSource testDataSource) {
        return new JdbcTemplate(testDataSource);
    }

}