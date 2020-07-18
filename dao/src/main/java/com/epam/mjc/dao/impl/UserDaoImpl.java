package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.entity.UserEntity;
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
    public List<UserEntity> getAllUsers() {
        return entityManager.createNamedQuery("Users.findAll", UserEntity.class).getResultList();
    }

    @Override
    public UserEntity getUserById(Long id) {

        return entityManager.createNamedQuery("Users.findById", UserEntity.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    @Transactional
    public Long createUser(UserEntity userEntity) {
        entityManager.persist(userEntity);
        entityManager.detach(userEntity);

        return userEntity.getId();
    }

    @Override
    @Transactional
    public UserEntity updateUser(UserEntity userEntity) {
        entityManager.merge(userEntity);
        entityManager.detach(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return entityManager.createNamedQuery("Users.findByEmail", UserEntity.class).setParameter("email", email).getSingleResult();
    }

}
