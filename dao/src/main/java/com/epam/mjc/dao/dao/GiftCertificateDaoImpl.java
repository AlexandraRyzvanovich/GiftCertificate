package com.epam.mjc.dao.dao;

import com.epam.mjc.dao.SqlStringBuilder;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.mapper.GiftCertificateMapper;
import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_CERTIFICATE = "select * from CERTIFICATE where id = ?";
    private static final String SQL_DELETE_CERTIFICATE = "delete from certificate where id = ?";
    private static final String SQL_FIND_CERTIFICATE_BY_NAME = "select * from certificate where name = ?";
    private static final String SQL_UPDATE_CERTIFICATE = "update certificate set name = ?, description = ?, price  = ?, creation_date = ?, modification_date = ?, valid_days = ? where id = ?";
    private static final String SQL_INSERT_CERTIFICATE = "insert into certificate(name, description, price, creation_date, valid_days ) values(?,?,?,?,?) RETURNING id";
    private static final String SQL_CREATE_CERTIFICATE_TAG = "insert into certificate_tag(certificate_id, tag_id) values(?,?)";
    private static final String SQL_SELECT_ALL = "SELECT\n" +
            "c.id, c.name, c.description,\n" +
            "c.price, c.creation_date, \n" +
            "c.modification_date,\n" +
            "c.valid_days \n" +
            "FROM certificate_tag c_t\n" +
            "JOIN tag t ON t.id = c_t.tag_id \n" +
            "JOIN certificate c ON c.id = c_t.certificate_id ";

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GiftCertificate getById(long id) throws DaoException {
        List<GiftCertificate> query = jdbcTemplate.query(SQL_FIND_CERTIFICATE,
                new Object[]{id},
                new GiftCertificateMapper());
        GiftCertificate certificate = DataAccessUtils.uniqueResult(query);
        if(certificate == null) {
            throw  new DaoException("SerificateNotFound");
        }
        return certificate;
    }
    public Optional<GiftCertificate> getByName(String name) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_CERTIFICATE_BY_NAME,
                new Object[]{name},
                new GiftCertificateMapper()));
    }

    @Override
    public List<GiftCertificate> getAll(SearchParams searchParams) {
        String sqlQueryPattern = SqlStringBuilder.buildQuery(searchParams);
        if(! StringUtils.isEmpty(sqlQueryPattern)) {
            return jdbcTemplate.query(SQL_SELECT_ALL.concat(sqlQueryPattern), new GiftCertificateMapper());
        }
        return jdbcTemplate.query(SQL_SELECT_ALL, new GiftCertificateMapper());
    }

    @Override
    public GiftCertificate update(GiftCertificate certificate) throws DaoException {
        boolean result = jdbcTemplate.update(SQL_UPDATE_CERTIFICATE, certificate.getName(), certificate.getDescription(), certificate.getPrice(), certificate.getCreationDate(),
                certificate.getModificationDate(), certificate.getValidDays(),
                certificate.getId()) > 0;
        if(!result) {
            throw new DaoException("Impossible to update Certificate with given parameters");
        }
        return certificate;
    }

    @Override
    public Long create(GiftCertificate certificate) throws DaoException {
    Long id = jdbcTemplate.queryForObject(SQL_INSERT_CERTIFICATE, new Object[] {
            certificate.getName(),
            certificate.getDescription(),
            certificate.getPrice(),
            LocalDateTime.now(),
            certificate.getValidDays()} , Long.class );
        if(id == null) {
            throw new DaoException("Impossible to create Certificate with given parameters");
        }
        return id;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result = jdbcTemplate.update(SQL_DELETE_CERTIFICATE, id) > 0;
        if(!result) {
            throw new DaoException("Impossible to delete Certificate with given parameters");
        }
        return true;
    }

    @Override
    public boolean createCertificateTag(long certificateId, long tagId) throws DaoException {
        boolean result = jdbcTemplate.update(SQL_CREATE_CERTIFICATE_TAG, certificateId, tagId) > 0;
        if(!result) {
            throw new DaoException("Impossible to create Certificate_tag with given parameters");
        }
        return true;
    }
}
