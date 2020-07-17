package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.entity.OrderEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@EnableTransactionManagement
public class OrderDaoImpl implements OrderDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderEntity> getAllOrders() {
        return entityManager.createNamedQuery("Orders.findAll", OrderEntity.class).getResultList();
    }

    @Override
    public OrderEntity getOrderById(Long id) {
        List<OrderEntity> ordersList = entityManager
                .createNamedQuery("Orders.findById", OrderEntity.class)
                .setParameter("id", id)
                .getResultList();

        return ordersList.size() > 0 ? ordersList.get(0) : null;
    }

    @Override
    public Long createOrder(OrderEntity orderEntity) {
       entityManager.persist(orderEntity);
       entityManager.detach(orderEntity);
       return orderEntity.getId();
    }

    @Override
    public List<OrderEntity> getAllByUserId(Long userId) {

        return entityManager.createNamedQuery("Orders.findByUserId", OrderEntity.class).setParameter("userId", userId).getResultList();
    }
}
