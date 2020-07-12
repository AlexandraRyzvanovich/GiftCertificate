package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.builder.SqlStringBuilder;
import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@EnableTransactionManagement
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String SQL_SELECT_ALL = "SELECT " +
            "c.id, c.name, c.description," +
            "            c.price, c.creation_date, " +
            "            c.modification_date," +
            "            c.valid_days, " +
            "            c.is_active " +
            "FROM certificate c " +
            "LEFT  JOIN certificate_tag c_t ON c.id = c_t.certificate_id " +
            "LEFT  JOIN tag t ON t.id = c_t.tag_id ";
    private static final String SQL_CREATE_CERTIFICATE_TAG = "insert into certificate_tag (certificate_id, tag_id) values (:certificate_id, :tag_id)";

    @Autowired
    public GiftCertificateDaoImpl() {
    }

    @Override
    public GiftCertificate getById(long id) {
        List<GiftCertificate> giftCertificateList = entityManager.createNamedQuery("Certificate.findById", GiftCertificate.class)
                .setParameter("id", id)
                .getResultList();
        return giftCertificateList.size() > 0 ? giftCertificateList.get(0) : null;
    }

    @Override
    public GiftCertificate getByName(String name) {
        List<GiftCertificate> giftCertificateList = entityManager.createNamedQuery("Certificate.findByName", GiftCertificate.class)
                .setParameter("name", name)
                .getResultList();
        return giftCertificateList.size() > 0 ? giftCertificateList.get(0) : null;
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
    @Transactional
    public Long create(GiftCertificate certificate) {
        entityManager.persist(certificate);
        entityManager.detach(certificate);

        return certificate.getId();
    }

    @Override
    public void createCertificateTag(Long certificateId, Long tagId) {
        entityManager.createNativeQuery(SQL_CREATE_CERTIFICATE_TAG).setParameter("certificate_id", certificateId).setParameter("tag_id", tagId).executeUpdate();
    }
}
