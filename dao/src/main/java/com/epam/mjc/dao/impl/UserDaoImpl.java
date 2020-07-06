package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.entity.User;
import com.epam.mjc.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    private static final String SQL_GET_ALL_USERS = "select * from USERS";
    private static final String SQL_FIND_USER_BY_ID = "select * from USERS where id = ?";
    private static final String SQL_CREATE_USER = "insert into USERS(name, surname) values(?,?) RETURNING id";
    private static final String SQL_UPDATE_USER = "update USERS set name = ?, surname = ? where id = ?";

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_GET_ALL_USERS, new UserMapper());
    }

    @Override
    public User getUserById(Long id) {
        List<User> query = jdbcTemplate.query(SQL_FIND_USER_BY_ID,
                new Object[]{id},
                new UserMapper());

        return DataAccessUtils.uniqueResult(query);
    }

    @Override
    public Long createUser(User user) {
        return jdbcTemplate.queryForObject(SQL_CREATE_USER, new Object[]{
                user.getName(),
                user.getSurname()
                }, Long.class);
    }

    @Override
    public boolean updateUser(Long id, User user) {
        return jdbcTemplate.update(SQL_UPDATE_USER,
                user.getName(),
                user.getSurname(),
                id) > 0;
    }
}
