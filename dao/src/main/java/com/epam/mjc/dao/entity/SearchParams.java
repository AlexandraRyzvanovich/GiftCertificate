package com.epam.mjc.dao.entity;

import java.util.List;

public class SearchParams {
    private List<String> tags;
    private String text;
    private SortParams sortParams;

    public SearchParams(List<String> tags, String text, SortParams sortType) {
        this.tags = tags;
        this.text = text;
        this.sortParams = sortType;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SortParams getSortParams() {
        return sortParams;
    }

    public void setSortParams(SortParams sortParams) {
        this.sortParams = sortParams;
    }
}
