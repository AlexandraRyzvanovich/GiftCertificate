package com.epam.mjc.service;

import com.epam.mjc.dao.GiftCertificateDaoImpl;
import com.epam.mjc.dao.TagDaoImpl;
import com.epam.mjc.dao.entity.*;
import com.epam.mjc.dao.exception.DaoIncorrectParamsException;
import com.epam.mjc.dao.exception.DaoNotFoundException;
import com.epam.mjc.service.testdata.GiftCertificateTestData;
import com.epam.mjc.service.validator.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

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
        when(giftCertificateDao.getById(GiftCertificateTestData.ID)).thenReturn(GiftCertificateTestData.CERTIFICATE);
        GiftCertificate certificateActual = service.getCertificateById(GiftCertificateTestData.ID);
        Assert.assertEquals(GiftCertificateTestData.CERTIFICATE, certificateActual);
    }
    @Test
    public void getCertificatesWithoutSearchParamsTest() {
        GiftCertificateTestData.GIFT_CERTIFICATE_LIST.add(GiftCertificateTestData.CERTIFICATE);
        when(giftCertificateDao.getAll(GiftCertificateTestData.SEARCH_PARAMS)).thenReturn(GiftCertificateTestData.GIFT_CERTIFICATE_LIST);
        List<GiftCertificate> actualList = service.getCertificates(GiftCertificateTestData.SEARCH_PARAMS);
        Assert.assertEquals(GiftCertificateTestData.GIFT_CERTIFICATE_LIST, actualList);
    }
    @Test
    public void createCertificateTest() throws DaoIncorrectParamsException, DaoNotFoundException {
        when(giftCertificateDao.create(GiftCertificateTestData.CERTIFICATE)).thenReturn(GiftCertificateTestData.ID);
        when(giftCertificateDao.getById(GiftCertificateTestData.ID)).thenReturn(GiftCertificateTestData.CERTIFICATE);
        GiftCertificate actualCertificate = service.createCertificate(GiftCertificateTestData.CERTIFICATE);
        Assert.assertEquals(GiftCertificateTestData.CERTIFICATE, actualCertificate);

    }
    @Test
    public void deleteCertificateByIdTest() throws DaoNotFoundException {
        when(giftCertificateDao.deleteById(GiftCertificateTestData.ID)).thenReturn(true);
        Assert.assertEquals(true, service.deleteCertificateById(GiftCertificateTestData.ID));
    }
}
