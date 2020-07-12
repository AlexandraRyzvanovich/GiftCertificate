package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.Tag;

import java.util.List;

public interface TagDao {
    Tag getById(Long id);
    List<Tag> getAllTagsByCertificateId(Long id);
    Tag getByName (String name);
    List<Tag> getAll();
    Long create(Tag item);
    void deleteById(Long id);
    void deleteFromCertificateTagByIds(Long certificateId, Long tagId);
}
