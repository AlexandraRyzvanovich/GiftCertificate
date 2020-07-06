package com.epam.mjc.service;

import com.epam.mjc.dao.impl.GiftCertificateDaoImpl;
import com.epam.mjc.dao.impl.TagDaoImpl;
import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.SortParams;
import com.epam.mjc.dao.entity.SortType;
import com.epam.mjc.service.exception.NotFoundException;
import com.epam.mjc.service.impl.GiftCertificateServiceImpl;
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

    private static final String ID = "2";
    private static final String INVALID_ID = "2222";
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
        service = new GiftCertificateServiceImpl(giftCertificateDao, tagDao);
    }

    @Test
    public void getCertificateByValidIdShouldReturnValidCertificateObjectTest() {
        long longId = Long.parseLong(ID);
        when(giftCertificateDao.getById(longId)).thenReturn(CERTIFICATE);
        GiftCertificate certificateActual = service.getCertificateById(longId);
        Assert.assertEquals(CERTIFICATE, certificateActual);
        verify(giftCertificateDao, times(1)).getById(longId);
        verify(tagDao, times(1)).getAllTagsByCertificateId(longId);
    }

    @Test(expected = NotFoundException.class)
    public void getCertificateByInvalidIdShouldReturnNotFoundExceptionTest() {
        long longId = Long.parseLong(INVALID_ID);
        when(giftCertificateDao.getById(longId)).thenReturn(null);
        service.getCertificateById(longId);
        verify(giftCertificateDao, times(1)).getById(longId);
        verify(service, times(1)).getCertificateById(longId);
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
        long longId = Long.parseLong(ID);
        when(giftCertificateDao.create(CERTIFICATE)).thenReturn(longId);
        when(giftCertificateDao.getById(longId)).thenReturn(CERTIFICATE);
        GiftCertificate actualCertificate = service.createCertificate(CERTIFICATE);
        Assert.assertEquals(CERTIFICATE, actualCertificate);
    }

    @Test
    public void deleteCertificateByIdTest() {
        long longId = Long.parseLong(ID);
        when(giftCertificateDao.deleteById(longId)).thenReturn(true);
        Assert.assertTrue(!service.deleteCertificateById(ID).isEmpty());
    }
}
