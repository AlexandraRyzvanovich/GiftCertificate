package com.epam.mjc.dao.dao.mapper;

import com.epam.mjc.dao.entity.Certificate;
import com.epam.mjc.dao.entity.Identifiable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CertificateMapper implements RowMapper<Certificate> {

    @Override
    public Certificate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Certificate certificate = new Certificate();
        certificate.setId(resultSet.getLong("id"));
        certificate.setName(resultSet.getString("name"));
        certificate.setDescription(resultSet.getString("description"));
        certificate.setPrice(resultSet.getBigDecimal("price"));
        certificate.setCreationDate(resultSet.getTimestamp("creation_date").toLocalDateTime());
        Timestamp modificationDate =  resultSet.getTimestamp("modification_date");
        if(modificationDate != null ) {
            certificate.setModificationDate(modificationDate.toLocalDateTime());
        }
        certificate.setValidDays(resultSet.getInt("valid_days"));

        return certificate;
    }
}
