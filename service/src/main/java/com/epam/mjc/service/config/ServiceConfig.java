package com.epam.mjc.service.config;

import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.TagDao;
import com.epam.mjc.dao.UserDao;
import com.epam.mjc.service.GiftCertificateService;
import com.epam.mjc.service.OrderService;
import com.epam.mjc.service.TagService;
import com.epam.mjc.service.UserService;
import com.epam.mjc.service.impl.GiftCertificateServiceImpl;
import com.epam.mjc.service.impl.OrderServiceImpl;
import com.epam.mjc.service.impl.TagServiceImpl;
import com.epam.mjc.service.impl.UserServiceImpl;
import com.epam.mjc.service.mapper.GiftCertificateMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
@EnableTransactionManagement
@EntityScan("com.epam.mjc")
public class ServiceConfig {

    @Bean
    TagService tagService(TagDao tagDao) {
        return new TagServiceImpl(tagDao);
    }

    @Bean
    GiftCertificateService giftCertificateService(GiftCertificateDao giftCertificateDao, TagDao tagDao, GiftCertificateMapper mapper) {
        return new GiftCertificateServiceImpl(giftCertificateDao, tagDao, mapper);
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
    OrderService orderService(OrderDao orderDao, GiftCertificateDao certificateDao, UserDao userDao) {
        return new OrderServiceImpl(orderDao, certificateDao, userDao);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }

}
