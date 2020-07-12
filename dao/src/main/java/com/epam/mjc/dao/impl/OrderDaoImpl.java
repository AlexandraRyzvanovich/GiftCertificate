package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.entity.Order;
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
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        List<Order> ordersList = entityManager.createNamedQuery("Orders.findById", Order.class).getResultList();

        return ordersList.size() > 0 ? ordersList.get(0) : null;
    }

    @Override
    public Long createOrder(Order order) {
       entityManager.persist(order);
       entityManager.detach(order);
       return order.getId();
    }

    @Override
    public List<Order> getAllByUserId(Long userId) {

        return entityManager.createNamedQuery("Orders.findAll", Order.class).getResultList();
    }
}
