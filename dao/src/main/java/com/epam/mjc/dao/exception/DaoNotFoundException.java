package com.epam.mjc.dao.exception;

public class DaoNotFoundException extends Exception {
    public DaoNotFoundException(String message) {
        super(message);
    }

    public DaoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
