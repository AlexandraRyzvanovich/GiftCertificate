package com.epam.mjc.service.config;

import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.TagDao;
import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.config.AppConfig;
import com.epam.mjc.service.GiftCertificateService;
import com.epam.mjc.service.OrderService;
import com.epam.mjc.service.TagService;
import com.epam.mjc.service.UserService;
import com.epam.mjc.service.impl.GiftCertificateServiceImpl;
import com.epam.mjc.service.impl.OrderServiceImpl;
import com.epam.mjc.service.impl.TagServiceImpl;
import com.epam.mjc.service.impl.UserServiceImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.epam.mjc.service")
@Import({AppConfig.class})
public class ServiceConfig {

    @Bean
    TagService tagService(TagDao tagDao) {
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

    @Bean
    UserService userService(UserDao userDao, OrderDao orderDao) {
        return new UserServiceImpl(userDao, orderDao);
    }

    @Bean
    OrderService orderService(OrderDao orderDao) {
        return new OrderServiceImpl(orderDao);
    }
}
