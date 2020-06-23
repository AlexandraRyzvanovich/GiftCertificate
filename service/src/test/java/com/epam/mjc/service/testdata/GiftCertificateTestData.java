package com.epam.mjc.service.testdata;

import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.SortType;
import com.epam.mjc.dao.entity.SortParams;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GiftCertificateTestData {
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

}
