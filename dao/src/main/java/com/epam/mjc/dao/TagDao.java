package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.Tag;

import java.util.List;

public interface TagDao {
    Tag getById(long id);
    List<Tag> getAllTagsByCertificateId(long id);
    Tag getByName (String name);
    List<Tag> getAll();
    Long create(Tag item);
    boolean deleteById(long id);
    boolean deleteFromCertificateTag(Long id, Long id1);
}
