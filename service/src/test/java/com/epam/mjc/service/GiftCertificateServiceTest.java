package com.epam.mjc.service;

import com.epam.mjc.dao.GiftCertificateDaoImpl;
import com.epam.mjc.dao.TagDaoImpl;
import com.epam.mjc.dao.entity.*;
import com.epam.mjc.service.validator.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public static final long ID = 2;
    public static final GiftCertificate CERTIFICATE = new GiftCertificate(2L,"Books",
            "Books,",
            new BigDecimal(10),
            LocalDateTime.now(),
            10);
    public static final SearchParams SEARCH_PARAMS = new SearchParams(
            new ArrayList<>(),
            "ok",
            new SortParams("date", SortType.ASC)
    );
    public static List<GiftCertificate> GIFT_CERTIFICATE_LIST = new ArrayList<>();

    @Before
    public void setUp() {
        service = new GiftCertificateServiceImpl(giftCertificateDao,tagDao, validator);
    }

    @Test
    public void getCertificateByIdValidIdTest() {
        when(giftCertificateDao.getById(ID)).thenReturn(CERTIFICATE);
        GiftCertificate certificateActual = service.getCertificateById(ID);
        Assert.assertEquals(CERTIFICATE, certificateActual);
        verify(giftCertificateDao, times(1)).getById(ID);
        verify(tagDao, times(1)).getAllTagsByCertificateId(ID);
    }

    @Test
    public void getCertificateByInvalidId() {
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
    public void createCertificateTest() {
        when(giftCertificateDao.create(CERTIFICATE)).thenReturn(ID);
        when(giftCertificateDao.getById(ID)).thenReturn(CERTIFICATE);
        GiftCertificate actualCertificate = service.createCertificate(CERTIFICATE);
        Assert.assertEquals(CERTIFICATE, actualCertificate);

    }
    @Test
    public void deleteCertificateByIdTest() {
        when(giftCertificateDao.deleteById(ID)).thenReturn(true);
        Assert.assertEquals(true, service.deleteCertificateById(ID));
    }
}
