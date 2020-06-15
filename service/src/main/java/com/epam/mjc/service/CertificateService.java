package com.epam.mjc.service;

import com.epam.mjc.dao.config.AppConfig;
import com.epam.mjc.dao.dao.impl.CertificateDaoImpl;
import com.epam.mjc.dao.entity.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CertificateService {
    private AnnotationConfigApplicationContext context;
    JdbcTemplate tmpl;
    CertificateDaoImpl certificateDao;

    @Autowired
    public CertificateService() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        tmpl = context.getBean("jdbcTemplate", JdbcTemplate.class);
        certificateDao = new CertificateDaoImpl(tmpl);
    }

    public Optional<Certificate> getCertificateById(long id) {
        return certificateDao.getById(id);
    }

    public List<Certificate> getAllCertificates() {
        return certificateDao.getAll();
    }

    public boolean createCertificate(Certificate certificate) {
        return certificateDao.create(certificate);

    }
}

