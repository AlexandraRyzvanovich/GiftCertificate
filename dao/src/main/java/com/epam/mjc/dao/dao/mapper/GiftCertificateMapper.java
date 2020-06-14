package com.epam.mjc.dao.dao.mapper;

import com.epam.mjc.dao.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiftCertificateMapper implements RowMapper<GiftCertificate> {
    @Override
    public GiftCertificate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setCertificate(resultSet.getLong("certificate_id"));
        giftCertificate.setCertificateTag(resultSet.getLong("tag_id"));

        return giftCertificate;
    }
}
