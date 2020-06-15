package com.epam.mjc.dao.dto;

import com.epam.mjc.dao.entity.Identifiable;
import com.epam.mjc.dao.entity.Tag;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CertificatedTaggedDto implements Identifiable {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private Integer validDays;
    List<Tag> tags;

    public CertificatedTaggedDto() {
    }

    public CertificatedTaggedDto(Long id, String name, String description, BigDecimal price, LocalDateTime creationDate, LocalDateTime modificationDate, Integer validDays, List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.validDays = validDays;
        this.tags = tags;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
