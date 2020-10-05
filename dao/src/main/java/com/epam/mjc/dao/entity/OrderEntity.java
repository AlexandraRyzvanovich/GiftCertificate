package com.epam.mjc.dao.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@EntityListeners(AuditListener.class)
@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "Orders.findAll", query = "select o from OrderEntity o"),
        @NamedQuery(name = "Orders.findById", query = "select o from OrderEntity o where o.id = :id"),
        @NamedQuery(name = "Orders.findByUserId", query = "select o from OrderEntity o where o.userId = :userId"),
})
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "user_id", updatable = false)
    private Long userId;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_certificates",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "certificate_id")}
    )
    private List<GiftCertificateEntity> certificates;
    public OrderEntity() {
    }

    public OrderEntity(Long id, Long userId, LocalDateTime date, BigDecimal amount, List<GiftCertificateEntity> certificates) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.amount = amount;
        this.certificates = certificates;
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

    public List<GiftCertificateEntity> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<GiftCertificateEntity> certificates) {
        this.certificates = certificates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(certificates, that.certificates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, date, amount, certificates);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", amount=" + amount +
                ", certificates=" + certificates +
                '}';
    }
}
