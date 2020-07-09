package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@EnableTransactionManagement
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    private final JdbcTemplate jdbcTemplate;
    private static final String SQL_UPDATE_USER = "update USERS set name = ?, surname = ? where id = ?";

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = entityManager.createNamedQuery("Users.findAll", User.class).getResultList();

        return users;
    }

    @Override
    public User getUserById(Long id) {

        return entityManager.createNamedQuery("Users.findById", User.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    @Transactional
    public Long createUser(User user ) {
        entityManager.persist(user);
        return user.getId();
    }

    @Override
    public boolean updateUser(Long id, User user) {
        entityManager.merge(user);
        return true;
    }
}
