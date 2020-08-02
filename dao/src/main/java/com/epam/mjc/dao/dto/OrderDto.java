package com.epam.mjc.dao.dto;

import com.epam.mjc.dao.entity.Identifiable;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDto extends RepresentationModel<OrderDto> implements Identifiable {
    private Long id;
    @NotNull
    private Long userId;
    private LocalDateTime date;
    private BigDecimal amount;
    @NotNull(message = "Certificate_id is required")
    private Long certificateId;
    private GiftCertificateDto certificate;

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

    public GiftCertificateDto getCertificate() {
        return certificate;
    }

    public void setCertificate(GiftCertificateDto certificate) {
        this.certificate = certificate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
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
        return Objects.hash(super.hashCode(), id, userId, date, amount, certificateId, certificate);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", amount=" + amount +
                ", certificateId=" + certificateId +
                ", certificate=" + certificate +
                '}';
    }
}
