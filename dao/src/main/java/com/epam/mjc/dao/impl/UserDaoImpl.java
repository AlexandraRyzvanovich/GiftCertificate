package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.entity.User;
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
    public Long createUser(User user) {
        entityManager.persist(user);

        return user.getId();
    }

    @Override
    @Transactional
    public User updateUser(User user) {

        return entityManager.merge(user);
    }
}
