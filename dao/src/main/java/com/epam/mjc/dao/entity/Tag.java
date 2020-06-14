package com.epam.mjc.dao.entity;

public class Tag implements Identifiable{
    private Long id;
    private String tagName;

    public Tag() {
    }

    public Tag(Long id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
