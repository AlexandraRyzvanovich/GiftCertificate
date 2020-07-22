package com.epam.mjc.dao.dto;

import com.epam.mjc.dao.entity.Identifiable;

import java.math.BigInteger;
import java.util.List;

public class PageDto<T extends Identifiable>  {
    List<T> items;
    BigInteger size;

    public PageDto(List<T> items, BigInteger size) {
        this.items = items;
        this.size = size;
    }

    public PageDto() {

    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public BigInteger getSize() {
        return size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }
}
