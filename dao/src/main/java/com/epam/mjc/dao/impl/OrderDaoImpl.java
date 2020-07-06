package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.OrderDao;
import com.epam.mjc.dao.entity.Order;
import com.epam.mjc.dao.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ORDER_BY_ID = "select * from ORDERS where id = ?";
    private static final String SQL_GET_ORDER_BY_USER_ID = "select * from ORDERS where user_id = ?";
    private static final String SQL_GET_ALL_ORDERS = "select * from ORDERS";
    private static final String SQL_CREATE_ORDER = "insert into ORDERS(user_id, date, amount, certificate_id) values(?,?,?,?) RETURNING id";

    @Autowired
    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        List<Order> query = jdbcTemplate.query(SQL_GET_ORDER_BY_ID,
                new Object[]{id},
                new OrderMapper());

        return DataAccessUtils.uniqueResult(query);
    }

    @Override
    public Long createOrder(Order order) {
        return jdbcTemplate.queryForObject(SQL_CREATE_ORDER, new Object[]{
                order.getUserId(),
                LocalDateTime.now(),
                order.getAmount(),
                order.getCertificate().getId()
        }, Long.class);
    }

    @Override
    public List<Order> getAllByUserId(Long userId) {

        return jdbcTemplate.query(SQL_GET_ORDER_BY_USER_ID,
                new Object[]{userId},
                new OrderMapper());
    }
}
