package com.epam.mjc.service.validator;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.Identifiable;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.service.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Validator {
    private static final BigDecimal MAX_PRICE_VALUE = new BigDecimal(10000) ;
    private static final BigDecimal MIN_PRICE_VALUE = new BigDecimal(0);
    private static final int DESCRIPTION_MAX_LENGTH = 200;
    private static final int NAME_MAX_LENGTH = 50;
    private static final int VALID_DAYS_MIN_VALUE = 0;
    private static final int VALID_DAYS_MAX_VALUE = 500;

    public static void validateCertificate(GiftCertificate giftCertificate) throws ValidationException {
        String name =  giftCertificate.getName();
        String description = giftCertificate.getDescription();
        BigDecimal price = giftCertificate.getPrice();
        Integer validDays = giftCertificate.getValidDays();

        if(name == null || name.length() > NAME_MAX_LENGTH || name.isEmpty()) {
            throw new ValidationException("Gift certificate name" + name + "is incorrect");
        }
        if(description == null || description.length() > DESCRIPTION_MAX_LENGTH || description.isEmpty()) {
            throw new ValidationException("Gift certificate description" + description + "is incorrect");
        }
        if(price == null || price.compareTo(MIN_PRICE_VALUE) < 0 || price.compareTo(MAX_PRICE_VALUE) > 0 ) {
            throw new ValidationException("Gift certificate price" + price + "is incorrect");
        }
        if(validDays == null || validDays < VALID_DAYS_MIN_VALUE || validDays > VALID_DAYS_MAX_VALUE) {
            throw new ValidationException("Gift certificate valid days field" + validDays + "is incorrect");
        }
    }

    public static void validateTag(Tag tag) throws ValidationException {
        String name =  tag.getName();
        if(name == null || name.length() > NAME_MAX_LENGTH || name.isEmpty()) {
            throw new ValidationException("Tag name" + name + "is incorrect");
        }
    }
}
