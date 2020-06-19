package com.epam.mjc.dao.dao;

import com.epam.mjc.dao.mapper.TagMapper;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_TAG_BY_ID = "select * from tag where id = ?";
    private static final String SQL_GET_TAG_BY_NAME = "select * from tag where name = ?";
    private static final String SQL_DELETE_TAG = "delete from tag where id = ?";
    private static final String SQL_GET_ALL_TAGS = "select * from tag";
    private static final String SQL_CREATE_TAG = "insert into tag (name) values(?) RETURNING id";
    private static final String SQL_SELECT_TAGS_BY_CERTIFICATE_ID = "SELECT \n" +
            "tag.id,\n" +
            "tag.name\n" +
            "FROM tag \n" +
            "JOIN certificate_tag ON certificate_tag.tag_id = tag.id\n" +
            "where certificate_tag.certificate_id = ?";

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag getById(long id) {
        List<Tag> tag = jdbcTemplate.query(SQL_GET_TAG_BY_ID,
                new Object[]{id},
                new TagMapper());

        return DataAccessUtils.uniqueResult(tag);
    }

    @Override
    public List<Tag> getAllTagsByCertificateId(long id) {
        return jdbcTemplate.query(SQL_SELECT_TAGS_BY_CERTIFICATE_ID,
                new Object[]{id},
                new TagMapper());
    }

    @Override
    public Tag getByName(String name) {

    List<Tag> tag =  jdbcTemplate.query(SQL_GET_TAG_BY_NAME,
            new Object[]{name},
            new TagMapper());
    return DataAccessUtils.uniqueResult(tag);
}

    @Override
    public List<Tag> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_TAGS, new TagMapper());
    }

    @Override
    public Long create(Tag tag) throws DaoException {
        Long result = jdbcTemplate.queryForObject(SQL_CREATE_TAG, new Object[] {tag.getName()}, Long.class);
        if(result == null) {
            throw new DaoException("Impossible to create Certificate with given parameters");
        }
        return result;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result = jdbcTemplate.update(SQL_DELETE_TAG, id) > 0;
        if(!result) {
            throw new DaoException("Impossible to delete Certificate with given parameters");
        }
        return true;
    }
}
