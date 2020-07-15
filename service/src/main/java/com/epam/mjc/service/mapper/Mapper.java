package com.epam.mjc.service.mapper;

import com.epam.mjc.dao.entity.Identifiable;

public interface Mapper<E extends Identifiable, D extends Identifiable> {
    Identifiable toEntity(D dto);
    Identifiable toDto(E entity);
}
