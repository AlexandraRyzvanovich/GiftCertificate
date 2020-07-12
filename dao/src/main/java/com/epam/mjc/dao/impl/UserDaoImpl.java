package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public UserDaoImpl() {
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createNamedQuery("Users.findAll", User.class).getResultList();
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
        entityManager.detach(user);

        return user.getId();
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        entityManager.merge(user);
        entityManager.detach(user);
        return user;
    }
}
