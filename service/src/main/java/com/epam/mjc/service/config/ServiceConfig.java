package com.epam.mjc.service.config;

import com.epam.mjc.dao.config.AppConfig;
import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.TagDao;
import com.epam.mjc.service.GiftCertificateService;
import com.epam.mjc.service.impl.GiftCertificateServiceImpl;
import com.epam.mjc.service.impl.TagServiceImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import({AppConfig.class})
public class ServiceConfig {

    @Bean
    TagServiceImpl tagService(TagDao tagDao) {
        return new TagServiceImpl(tagDao);
    }

    @Bean
    GiftCertificateService giftCertificateService(GiftCertificateDao giftCertificateDao, TagDao tagDao) {
        return new GiftCertificateServiceImpl(giftCertificateDao, tagDao);
    }

    @Bean
    PlatformTransactionManager transactionManagementConfigurer(HikariDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
