package com.epam.mjc.dao.entity;

public enum UserRoleEnum {
    GUEST("GUEST"),
    USER("USER"),
    ADMINISTRATOR("ADMINISTRATOR");

    UserRoleEnum() {
    }

    UserRoleEnum(String value) {
        UserRoleEnum.valueOf(value);
    }
}
