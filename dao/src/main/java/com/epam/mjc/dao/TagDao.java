package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.TagEntity;

import java.util.List;

public interface TagDao {
    TagEntity getById(Long id);
    TagEntity getByName (String name);
    List<TagEntity> getAll(Integer size, Integer pageNumber);
    Long create(TagEntity item);
    void deleteById(Long id);
    List<TagEntity> getMostPopularAndExpensiveTag();
    int countTags();
}
