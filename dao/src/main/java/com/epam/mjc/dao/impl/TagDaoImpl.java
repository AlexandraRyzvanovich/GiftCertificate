package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.TagDao;
import com.epam.mjc.dao.entity.TagEntity;
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
    public TagEntity getById(Long id) {
        List<TagEntity> tagEntityList = entityManager.createNamedQuery("Tags.findById", TagEntity.class).setParameter("id", id).getResultList();
        return tagEntityList.size() > 0 ? tagEntityList.get(0) : null;
    }

    @Override
    public List getAllTagsByCertificateId(Long id) {

        return entityManager.createNativeQuery(SQL_SELECT_TAGS_BY_CERTIFICATE_ID, TagEntity.class).setParameter("id", id).getResultList();
    }

    @Override
    public TagEntity getByName(String name) {
        List<TagEntity> tagEntities = entityManager.createNamedQuery("Tags.getByName", TagEntity.class).setParameter("name", name).getResultList();
        return tagEntities.size() > 0 ? tagEntities.get(0) : null;
    }

    @Override
    public List<TagEntity> getAll() {
        return entityManager.createNamedQuery("Tags.findAll", TagEntity.class).getResultList();
    }

    @Override
    @Transactional
    public Long create(TagEntity tagEntity) {
        entityManager.persist(tagEntity);
        entityManager.detach(tagEntity);

        return tagEntity.getId();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createNamedQuery("Tags.deleteById", TagEntity.class).setParameter("id", id).executeUpdate();
    }

    @Override
    public void deleteFromCertificateTagByIds(Long certificateId, Long tagId) {
        entityManager.createQuery(SQL_DELETE_FROM_CERTIFICATE_TAG).setParameter("certificate_id", certificateId).setParameter("tag_id", tagId).executeUpdate();
    }
}















