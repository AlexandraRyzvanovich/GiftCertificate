package com.epam.mjc.web.controller;

import com.epam.mjc.service.exception.IncorrectParamsException;
import com.epam.mjc.service.exception.NotFoundException;
import com.epam.mjc.service.exception.ValidationException;
import com.epam.mjc.web.erroradvice.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ErrorAdvice {
@       ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler({NotFoundException.class})
        public ErrorMessage handleNotFoundException(NotFoundException e) {
                return new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        }

        @ExceptionHandler({ValidationException.class})
        public ErrorMessage handleValidationException(ValidationException exception) {
                return new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

        @ExceptionHandler({IncorrectParamsException.class})
        public ErrorMessage handleIncorrectParamsException(IncorrectParamsException exception) {
                return new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        }
        @ExceptionHandler({Exception.class})
        public ErrorMessage handleException(Exception exception) {
                return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
}
