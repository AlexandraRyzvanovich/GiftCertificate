package com.epam.mjc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


public class AuditListener {
    private static final Logger logger = LoggerFactory.getLogger(AuditListener.class);

    @PrePersist
    private void beforeAnyOperation(Object obj) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> errors = validator.validate(obj);
        if (!CollectionUtils.isEmpty(errors)) {
            throw new ConstraintViolationException(errors);
        }

    }
    @PreUpdate
    private void beforeAnyUpdateOperation(Object obj) {
        logger.debug("Pre update operation");
    }
    @PreRemove
    private void beforeAnyRemoveOperation(Object obj) {
        logger.debug("Pre remove operation");
    }
}
