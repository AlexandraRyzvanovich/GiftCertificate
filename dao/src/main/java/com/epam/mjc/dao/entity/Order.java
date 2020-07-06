package com.epam.mjc.dao.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private Long id;
    private Long userId;
    private LocalDateTime date;
    private BigDecimal amount;
    private GiftCertificate certificate;

    public Order() {
    }

    public Order(Long id, Long userId, LocalDateTime date, BigDecimal amount, GiftCertificate certificate) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.amount = amount;
        this.certificate = certificate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public GiftCertificate getCertificate() {
        return certificate;
    }

    public void setCertificate(GiftCertificate certificate) {
        this.certificate = certificate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(date, order.date) &&
                Objects.equals(amount, order.amount) &&
                Objects.equals(certificate, order.certificate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, date, amount, certificate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", amount=" + amount +
                ", certificate=" + certificate +
                '}';
    }
}
