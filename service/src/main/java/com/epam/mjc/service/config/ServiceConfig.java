package com.epam.mjc.service.config;

import com.epam.mjc.dao.*;
import com.epam.mjc.service.GiftCertificateService;
import com.epam.mjc.service.OrderService;
import com.epam.mjc.service.TagService;
import com.epam.mjc.service.UserService;
import com.epam.mjc.service.impl.GiftCertificateServiceImpl;
import com.epam.mjc.service.impl.OrderServiceImpl;
import com.epam.mjc.service.impl.TagServiceImpl;
import com.epam.mjc.service.impl.UserServiceImpl;
import com.epam.mjc.service.mapper.GiftCertificateMapper;
import com.epam.mjc.service.mapper.OrderMapper;
import com.epam.mjc.service.mapper.TagMapper;
import com.epam.mjc.service.mapper.UserMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
@EnableTransactionManagement
@EntityScan("com.epam.mjc")
public class ServiceConfig {

    @Bean
    TagService tagService(TagDao tagDao, TagMapper mapper) {
        return new TagServiceImpl(tagDao, mapper);
    }

    @Bean
    GiftCertificateService giftCertificateService(GiftCertificateDao giftCertificateDao, TagDao tagDao, GiftCertificateMapper certificateMapper,TagMapper tagMapper) {
        return new GiftCertificateServiceImpl(giftCertificateDao, tagDao, certificateMapper, tagMapper);
    }

    @Bean
    PlatformTransactionManager transactionManagementConfigurer(HikariDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserService userService(UserDao userDao, UserMapper mapper, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
        return new UserServiceImpl(userDao, mapper, roleDao, passwordEncoder);
    }

    @Bean
    OrderService orderService(OrderDao orderDao, GiftCertificateDao certificateDao, UserDao userDao, OrderMapper mapper, GiftCertificateMapper certificateMapper) {
        return new OrderServiceImpl(orderDao, certificateDao, userDao, mapper, certificateMapper);
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
