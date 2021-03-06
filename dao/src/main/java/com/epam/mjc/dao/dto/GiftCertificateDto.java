package com.epam.mjc.dao.dto;

import com.epam.mjc.dao.entity.Identifiable;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class GiftCertificateDto extends RepresentationModel<GiftCertificateDto> implements Identifiable {
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 1,max = 200, message = "Max size 200 characters")
    private String description;
    @DecimalMin(value = "0", message = "Price min value = 0")
    @DecimalMax(value = "10000", message = "Price max value = 10000")
    private BigDecimal price;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

    @DecimalMin(value = "1", message = "Min value = 1$")
    @DecimalMax(value = "365", message = "Max value = 365")
    private Integer validDays;
    private List<TagDto> tags;

    public GiftCertificateDto() {
    }


    public GiftCertificateDto(Long id, @NotNull @Size(min = 1, max = 50) String name, @Size(min = 1, max = 200, message = "Max size 200 characters") String description, @DecimalMin(value = "0", message = "Price min value = 0") @DecimalMax(value = "10000", message = "Price max value = 10000") BigDecimal price, LocalDateTime creationDate, @DecimalMin(value = "1", message = "Min value = 1$") @DecimalMax(value = "365", message = "Max value = 365") Integer validDays) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.validDays = validDays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tagEntities) {
        this.tags = tagEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftCertificateDto that = (GiftCertificateDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(modificationDate, that.modificationDate) &&
                Objects.equals(validDays, that.validDays) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, creationDate, modificationDate, validDays, tags);
    }

    @Override
    public String toString() {
        return "GiftCertificateDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                ", validDays=" + validDays +
                ", tags=" + tags +
                '}';
    }
}
