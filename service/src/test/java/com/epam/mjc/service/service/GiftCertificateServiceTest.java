package com.epam.mjc.service.service;

import com.epam.mjc.dao.dao.GiftCertificateDao;
import com.epam.mjc.dao.dao.GiftCertificateDaoImpl;
import com.epam.mjc.dao.dao.TagDao;
import com.epam.mjc.dao.dao.TagDaoImpl;
import com.epam.mjc.dao.entity.*;
import com.epam.mjc.dao.exception.DaoIncorrectParamsException;
import com.epam.mjc.dao.exception.DaoNotFoundException;
import com.epam.mjc.service.validator.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.epam.mjc.service.service.testData.GiftCertificateTestData.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class GiftCertificateServiceTest {
    @Mock
    GiftCertificateDaoImpl giftCertificateDao = mock(GiftCertificateDaoImpl.class);
    @Mock
    TagDaoImpl tagDao = mock(TagDaoImpl.class);
    @Mock
    Validator validator = mock(Validator.class);

    @InjectMocks
    private GiftCertificateServiceImpl service;

    @Before
    public void setUp() {
        service = new GiftCertificateServiceImpl(giftCertificateDao,tagDao, validator);
    }


    /*@Test
    public void getCertificateByIdValidIdTest() throws DaoNotFoundException {
        when(giftCertificateDao.getById(ID)).thenReturn(CERTIFICATE);
        GiftCertificate certificateActual = service.getCertificateById(ID);
        Assert.assertEquals(CERTIFICATE, certificateActual);
        verify(giftCertificateDao, times(1)).getById(ID);
        verify(tagDao, times(1)).getAllTagsByCertificateId(ID);
    }*/

    @Test
    public void getCertificateByInvalidId() throws DaoNotFoundException {
        when(giftCertificateDao.getById(ID)).thenReturn(CERTIFICATE);
        GiftCertificate certificateActual = service.getCertificateById(ID);
        Assert.assertEquals(CERTIFICATE, certificateActual);
    }
    @Test
    public void getCertificatesWithoutSearchParamsTest() {
        GIFT_CERTIFICATE_LIST.add(CERTIFICATE);
        when(giftCertificateDao.getAll(SEARCH_PARAMS)).thenReturn(GIFT_CERTIFICATE_LIST);
        List<GiftCertificate> actualList = service.getCertificates(SEARCH_PARAMS);
        Assert.assertEquals(GIFT_CERTIFICATE_LIST, actualList);
    }
    @Test
    public void createCertificateTest() throws DaoIncorrectParamsException, DaoNotFoundException {
        when(giftCertificateDao.create(CERTIFICATE)).thenReturn(ID);
        when(giftCertificateDao.getById(ID)).thenReturn(CERTIFICATE);
        GiftCertificate actualCertificate = service.createCertificate(CERTIFICATE);
        Assert.assertEquals(CERTIFICATE, actualCertificate);

    }
    @Test
    public void deleteCertificateByIdTest() throws DaoNotFoundException {
        when(giftCertificateDao.deleteById(ID)).thenReturn(true);
        Assert.assertEquals(true, service.deleteCertificateById(ID));
    }
}
