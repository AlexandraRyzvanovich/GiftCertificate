//package com.epam.mjc.service.validator;
//
//import com.epam.mjc.dao.entity.GiftCertificate;
//import com.epam.mjc.dao.entity.Tag;
//import com.epam.mjc.service.exception.ValidationException;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//
//import java.math.BigDecimal;
//
//@RunWith(JUnit4.class)
//public class ValidatorTest {
//    private static final GiftCertificate CERTIFICATE_WITH_ALL_VALID_VALUES = new GiftCertificate("name", "description", BigDecimal.valueOf(10), 10);
//    private static final GiftCertificate CERTIFICATE_WITH_INCORRECT_PRICE_VALUE = new GiftCertificate("name", "description", BigDecimal.valueOf(1000000000), 10);
//    private static final Tag TAG_WITH_VALID_NAME_VALUE = new Tag("tagName");
//    private static final Tag TAG_WITH_INVALID_NAME_VALUE = new Tag("tag Nameeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
//
//    @Test
//    public void validateCertificateValidCertificateShouldReturnNothingTest() {
//        Validator.validateCertificate(CERTIFICATE_WITH_ALL_VALID_VALUES);
//    }
//
//    @Test(expected = ValidationException.class)
//    public void validateCertificateInvalidPriceShouldReturnValidationExceptionTest() {
//        Validator.validateCertificate(CERTIFICATE_WITH_INCORRECT_PRICE_VALUE);
//    }
//
//    @Test
//    public void validateTagValidTagShouldReturnNothingTest() {
//        Validator.validateTag(TAG_WITH_VALID_NAME_VALUE);
//    }
//
//    @Test(expected = ValidationException.class)
//    public void validateTagInvalidNameShouldReturnValidationExceptionTest() {
//        Validator.validateTag(TAG_WITH_INVALID_NAME_VALUE);
//    }
//}
