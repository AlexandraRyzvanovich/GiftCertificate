package com.epam.mjc.dao.entity;

import com.epam.mjc.dao.entity.SortType;

public class SortParams {
    String fieldName;
    SortType sortType;

    public SortParams(String fieldName, SortType sortType) {
        this.fieldName = fieldName;
        this.sortType = sortType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }
}
