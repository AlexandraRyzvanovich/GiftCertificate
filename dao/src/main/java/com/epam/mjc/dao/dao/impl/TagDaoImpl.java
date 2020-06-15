package com.epam.mjc.dao.dao.impl;

import com.epam.mjc.dao.dao.TagDao;
import com.epam.mjc.dao.dao.mapper.TagMapper;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TagDaoImpl implements TagDao {
    private JdbcTemplate jdbcTemplate;

    private final String SQL_GET_TAG_BY_ID = "select * from tag where id = ?";
    private final String SQL_DELETE_TAG = "delete from tag where id = ?";
    private final String SQL_UPDATE_TAG = "update tag set tag_name = ? where id = ?";
    private final String SQL_GET_ALL_TAGS = "select * from tag";
    private final String SQL_CREATE_TAG = "insert into tag (name) value(?)";

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Tag> getById(long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_GET_TAG_BY_ID,
                new Object[]{id},
                new TagMapper()));
    }

    @Override
    public Optional<Tag> getByName(String name) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_GET_TAG_BY_ID,
                new Object[]{name},
                new TagMapper()));
    }

    @Override
    public List<Tag> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_TAGS, new TagMapper());
    }

    @Override
    public Tag update(Tag tag) throws DaoException {
        boolean result = jdbcTemplate.update(SQL_UPDATE_TAG, tag.getTagName(),
                tag.getId()) > 0;
        if(result != true) {
            throw new DaoException ("Impossible to update Certificate with given parameters");
        }
        return tag;
    }

    @Override
    public Tag create(Tag tag) throws DaoException {
        boolean result = jdbcTemplate.update(SQL_CREATE_TAG, tag.getTagName()) > 0;
        if(result != true) {
            throw new DaoException("Impossible to create Certificate with given parameters");
        }
        return tag;
    }

    @Override
    public boolean delete(Tag tag) throws DaoException {
        boolean result = jdbcTemplate.update(SQL_DELETE_TAG, tag.getId()) > 0;
        if(result != true) {
            throw new DaoException("Impossible to delete Certificate with given parameters");
        }
        return true;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result = jdbcTemplate.update(SQL_DELETE_TAG, id) > 0;
        if(result != true) {
            throw new DaoException("Impossible to delete Certificate with given parameters");
        }
        return true;
    }
}
