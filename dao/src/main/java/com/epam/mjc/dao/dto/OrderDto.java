package com.epam.mjc.dao.dto;

import com.epam.mjc.dao.entity.GiftCertificateEntity;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDto {
    private Long id;
    @NotNull
    private Long userId;
    private LocalDateTime date;
    private BigDecimal amount;
    @NotNull(message = "Certificate_id is required")
    private Long certificateId;
    private GiftCertificateEntity certificate;

    public OrderDto() {
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

    public GiftCertificateEntity getCertificate() {
        return certificate;
    }

    public void setCertificate(GiftCertificateEntity certificate) {
        this.certificate = certificate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) &&
                Objects.equals(userId, orderDto.userId) &&
                Objects.equals(date, orderDto.date) &&
                Objects.equals(amount, orderDto.amount) &&
                Objects.equals(certificateId, orderDto.certificateId) &&
                Objects.equals(certificate, orderDto.certificate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, date, amount, certificateId, certificate);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", certificate=" + certificate +
                '}';
    }
}