package com.epam.mjc.dao.dao.impl;

import com.epam.mjc.dao.dao.CertificateDao;
import com.epam.mjc.dao.dao.mapper.CertificateMapper;
import com.epam.mjc.dao.entity.Certificate;
import com.epam.mjc.dao.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CertificateDaoImpl implements CertificateDao {
    private JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_CERTIFICATE = "select * from CERTIFICATE where id = ?";
    private final String SQL_DELETE_CERTIFICATE = "delete from certificate where id = ?";
    private final String SQL_UPDATE_CERTIFICATE = "update certificate set name = ?, description = ?, price  = ?, creation_date = ?, modification_date = ?, valid_days = ? where id = ?";
    private final String SQL_GET_ALL = "select * from certificate";
    private final String SQL_INSERT_CERTIFICATE = "insert into certificate(name, description, price, creation_date, modification_date, valid_days ) values(?,?,?,?,?,?)";


    @Autowired
    public CertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Certificate> getById(long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_CERTIFICATE,
                new Object[]{id},
                new CertificateMapper()));
    }

    @Override
    public List<Certificate> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new CertificateMapper());
    }

    @Override
    public Certificate update(Certificate certificate) throws DaoException {
        boolean result = jdbcTemplate.update(SQL_UPDATE_CERTIFICATE, certificate.getName(), certificate.getDescription(), certificate.getPrice(), certificate.getCreationDate(),
                certificate.getModificationDate(), certificate.getValidDays(),
                certificate.getId()) > 0;
        if(result != true) {
            throw new DaoException("Impossible to update Certificate with given parameters");
        }
        return certificate;
    }

    @Override
    public Certificate create(Certificate certificate) throws DaoException {
        boolean result = jdbcTemplate.update(SQL_INSERT_CERTIFICATE, certificate.getName(), certificate.getDescription(), certificate.getPrice(), certificate.getCreationDate(),
                certificate.getModificationDate(), certificate.getValidDays(),
                certificate.getPrice()) > 0;
        if(result != true) {
            throw new DaoException("Impossible to create Certificate with given parameters");
        }
        return certificate;
    }

    @Override
    public boolean delete(Certificate certificate) throws DaoException {
        boolean result = jdbcTemplate.update(SQL_DELETE_CERTIFICATE, certificate.getId()) > 0;
        if(result != true) {
            throw new DaoException("Impossible to delete Certificate with given parameters");
        }
        return true;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result = jdbcTemplate.update(SQL_DELETE_CERTIFICATE, id) > 0;
        if(result != true) {
            throw new DaoException("Impossible to delete Certificate with given parameters");
        }
        return true;
    }
}
