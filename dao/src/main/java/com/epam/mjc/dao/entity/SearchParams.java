package com.epam.mjc.dao.entity;

import java.time.LocalDateTime;
import java.util.List;

public class SearchParams {
    private List<String> tags;
    private String text;
    private SorterParams sorterParams;

    public SearchParams(List<String> tags, String text, SorterParams sortType) {
        this.tags = tags;
        this.text = text;
        this.sorterParams = sortType;
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

    public SorterParams getSorterParams() {
        return sorterParams;
    }

    public void setSorterParams(SorterParams sorterParams) {
        this.sorterParams = sorterParams;
    }
}
