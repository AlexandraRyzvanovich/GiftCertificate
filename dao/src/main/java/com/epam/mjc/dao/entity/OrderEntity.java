package com.epam.mjc.dao.entity;

import com.epam.mjc.dao.AuditListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditListener.class)
@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "Orders.findAll", query = "select o from OrderEntity o"),
        @NamedQuery(name = "Orders.findById", query = "select distinct o from OrderEntity o where o.id = :id"),
})
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "certificate_id", insertable = false, updatable = false)
    private Long certificateId;

    public OrderEntity() {
    }

    public OrderEntity(Long id, Long userId, LocalDateTime date, BigDecimal amount, Long certificateId) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.amount = amount;
        this.certificateId = certificateId;
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

    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity orderEntity = (OrderEntity) o;
        return Objects.equals(id, orderEntity.id) &&
                Objects.equals(userId, orderEntity.userId) &&
                Objects.equals(date, orderEntity.date) &&
                Objects.equals(amount, orderEntity.amount) &&
                Objects.equals(certificateId, orderEntity.certificateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, date, amount, certificateId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", amount=" + amount +
                ", certificateId=" + certificateId +
                '}';
    }
}
