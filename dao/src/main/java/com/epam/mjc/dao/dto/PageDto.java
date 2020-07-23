package com.epam.mjc.dao.dto;

import com.epam.mjc.dao.entity.Identifiable;
import org.springframework.hateoas.CollectionModel;

import java.math.BigInteger;

public class PageDto<T extends Identifiable>  {
    CollectionModel<T> items;
    BigInteger size;

    public PageDto(CollectionModel<T> items, BigInteger size) {
        this.items = items;
        this.size = size;
    }

    public PageDto() {

    }

    public CollectionModel<T> getItems() {
        return items;
    }

    public void setItems(CollectionModel<T> items) {
        this.items = items;
    }

    public BigInteger getSize() {
        return size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }
}
