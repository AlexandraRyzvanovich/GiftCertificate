package com.epam.mjc.dao;

import com.epam.mjc.dao.entity.RoleEntity;

public interface RoleDao {
    RoleEntity getRoleByName(String name);

}
