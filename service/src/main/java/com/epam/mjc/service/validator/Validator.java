package com.epam.mjc.service.validator;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.service.exception.ValidatorException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

public class Validator {
    private static final BigDecimal MAX_PRICE_VALUE = new BigDecimal(10000) ;
    private static final BigDecimal MIN_PRICE_VALUE = new BigDecimal(0);
    private static final int DESCRIPTION_MAX_LENGTH = 200;
    private static final int NAME_MAX_LENGTH = 50;
    private static final int VALID_DAYS_MIN_VALUE = 0;
    private static final int VALID_DAYS_MAX_VALUE = 500;

    public static void validateCertificateProperties(GiftCertificate giftCertificate) throws ValidatorException {
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
}
