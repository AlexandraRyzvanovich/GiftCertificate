package com.epam.mjc.dao.dto;

import com.epam.mjc.dao.entity.Identifiable;
import org.springframework.hateoas.CollectionModel;

public class PageDto<T extends Identifiable>  {
    CollectionModel<T> items;
    int size;

    public PageDto(CollectionModel<T> items, int size) {
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

    public int getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
