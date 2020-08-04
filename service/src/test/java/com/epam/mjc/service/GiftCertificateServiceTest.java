package com.epam.mjc.service;//package com.epam.mjc.service;


import com.epam.mjc.dao.dto.GiftCertificateDto;
import com.epam.mjc.dao.entity.*;
import com.epam.mjc.dao.impl.GiftCertificateDaoImpl;
import com.epam.mjc.dao.impl.TagDaoImpl;
import com.epam.mjc.service.exception.NotFoundException;
import com.epam.mjc.service.impl.GiftCertificateServiceImpl;
import com.epam.mjc.service.mapper.GiftCertificateMapper;
import com.epam.mjc.service.mapper.TagMapper;
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
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GiftCertificateServiceTest {
    @Mock
    GiftCertificateDaoImpl giftCertificateDao = mock(GiftCertificateDaoImpl.class);
    @Mock
    TagDaoImpl tagDao = mock(TagDaoImpl.class);
    @Mock
    GiftCertificateMapper certificateMapper = mock(GiftCertificateMapper.class);
    @Mock
    TagMapper tagMapper = mock(TagMapper.class);

    @InjectMocks
    private GiftCertificateServiceImpl service;

    private static final String ID = "2";
    private static final String INVALID_ID = "2222";
    private static final GiftCertificateEntity CERTIFICATE_ENTITY = new GiftCertificateEntity(2L, "Books",
            "Books",
            new BigDecimal(10),
            LocalDateTime.now(),
            10,
            true);
    private static final GiftCertificateDto CERTIFICATE_DTO = new GiftCertificateDto(2L, "Books",
            "Books",
            new BigDecimal(10),
            LocalDateTime.now(),
            10);
    private static final List<String> tags = new ArrayList<>();
    private static final SearchParams SEARCH_PARAMS_WITH_DATA = new SearchParams(
            initializeTags(),
            "ok",
            new SortParams("date", SortType.ASC));
    private static List<GiftCertificateDto> GIFT_CERTIFICATE_DTO_LIST = new ArrayList<>();
    private static List<GiftCertificateEntity> GIFT_CERTIFICATE_ENTITY_LIST = new ArrayList<>();
    private static final SearchParams SEARCH_PARAMS_WITHOUT_DATA = new SearchParams(null, null, new SortParams(null, null));

    private static List<String> initializeTags() {
        tags.add("fun");
        tags.add("sport");
        return tags;
    }

    @Before
    public void setUp( ) {
        service = new GiftCertificateServiceImpl(giftCertificateDao, tagDao, certificateMapper, tagMapper);
    }

    @Test
    public void getCertificateByValidIdShouldReturnValidCertificateObjectTest() {
        long longId = Long.parseLong(ID);
        when(giftCertificateDao.getById(longId)).thenReturn(java.util.Optional.of(CERTIFICATE_ENTITY));
        when(certificateMapper.toDto(CERTIFICATE_ENTITY)).thenReturn(CERTIFICATE_DTO);
        GiftCertificateDto certificateActual = service.getCertificateById(longId);
        Assert.assertEquals(CERTIFICATE_DTO, certificateActual);
        verify(giftCertificateDao, times(1)).getById(longId);
    }

    @Test(expected = NotFoundException.class)
    public void getCertificateByInvalidIdShouldReturnNotFoundExceptionTest() {
        long longId = Long.parseLong(INVALID_ID);
        when(giftCertificateDao.getById(longId)).thenReturn(Optional.empty());
        service.getCertificateById(longId);
        verify(giftCertificateDao, times(1)).getById(longId);
        verify(service, times(1)).getCertificateById(longId);
    }

    @Test
    public void getCertificatesWithSearchParamsShouldReturnListCertificatesDtoTest() {
        when(giftCertificateDao.getAll(SEARCH_PARAMS_WITH_DATA, 5, 1)).thenReturn(GIFT_CERTIFICATE_ENTITY_LIST);
        GIFT_CERTIFICATE_ENTITY_LIST.add(CERTIFICATE_ENTITY);
        GIFT_CERTIFICATE_DTO_LIST.add(CERTIFICATE_DTO);
        when(certificateMapper.toDto(CERTIFICATE_ENTITY)).thenReturn(CERTIFICATE_DTO);
        List<GiftCertificateDto> result = service.getCertificates(SEARCH_PARAMS_WITH_DATA, 5, 1);
        verify(giftCertificateDao, times(1)).getAll(SEARCH_PARAMS_WITH_DATA, 5, 1);
        verify(certificateMapper, times(1)).toDto(CERTIFICATE_ENTITY);
        Assert.assertEquals(GIFT_CERTIFICATE_DTO_LIST, result);
    }
    @Test
    public void createCertificateWithValidParamsShouldReturnCertificateObject() {
        when(giftCertificateDao.getByName("Books")).thenReturn(Optional.empty());
        when(tagDao.getByName("fun")).thenReturn(Optional.empty());
        when(tagDao.getByName("sport")).thenReturn(Optional.empty());
        when(tagDao.create(new TagEntity("fun"))).thenReturn(2L);
        when(tagDao.create(new TagEntity("sport"))).thenReturn(3L);

        when(certificateMapper.toDto(CERTIFICATE_ENTITY)).thenReturn(CERTIFICATE_DTO);
        when(giftCertificateDao.create(CERTIFICATE_ENTITY)).thenReturn(2L);
        when(giftCertificateDao.getById(2L)).thenReturn(Optional.of(CERTIFICATE_ENTITY));
        GiftCertificateDto result = service.createCertificate(CERTIFICATE_DTO);
        Assert.assertEquals(CERTIFICATE_DTO, result);
    }


}
