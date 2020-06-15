package com.epam.mjc.service.config;

import com.epam.mjc.dao.config.AppConfig;
import com.epam.mjc.dao.dao.impl.CertificateDaoImpl;
import com.epam.mjc.dao.dao.impl.TagDaoImpl;
import com.epam.mjc.service.CertificateService;
import com.epam.mjc.service.TagService;
import org.springframework.context.annotation.*;

@Configuration
@Import({AppConfig.class})
public class ServiceConfig {

    @Bean
    CertificateService certificateService(CertificateDaoImpl certificateDao) {
        return new CertificateService(certificateDao);
    }

    @Bean
    TagService tagService(TagDaoImpl tagDao) {
        return new TagService(tagDao);
    }
}
