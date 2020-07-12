package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.TagDao;
import com.epam.mjc.dao.entity.Tag;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@EnableTransactionManagement
public class TagDaoImpl implements TagDao {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String SQL_DELETE_FROM_CERTIFICATE_TAG = "delete from certificate_tag where certificate_id = ? AND  tag_id = ?";
    private static final String SQL_SELECT_TAGS_BY_CERTIFICATE_ID = "SELECT \n" +
            "tag.id,\n" +
            "tag.name\n" +
            "FROM tag \n" +
            "JOIN certificate_tag ON certificate_tag.tag_id = tag.id\n" +
            "where certificate_tag.certificate_id = :id";

    @Override
    public Tag getById(Long id) {
        List<Tag> tagList = entityManager.createNamedQuery("Tags.findById", Tag.class).setParameter("id", id).getResultList();
        return tagList.size() > 0 ? tagList.get(0) : null;
    }

    @Override
    public List getAllTagsByCertificateId(Long id) {

        return entityManager.createNativeQuery(SQL_SELECT_TAGS_BY_CERTIFICATE_ID, Tag.class).setParameter("id", id).getResultList();
    }

    @Override
    public Tag getByName(String name) {
        List<Tag> tags = entityManager.createNamedQuery("Tags.getByName", Tag.class).setParameter("name", name).getResultList();
        return tags.size() > 0 ? tags.get(0) : null;
    }

    @Override
    public List<Tag> getAll() {
        return entityManager.createNamedQuery("Tags.findAll", Tag.class).getResultList();
    }

    @Override
    @Transactional
    public Long create(Tag tag) {
        entityManager.persist(tag);
        entityManager.detach(tag);

        return tag.getId();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createNamedQuery("Tags.deleteById", Tag.class).setParameter("id", id).executeUpdate();
    }

    @Override
    public void deleteFromCertificateTagByIds(Long certificateId, Long tagId) {
        entityManager.createQuery(SQL_DELETE_FROM_CERTIFICATE_TAG).setParameter("certificate_id", certificateId).setParameter("tag_id", tagId).executeUpdate();
    }
}















