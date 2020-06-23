package com.epam.mjc.dao.entity;

public class CertificateTag {
    private long certificateId;
    private long tagId;

    public CertificateTag() {
    }

    public CertificateTag(long certificateId, long tagId) {
        this.certificateId = certificateId;
        this.tagId = tagId;
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


