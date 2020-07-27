package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.builder.SqlStringBuilder;
import com.epam.mjc.dao.entity.OrderEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
@EnableTransactionManagement
public class OrderDaoImpl implements OrderDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final String QUERY_GET_ALL_BY_USER_ID = "Select * from orders " +
            "Where user_Id = :userId";
    private static final String QUERY_COUNT_ORDERS = "SELECT COUNT(id)\n" +
            "FROM orders " +
            "where user_id = :user_id";

    @Override
    public Optional<OrderEntity> getOrderById(Long id) {
        List<OrderEntity> ordersList = entityManager
                .createNamedQuery("Orders.findById", OrderEntity.class)
                .setParameter("id", id)
                .getResultList();

        return ordersList.stream().findFirst();
    }

    @Override
    public Long createOrder(OrderEntity orderEntity) {
       entityManager.persist(orderEntity);

       return orderEntity.getId();
    }

    @Override
    public int ordersSize(Long userId) {
        BigInteger count = (BigInteger) entityManager.createNativeQuery(QUERY_COUNT_ORDERS).setParameter("user_id", userId).getSingleResult();
        return count.intValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<OrderEntity> getAllByUserId(Long userId, Integer size, Integer pageNumber) {
        String paginationQuery = SqlStringBuilder.paginationBuilder(size, pageNumber);

        return entityManager.createNativeQuery(QUERY_GET_ALL_BY_USER_ID.concat(paginationQuery), OrderEntity.class).setParameter("userId", userId).getResultList();
    }
}
