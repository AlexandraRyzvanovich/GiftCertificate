package com.epam.mjc.service.validator;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.Identifiable;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.service.exception.ValidatorException;

import java.math.BigDecimal;

public class Validator<T extends Identifiable> {
    private static final BigDecimal MAX_PRICE_VALUE = new BigDecimal(10000) ;
    private static final BigDecimal MIN_PRICE_VALUE = new BigDecimal(0);
    private static final int DESCRIPTION_MAX_LENGTH = 200;
    private static final int NAME_MAX_LENGTH = 50;
    private static final int VALID_DAYS_MIN_VALUE = 0;
    private static final int VALID_DAYS_MAX_VALUE = 500;

    public void validate(T item) throws ValidatorException {
        if (GiftCertificate.class.equals(item.getClass())) {
            validateCertificate((GiftCertificate) item);
        } else if (Tag.class.equals(item.getClass())) {
            validateTag((Tag) item);
        } else {
            throw new ValidatorException("Validator not specified for such class");
        }

    }
    private void validateCertificate(GiftCertificate giftCertificate) throws ValidatorException {
        String name =  giftCertificate.getName();
        String description = giftCertificate.getDescription();
        BigDecimal price = giftCertificate.getPrice();
        Integer validDays = giftCertificate.getValidDays();

        if(name == null || name.length() > NAME_MAX_LENGTH || name.isEmpty()) {
            throw new ValidatorException("Gift certificate name is incorrect");
        }
        if(description == null || description.length() > DESCRIPTION_MAX_LENGTH || description.isEmpty()) {
            throw new ValidatorException("Gift certificate description is incorrect");
        }
        if(price == null || price.compareTo(MIN_PRICE_VALUE) < 0 || price.compareTo(MAX_PRICE_VALUE) > 0 ) {
            throw new ValidatorException("Gift certificate price is incorrect");
        }
        if(validDays == null || validDays < VALID_DAYS_MIN_VALUE || validDays > VALID_DAYS_MAX_VALUE) {
            throw new ValidatorException("Gift certificate valid days field is incorrect");
        }
    }
    private void validateTag(Tag tag) throws ValidatorException {
        String name =  tag.getName();
        if(name == null || name.length() > NAME_MAX_LENGTH || name.isEmpty()) {
            throw new ValidatorException("Tag name is incorrect");
        }
    }
}
