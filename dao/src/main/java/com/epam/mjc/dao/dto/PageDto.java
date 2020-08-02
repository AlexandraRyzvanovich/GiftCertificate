package com.epam.mjc.dao.dto;

import com.epam.mjc.dao.entity.Identifiable;
import org.springframework.hateoas.CollectionModel;

public class PageDto<T extends Identifiable>  {
    private CollectionModel<T> items;
    private int size;
    private int currentPage;
    private int pageCount;

    public PageDto(CollectionModel<T> items, int size, int currentPage, int pageCount) {
        this.items = items;
        this.size = size;
        this.currentPage = currentPage;
        this.pageCount = pageCount;
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

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageCount(int size, int pageSize) {
        double page = Math.ceil(size/pageSize);
        return (int)page;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
