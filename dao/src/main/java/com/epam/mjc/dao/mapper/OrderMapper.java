package com.epam.mjc.dao.mapper;

import com.epam.mjc.dao.entity.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("id"));
        order.setUserId(resultSet.getLong("user_id"));
        order.setDate(resultSet.getTimestamp("date").toLocalDateTime());
        order.setAmount(resultSet.getBigDecimal("amount"));
        return order;
    }
}
