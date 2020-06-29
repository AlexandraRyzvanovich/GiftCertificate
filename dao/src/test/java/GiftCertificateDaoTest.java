import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.entity.GiftCertificate;
import config.TestAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static testdata.TestData.SEARCH_PARAMS_EMPTY;
import static testdata.TestData.SEARCH_PARAMS_NOT_EMPTY;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class GiftCertificateDaoTest {
    @Autowired
    private GiftCertificateDao giftCertificateDao;

    @Test
    public void getByIdValidTest() {
        GiftCertificate actual = giftCertificateDao.getById(2);
        assertThat(actual.getId(), is(2L));
        assertEquals(actual.getName(),"Computer");
        assertEquals(actual.getDescription(), "Discount for Computer");
        assertEquals(actual.getPrice(), new BigDecimal(10));
    }

    @Test
    public void getAllWithEmptySearchParamsTest() {
        List<GiftCertificate> actualList =  giftCertificateDao.getAll(SEARCH_PARAMS_EMPTY);
        assertTrue(actualList.size() >= 3);
    }

    @Test
    public void getAllWithNonEmptySearchParamsTest() {
        List<GiftCertificate> actualList =  giftCertificateDao.getAll(SEARCH_PARAMS_NOT_EMPTY);
        assertTrue(actualList.size() >= 1);
    }
}
