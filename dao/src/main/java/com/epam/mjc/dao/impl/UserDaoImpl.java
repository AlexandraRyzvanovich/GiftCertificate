package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.UserDao;
import com.epam.mjc.dao.builder.SqlStringBuilder;
import com.epam.mjc.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
@EnableTransactionManagement
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final String QUERY_FIND_ALL = "SELECT * FROM Users";
    private static final String QUERY_COUNT_USERS = "SELECT COUNT(id)\n" +
            "FROM users ";


    @Autowired
    public UserDaoImpl() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserEntity> getAllUsers(Integer size, Integer pageNumber) {
        String sqlQueryPattern = SqlStringBuilder.paginationBuilder(size, pageNumber);
        return entityManager.createNativeQuery(QUERY_FIND_ALL.concat(sqlQueryPattern), UserEntity.class).getResultList();
    }

    @Override
    public int usersTableSize() {
        BigInteger count = (BigInteger) entityManager.createNativeQuery(QUERY_COUNT_USERS).getSingleResult();
        return count.intValue();
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        List<UserEntity> user = entityManager.createNamedQuery("Users.findById", UserEntity.class)
                .setParameter("id", id).getResultList();
        return user.stream().findFirst();
    }

    @Override
    @Transactional
    public Long createUser(UserEntity userEntity) {
        entityManager.persist(userEntity);

        return userEntity.getId();
    }

    @Override
    @Transactional
    public UserEntity updateUser(UserEntity userEntity) {
        entityManager.merge(userEntity);
        return userEntity;
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        List<UserEntity> user = entityManager.createNamedQuery("Users.findByEmail", UserEntity.class).setParameter("email", email).getResultList();
        return user.stream().findFirst();
    }

}
