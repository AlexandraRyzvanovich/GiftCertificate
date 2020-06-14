package com.epam.mjc.dao.dao.impl;

import com.epam.mjc.dao.dao.Dao;
import com.epam.mjc.dao.dao.mapper.TagMapper;
import com.epam.mjc.dao.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CertificateTagDaoImpl implements Dao<Tag> {
    private JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_CERTIFICATE_TAG = "select * from certificate_tag where id = ?";
    private final String SQL_DELETE_CERTIFICATE_TAG = "delete from certificate_tag where id = ?";
    private final String SQL_UPDATE_CERTIFICATE_TAG = "update certificate_tag set tag_name = ? where id = ?";
    private final String SQL_GET_ALL_TAGS = "select * from certificate_tag";
    private final String SQL_INSERT_CERTIFICATE_TAG = "insert into certificate_tag(id, name, description, price, creation_date, modification_date, valid_days ) values(?,?,?,?,?,?,?)";

    @Autowired
    public CertificateTagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Tag> getById(long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_CERTIFICATE_TAG,
                new Object[]{id},
                new TagMapper()));
    }

    @Override
    public List<Tag> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_TAGS, new TagMapper());
    }

    @Override
    public boolean update(Tag tag) {
        return jdbcTemplate.update(SQL_UPDATE_CERTIFICATE_TAG, tag.getTagName(),
                tag.getId()) > 0;
    }

    @Override
    public boolean create(Tag tag) {
        return jdbcTemplate.update(SQL_INSERT_CERTIFICATE_TAG, tag.getTagName()) > 0;
    }

    @Override
    public boolean delete(Tag tag) {
        return jdbcTemplate.update(SQL_DELETE_CERTIFICATE_TAG, tag.getId()) > 0;
    }
}
