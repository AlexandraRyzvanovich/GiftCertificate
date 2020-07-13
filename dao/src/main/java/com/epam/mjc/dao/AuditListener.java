package com.epam.mjc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;


public class AuditListener {
    private static Logger logger = LoggerFactory.getLogger(AuditListener.class);


    @PrePersist
    private void beforeAnyOperation(Object obj) {
        logger.debug("Pre persist operation");

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
