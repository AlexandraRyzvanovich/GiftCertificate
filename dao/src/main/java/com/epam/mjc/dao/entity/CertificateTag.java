package com.epam.mjc.dao.entity;

public class CertificateTag {
    private long id;
    private long certificateId;
    private long tagId;

    public CertificateTag() {
    }

    public CertificateTag(long id, long certificateId, long tagId) {
        this.id = id;
        this.certificateId = certificateId;
        this.tagId = tagId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(long certificateId) {
        this.certificateId = certificateId;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }
}
