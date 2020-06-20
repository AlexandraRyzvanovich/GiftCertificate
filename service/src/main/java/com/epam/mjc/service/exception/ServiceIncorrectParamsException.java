package com.epam.mjc.service.exception;

public class ServiceIncorrectParamsException extends RuntimeException {
    public ServiceIncorrectParamsException(String message) {
        super(message);
    }
}
