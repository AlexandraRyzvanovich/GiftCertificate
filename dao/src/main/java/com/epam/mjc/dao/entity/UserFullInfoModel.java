package com.epam.mjc.dao.entity;

import java.util.List;

public class UserFullInfoModel {
    private User user;
    private List<Order> orders;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
