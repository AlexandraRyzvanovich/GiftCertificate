package com.epam.mjc.web;

import com.epam.mjc.service.exception.ServiceIncorrectParamsException;
import com.epam.mjc.service.exception.ServiceNotFoundException;
import com.epam.mjc.service.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GiftCertificateErrorAdvice {

        @ExceptionHandler({ServiceNotFoundException.class})
        public ErrorMessage handleNotFoundException(ServiceNotFoundException e) {
                return new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());

        }

        @ExceptionHandler({ValidationException.class})
        public ErrorMessage handleValidationException(ValidationException exception) {
                return new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

        @ExceptionHandler({ServiceIncorrectParamsException.class})
        public ErrorMessage handleIncorrectParamsException(ServiceIncorrectParamsException exception) {
                return new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        }


}
