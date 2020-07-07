package com.epam.mjc.service.validator;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.Order;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.entity.User;
import com.epam.mjc.service.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Validator {
    private static final BigDecimal MAX_PRICE_VALUE = new BigDecimal(10000) ;
    private static final BigDecimal MIN_PRICE_VALUE = new BigDecimal(0);
    private static final int DESCRIPTION_MAX_LENGTH = 200;
    private static final int CERTIFICATE_NAME_MAX_LENGTH = 50;
    private static final int TAG_NAME_MAX_LENGTH = 50;
    private static final int VALID_DAYS_MIN_VALUE = 0;
    private static final int VALID_DAYS_MAX_VALUE = 500;
    private static final int USER_NAME_MAX_LENGTH = 50;
    private static final int USER_SURNAME_MAX_LENGTH = 50;

    public static void validateCertificate(GiftCertificate giftCertificate) {
        String name =  giftCertificate.getName();
        String description = giftCertificate.getDescription();
        BigDecimal price = giftCertificate.getPrice();
        Integer validDays = giftCertificate.getValidDays();

        if(name == null || name.length() > CERTIFICATE_NAME_MAX_LENGTH || name.isEmpty()) {
            throw new ValidationException("Gift certificate name" + name + "is incorrect. Requirements: not empty, max length 50 characters");
        }
        if(description == null || description.length() > DESCRIPTION_MAX_LENGTH || description.isEmpty()) {
            throw new ValidationException("Gift certificate description" + description + "is incorrect. Requirements: not empty, max length " + DESCRIPTION_MAX_LENGTH + " characters");
        }
        if(price == null || price.compareTo(MIN_PRICE_VALUE) < 0 || price.compareTo(MAX_PRICE_VALUE) > 0 ) {
            throw new ValidationException("Gift certificate price" + price + "is incorrect. Requirements: Min value = " +  MIN_PRICE_VALUE + ", max = " + MAX_PRICE_VALUE);
        }
        if(validDays == null || validDays < VALID_DAYS_MIN_VALUE || validDays > VALID_DAYS_MAX_VALUE) {
            throw new ValidationException("Gift certificate valid days field" + validDays + "is incorrect. Requirements: not empty, max value " + VALID_DAYS_MAX_VALUE);
        }
    }

    public static void validateTag(Tag tag) {
        String name =  tag.getName();
        if(name == null || name.length() > TAG_NAME_MAX_LENGTH || name.isEmpty()) {
            throw new ValidationException("Tag name" + name + "is incorrect. Requirements: not empty, max length " + TAG_NAME_MAX_LENGTH + " characters");
        }
    }
    public static void validateUser(User user) {
        String name = user.getName();
        String surname = user.getSurname();
        if(name == null || name.length() > CERTIFICATE_NAME_MAX_LENGTH || name.isEmpty()) {
            throw new ValidationException("User name" + name + "is incorrect. Requirements: not empty, max length " + USER_NAME_MAX_LENGTH + " characters");
        }
        if(surname == null || surname.length() > CERTIFICATE_NAME_MAX_LENGTH || surname.isEmpty()) {
            throw new ValidationException("User surname" + name + "is incorrect. Requirements: not empty, max length " +  USER_SURNAME_MAX_LENGTH + " characters");
        }
    }
    public static void validateOrder(Order order) {
        Long userId = order.getUserId();
        Long certificateId = order.getCertificateId();
        if (userId == null) {
            throw new ValidationException("UserId is required.");
        }
        if (certificateId == null) {
            throw new ValidationException("CertificateId is required");
        }
    }
}
