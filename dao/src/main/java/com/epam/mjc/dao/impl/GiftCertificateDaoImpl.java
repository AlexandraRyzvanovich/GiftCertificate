package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.builder.SqlStringBuilder;
import com.epam.mjc.dao.entity.GiftCertificateEntity;
import com.epam.mjc.dao.entity.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
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
    private static final String SQL_COUNT_CERTIFICATES = "Select COUNT(c.id) " +
            "FROM certificate c " +
            "LEFT  JOIN certificate_tag c_t ON c.id = c_t.certificate_id " +
            "LEFT  JOIN tag t ON t.id = c_t.tag_id ";

    @Autowired
    public GiftCertificateDaoImpl() {
    }

    @Override
    public GiftCertificateEntity getById(long id) {
        List<GiftCertificateEntity> giftCertificateEntityList = entityManager.createNamedQuery("Certificate.findById", GiftCertificateEntity.class)
                .setParameter("id", id)
                .getResultList();
        return giftCertificateEntityList.size() > 0 ? giftCertificateEntityList.get(0) : null;
    }

    @Override
    public GiftCertificateEntity getByName(String name) {
        List<GiftCertificateEntity> giftCertificateEntityList = entityManager.createNamedQuery("Certificate.findByName", GiftCertificateEntity.class)
                .setParameter("name", name)
                .getResultList();
        return giftCertificateEntityList.size() > 0 ? giftCertificateEntityList.get(0) : null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GiftCertificateEntity> getAll(SearchParams searchParams, Integer size, Integer pageNumber) {
        String sqlQueryPattern = SqlStringBuilder.buildQuery(searchParams, size, pageNumber);
        if (!StringUtils.isEmpty(sqlQueryPattern)) {
            return entityManager.createNativeQuery(SQL_SELECT_ALL.concat(sqlQueryPattern), GiftCertificateEntity.class).getResultList();
        }
        return entityManager.createNativeQuery(SQL_SELECT_ALL, GiftCertificateEntity.class).getResultList();
    }

    @Override
    public GiftCertificateEntity update(GiftCertificateEntity certificate) {

        return entityManager.merge(certificate);
    }

    @Override
    @Transactional
    public Long create(GiftCertificateEntity certificate) {
        entityManager.persist(certificate);

        return certificate.getId();
    }

    @SuppressWarnings("unchecked")
    @Override
    public int countCertificates(SearchParams searchParams) {
        String sqlQueryPattern = SqlStringBuilder.buildQuery(searchParams, null, null);
        String query = SQL_COUNT_CERTIFICATES;
        if (!StringUtils.isEmpty(sqlQueryPattern)) {
            query = query.concat(sqlQueryPattern);
        }
        List<BigInteger> result = entityManager.createNativeQuery(query).getResultList();

        return result.size() == 0 ? 0 : result.get(0).intValue();
    }
}
