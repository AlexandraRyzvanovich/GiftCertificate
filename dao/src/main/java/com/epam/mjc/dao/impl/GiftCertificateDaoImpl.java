package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.builder.SqlStringBuilder;
import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String SQL_CREATE_CERTIFICATE_TAG = "insert into certificate_tag(certificate_id, tag_id) values(?,?)";
    private static final String SQL_SELECT_ALL = "SELECT " +
            "c.id, c.name, c.description," +
            "            c.price, c.creation_date, " +
            "            c.modification_date," +
            "            c.valid_days, " +
            "            c.isActive " +
            "FROM certificate c " +
            "LEFT  JOIN certificate_tag c_t ON c.id = c_t.certificate_id " +
            "LEFT  JOIN tag t ON t.id = c_t.tag_id ";

    @Autowired
    public GiftCertificateDaoImpl() {
    }

    @Override
    public GiftCertificate getById(long id) {

        return entityManager.createNamedQuery("Certificate.findById", GiftCertificate.class)
                .setParameter("id", id)
                .getSingleResult();

    }

    @Override
    public GiftCertificate getByName(String name) {

        return entityManager.createNamedQuery("Certificate.findByName", GiftCertificate.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GiftCertificate> getAll(SearchParams searchParams) {
        String sqlQueryPattern = SqlStringBuilder.buildQuery(searchParams);
        if (!StringUtils.isEmpty(sqlQueryPattern)) {
            return entityManager.createNativeQuery(SQL_SELECT_ALL.concat(sqlQueryPattern), GiftCertificate.class).getResultList();
        }
        return entityManager.createNativeQuery(SQL_SELECT_ALL, GiftCertificate.class).getResultList();
    }

    @Override
    public GiftCertificate update(GiftCertificate certificate) {

        return entityManager.merge(certificate);
    }

    @Override
    public Long create(GiftCertificate certificate) {
        entityManager.merge(certificate);

        return certificate.getId();
    }

//    @Override
//    public boolean createCertificateTag(long certificateId, long tagId) {
//
//        return jdbcTemplate.update(SQL_CREATE_CERTIFICATE_TAG, certificateId, tagId) > 0;
//    }
}
