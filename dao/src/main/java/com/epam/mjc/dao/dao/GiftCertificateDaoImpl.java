package com.epam.mjc.dao.dao;

import com.epam.mjc.dao.builder.SqlStringBuilder;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.mapper.GiftCertificateMapper;
import com.epam.mjc.dao.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_CERTIFICATE = "select * from CERTIFICATE where id = ?";
    private static final String SQL_DELETE_CERTIFICATE = "delete from certificate where id = ?";
    private static final String SQL_UPDATE_CERTIFICATE = "update certificate set name = ?, description = ?, price  = ?, modification_date = ?, valid_days = ? where id = ?";
    private static final String SQL_INSERT_CERTIFICATE = "insert into certificate(name, description, price, creation_date, valid_days ) values(?,?,?,?,?) RETURNING id";
    private static final String SQL_CREATE_CERTIFICATE_TAG = "insert into certificate_tag(certificate_id, tag_id) values(?,?)";
    private static final String SQL_FIND_CERTIFICATE_TAG = "select certificate_id from CERTIFICATE_TAG where certificate_id = ? AND tag_id = ?";
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
    public GiftCertificate getById(long id) {
        List<GiftCertificate> query = jdbcTemplate.query(SQL_FIND_CERTIFICATE,
                new Object[]{id},
                new GiftCertificateMapper());
        GiftCertificate certificate = DataAccessUtils.uniqueResult(query);

        return certificate;
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
    public boolean update(GiftCertificate certificate) {
        boolean result = jdbcTemplate.update(SQL_UPDATE_CERTIFICATE,
                certificate.getName(),
                certificate.getDescription(),
                certificate.getPrice(),
                LocalDateTime.now(),
                certificate.getValidDays(),
                certificate.getId()) > 0;

        return result;
    }

    @Override
    public Long create(GiftCertificate certificate) {
    Long id = jdbcTemplate.queryForObject(SQL_INSERT_CERTIFICATE, new Object[] {
            certificate.getName(),
            certificate.getDescription(),
            certificate.getPrice(),
            LocalDateTime.now(),
            certificate.getValidDays()} , Long.class );

        return id;
    }

    @Override
    public boolean deleteById(long id)  {
        boolean result = jdbcTemplate.update(SQL_DELETE_CERTIFICATE, id) > 0;

        return result;
    }

    @Override
    public boolean createCertificateTag(long certificateId, long tagId) {
        boolean result = jdbcTemplate.update(SQL_CREATE_CERTIFICATE_TAG, certificateId, tagId) > 0;

        return result;
    }
    @Override
    public boolean isCertificateHasTag(long certficateId, long tagId) {
        Long certificateFound = jdbcTemplate.queryForObject(SQL_FIND_CERTIFICATE_TAG,
                new Object[]{certficateId, tagId}, Long.class);
        if(certificateFound != null) {
            return true;
        }
        return false;
    }
}
