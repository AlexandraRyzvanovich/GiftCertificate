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
    private static final String FIND_POPULAR_TAG = "SELECT id, name  FROM tag WHERE id in(\n" +
            "          SELECT ct.tag_id from certificate_tag ct \n" +
            "          join orders as oc on oc.certificate_id=ct.certificate_id \n" +
            "           WHERE oc.user_id =\n" +
            "          (SELECT u.id from users as u \n" +
            "          join orders as o on u.id = o.user_id \n" +
            "          group by u.id \n" +
            "          HAVING sum(o.amount) = \n" +
            "          (SELECT max(s) FROM \n" +
            "          (SELECT sum(o.amount) s from users as u \n" +
            "          join orders as o on u.id = o.user_id \n" +
            "          group by u.id) as tp LIMIT(1))) \n" +
            "          GROUP BY oc.user_id, ct.tag_id \n" +
            "          HAVING count(ct.tag_id) = (\n" +
            "          SELECT max(tag_count) FROM \n" +
            "          (SELECT ct.tag_id, count(ct.tag_id) tag_count from certificate_tag ct \n" +
            "          join orders as oc on oc.certificate_id=ct.certificate_id \n" +
            "          WHERE oc.user_id = \n" +
            "          (SELECT u.id from users as u \n" +
            "          join orders as o on u.id = o.user_id \n" +
            "          group by u.id \n" +
            "          HAVING sum(o.amount) = \n" +
            "          (SELECT max(s) FROM \n" +
            "          (SELECT sum(o.amount) s from users as u \n" +
            "          join orders as o on u.id = o.user_id \n" +
            "          group by u.id) as tp LIMIT(1))) \n" +
            "          GROUP BY ct.tag_id)\n" +
            "          AS max_tag ))";

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

        return userEntity.getId();
    }

    @Override
    @Transactional
    public UserEntity updateUser(UserEntity userEntity) {
        entityManager.merge(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return entityManager.createNamedQuery("Users.findByEmail", UserEntity.class).setParameter("email", email).getSingleResult();
    }

}
