package com.epam.mjc.web;

import com.epam.mjc.dao.exception.DaoException;
import com.epam.mjc.service.exception.ServiceException;
import com.epam.mjc.service.exception.ValidatorException;
import com.epam.mjc.web.exception.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GiftCertificateErrorAdvice {

        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler({DaoException.class})
        public void handle(DaoException e) {

        }

        @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
        @ExceptionHandler({ServiceException.class, ValidatorException.class, NullPointerException.class})
        public void handle() {

        }

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler({ControllerException.class})
        public void handle(ControllerException e) {

        }

}
