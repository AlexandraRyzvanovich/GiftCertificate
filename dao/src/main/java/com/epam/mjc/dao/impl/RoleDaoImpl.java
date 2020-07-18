package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.RoleDao;
import com.epam.mjc.dao.entity.RoleEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@EnableTransactionManagement
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public RoleEntity getRoleByName(String name) {
        return entityManager.createNamedQuery("Roles.findByName", RoleEntity.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
