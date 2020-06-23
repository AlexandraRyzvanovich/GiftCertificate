package com.epam.mjc.service.config;

import com.epam.mjc.dao.config.AppConfig;
import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.TagDao;
import com.epam.mjc.service.GiftCertificateServiceImpl;
import com.epam.mjc.service.TagServiceImpl;
import com.epam.mjc.service.GiftCertificateService;
import com.epam.mjc.service.validator.Validator;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import({AppConfig.class})
public class ServiceConfig {

    @Bean
    Validator validator() {
        return new Validator();
    }

    @Bean
    TagServiceImpl tagService(TagDao tagDao, Validator validator) {
        return new TagServiceImpl(tagDao, validator);
    }

    @Bean
    GiftCertificateService giftCertificateService(GiftCertificateDao giftCertificateDao, TagDao tagDao, Validator validator) {
        return new GiftCertificateServiceImpl(giftCertificateDao, tagDao, validator);
    }

    @Bean
    PlatformTransactionManager transactionManagementConfigurer(HikariDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
