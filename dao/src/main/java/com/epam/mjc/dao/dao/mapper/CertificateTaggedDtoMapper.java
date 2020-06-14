package com.epam.mjc.dao.dao.mapper;

import com.epam.mjc.dao.dto.CertificatedTaggedDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CertificateTaggedDtoMapper implements RowMapper<CertificatedTaggedDto> {

    @Override
    public CertificatedTaggedDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        CertificatedTaggedDto certificatedTaggedDto = new CertificatedTaggedDto();
        certificatedTaggedDto.setId(resultSet.getLong("id"));
        certificatedTaggedDto.setName(resultSet.getString("name"));
        certificatedTaggedDto.setDescription(resultSet.getString("description"));
        certificatedTaggedDto.setPrice(resultSet.getBigDecimal("price"));
        certificatedTaggedDto.setCreationDate(resultSet.getTimestamp("creation_date").toLocalDateTime());
        certificatedTaggedDto.setModificationDate(resultSet.getTimestamp("modification_date").toLocalDateTime());
        certificatedTaggedDto.setValidDays(resultSet.getInt("valid_days"));
        Array tagsArray = resultSet.getArray("tags");
        certificatedTaggedDto.setTags((String[])tagsArray.getArray());
        return certificatedTaggedDto;
    }

}
