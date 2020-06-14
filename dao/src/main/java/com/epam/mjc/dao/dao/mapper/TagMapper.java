package com.epam.mjc.dao.dao.mapper;

import com.epam.mjc.dao.entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Tag tag = new Tag();
        tag.setId(resultSet.getLong("id"));
        tag.setTagName(resultSet.getString("tagName"));

        return tag;
    }
}
