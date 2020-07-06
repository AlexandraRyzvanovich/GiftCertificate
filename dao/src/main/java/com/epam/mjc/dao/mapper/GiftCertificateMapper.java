package com.epam.mjc.dao.mapper;

import com.epam.mjc.dao.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class GiftCertificateMapper implements RowMapper<GiftCertificate> {

    @Override
    public GiftCertificate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(resultSet.getLong("id"));
        giftCertificate.setName(resultSet.getString("name"));
        giftCertificate.setDescription(resultSet.getString("description"));
        giftCertificate.setPrice(resultSet.getBigDecimal("price"));
        giftCertificate.setCreationDate(resultSet.getTimestamp("creation_date").toLocalDateTime());
        Timestamp date =  resultSet.getTimestamp("modification_date");
        if(date!= null) {
            giftCertificate.setModificationDate(date.toLocalDateTime());
        }
        giftCertificate.setValidDays(resultSet.getInt("valid_days"));
        giftCertificate.setActive(resultSet.getBoolean("isActive"));

        return giftCertificate;
    }
}
