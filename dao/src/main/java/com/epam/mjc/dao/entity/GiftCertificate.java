package com.epam.mjc.dao.entity;

public class GiftCertificate implements Identifiable {
    private Long certificate;
    private Long certificateTag;

    public GiftCertificate() {
    }

    public GiftCertificate(Long certificate, Long certificateTag) {
        this.certificate = certificate;
        this.certificateTag = certificateTag;
    }

    public Long getCertificate() {
        return certificate;
    }

    public void setCertificate(Long certificate) {
        this.certificate = certificate;
    }

    public Long getCertificateTag() {
        return certificateTag;
    }

    public void setCertificateTag(Long certificateTag) {
        this.certificateTag = certificateTag;
    }
}
