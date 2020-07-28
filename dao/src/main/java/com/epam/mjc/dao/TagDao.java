package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.TagEntity;

import java.util.List;
import java.util.Optional;

public interface TagDao {
    Optional<TagEntity> getById(Long id);
    Optional<TagEntity> getByName (String name);
    List<TagEntity> getAll(Integer size, Integer pageNumber);
    Long create(TagEntity item);
    void deleteById(Long id);
    List<TagEntity> getMostPopularAndExpensiveTag();
    int countTags();
}
