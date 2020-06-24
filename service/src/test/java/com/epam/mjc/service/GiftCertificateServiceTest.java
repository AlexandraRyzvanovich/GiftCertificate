package com.epam.mjc.service;

import com.epam.mjc.dao.GiftCertificateDaoImpl;
import com.epam.mjc.dao.TagDaoImpl;
import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.SortParams;
import com.epam.mjc.dao.entity.SortType;
import com.epam.mjc.service.exception.NotFoundException;
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

    private static final long ID = 2;
    private static final long INVALID_ID = 2222;
    private static final GiftCertificate CERTIFICATE = new GiftCertificate(2L, "Books",
            "Books,",
            new BigDecimal(10),
            LocalDateTime.now(),
            10);
    private static final List<String> tags = new ArrayList<>();
    private static final SearchParams SEARCH_PARAMS_WITH_DATA = new SearchParams(
            initializeTags(),
            "ok",
            new SortParams("date", SortType.ASC));
    private static List<GiftCertificate> GIFT_CERTIFICATE_LIST = new ArrayList<>();
    private static final SearchParams SEARCH_PARAMS_WITHOUT_DATA = new SearchParams(null, null, new SortParams(null, null));

    private static List<String> initializeTags() {
        tags.add("fun");
        return tags;
    }

    @Before
    public void setUp() {
        service = new GiftCertificateServiceImpl(giftCertificateDao, tagDao, validator);
    }

    @Test
    public void getCertificateByValidIdShouldReturnValidCertificateObjectTest() {
        when(giftCertificateDao.getById(ID)).thenReturn(CERTIFICATE);
        GiftCertificate certificateActual = service.getCertificateById(ID);
        Assert.assertEquals(CERTIFICATE, certificateActual);
        verify(giftCertificateDao, times(1)).getById(ID);
        verify(tagDao, times(1)).getAllTagsByCertificateId(ID);
    }

    @Test(expected = NotFoundException.class)
    public void getCertificateByInvalidIdShouldReturnNotFoundExceptionTest() {
        when(giftCertificateDao.getById(INVALID_ID)).thenReturn(null);
        service.getCertificateById(INVALID_ID);
        verify(giftCertificateDao, times(1)).getById(INVALID_ID);
        verify(service, times(1)).getCertificateById(INVALID_ID);
    }

    @Test
    public void getAllCertificatesWithoutSearchParamsShouldReturnListOfAllCertificatesTest() {
        GIFT_CERTIFICATE_LIST.add(CERTIFICATE);
        when(giftCertificateDao.getAll(SEARCH_PARAMS_WITHOUT_DATA)).thenReturn(GIFT_CERTIFICATE_LIST);
        List<GiftCertificate> actualList = service.getCertificates(SEARCH_PARAMS_WITHOUT_DATA);
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
        Assert.assertTrue(service.deleteCertificateById(ID));
    }
}
