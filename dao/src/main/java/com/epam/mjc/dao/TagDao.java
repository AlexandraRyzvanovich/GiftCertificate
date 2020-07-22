package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.TagEntity;

import java.util.List;

public interface TagDao {
    TagEntity getById(Long id);
    List<TagEntity> getAllTagsByCertificateId(Long id);
    TagEntity getByName (String name);
    List<TagEntity> getAll();
    Long create(TagEntity item);
    void deleteById(Long id);
    void deleteFromCertificateTagByIds(Long certificateId, Long tagId);
    List<TagEntity> getMostPopularAndExpensiveTag();
}
