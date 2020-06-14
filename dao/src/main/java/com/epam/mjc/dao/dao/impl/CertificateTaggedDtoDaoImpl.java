package com.epam.mjc.dao.dao.impl;

import com.epam.mjc.dao.dao.Dao;
import com.epam.mjc.dao.dao.mapper.CertificateTaggedDtoMapper;
import com.epam.mjc.dao.dto.CertificatedTaggedDto;
import com.epam.mjc.dao.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class CertificateTaggedDtoDaoImpl implements Dao<CertificatedTaggedDto> {
    private JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_GIFT_CERTIFICATE = "SELECT " +
            "i.id," +
            " i.name," +
            " i.description," +
            " i.price," +
            " i.creation_date," +
            " i.modification_date," +
            " i.valid_days," +
            " t.tags\n" +
            "FROM   certificate      i\n" +
            "JOIN  (  \n" +
            "   SELECT it.certificate_id AS id, array_agg(t.tag_name) AS tags\n" +
            "   FROM   gift_certificate it\n" +
            "   JOIN   certificate_tag       t  ON t.id = it.tag_id\n" +
            "   GROUP  BY it.certificate_id\n" +
            "   ) t USING (id)\n" +
            "   WHERE i.id = ?";

    private final String SQL_GET_ALL_TAGS = "SELECT " +
            "i.id," +
            " i.name," +
            " i.description," +
            " i.price," +
            " i.creation_date," +
            " i.modification_date," +
            " i.valid_days," +
            " t.tags\n" +
            "FROM   certificate      i\n" +
            "JOIN  (  \n" +
            "   SELECT it.certificate_id AS id, array_agg(t.tag_name) AS tags\n" +
            "   FROM   gift_certificate it\n" +
            "   JOIN   certificate_tag       t  ON t.id = it.tag_id\n" +
            "   GROUP  BY it.certificate_id\n" +
            "   ) t USING (id)\n";

    @Autowired
    public CertificateTaggedDtoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<CertificatedTaggedDto> getById(long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_GIFT_CERTIFICATE,
                new Object[]{id},
                new CertificateTaggedDtoMapper()));
    }

    @Override
    public List<CertificatedTaggedDto> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_TAGS, new CertificateTaggedDtoMapper());
    }

    @Override
    public boolean update(CertificatedTaggedDto item) throws DaoException {
        throw new DaoException("Operation not supported");
    }

    @Override
    public boolean create(CertificatedTaggedDto item) throws DaoException {
        throw new DaoException("Operation not supported");
    }

    @Override
    public boolean delete(CertificatedTaggedDto item) throws DaoException {
        throw new DaoException("Operation not supported");
    }


}
