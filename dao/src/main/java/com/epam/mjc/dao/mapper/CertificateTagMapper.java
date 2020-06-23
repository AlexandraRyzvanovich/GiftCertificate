package com.epam.mjc.dao.mapper;

import com.epam.mjc.dao.entity.CertificateTag;
import com.epam.mjc.dao.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CertificateTagMapper implements RowMapper<CertificateTag> {

    @Override
    public CertificateTag mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        CertificateTag certificateTag = new CertificateTag();
        certificateTag.setId(resultSet.getLong("id"));
        certificateTag.setCertificateId(resultSet.getLong("certificate_id"));
        certificateTag.setTagId(resultSet.getLong("tag_id"));


        return certificateTag;
    }
}

