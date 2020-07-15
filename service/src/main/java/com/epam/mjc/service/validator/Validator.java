package com.epam.mjc.service.validator;

import com.epam.mjc.dao.entity.OrderEntity;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.entity.User;
import com.epam.mjc.service.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    private static final int CERTIFICATE_NAME_MAX_LENGTH = 50;
    private static final int TAG_NAME_MAX_LENGTH = 50;
    private static final int USER_NAME_MAX_LENGTH = 50;
    private static final int USER_SURNAME_MAX_LENGTH = 50;


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
    public static void validateOrder(OrderEntity orderEntity) {
        Long userId = orderEntity.getUserId();
        Long certificateId = orderEntity.getCertificateId();
        if (userId == null) {
            throw new ValidationException("UserId is required.");
        }
        if (certificateId == null) {
            throw new ValidationException("CertificateId is required");
        }
    }
}
