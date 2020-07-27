import com.epam.mjc.dao.GiftCertificateDao;
import com.epam.mjc.dao.entity.GiftCertificateEntity;
import config.TestAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static testdata.TestData.SEARCH_PARAMS_EMPTY;
import static testdata.TestData.SEARCH_PARAMS_NOT_EMPTY;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class GiftCertificateDaoTest {
    @Autowired
    private GiftCertificateDao giftCertificateDao;

    @Test
    public void getByIdValidTest() {
        GiftCertificateEntity actual = giftCertificateDao.getById(2);
        assertEquals(actual.getId(), is(2L));
        assertEquals(actual.getName(),"Computer");
        assertEquals(actual.getDescription(), "Discount for Computer");
        assertEquals(actual.getPrice(), new BigDecimal(10));
    }

    @Test
    public void getAllWithEmptySearchParamsTest() {
        List<GiftCertificateEntity> actualList =  giftCertificateDao.getAll(SEARCH_PARAMS_EMPTY, 5, 1);
        assertTrue(actualList.size() >= 3);
    }

    @Test
    public void getAllWithNonEmptySearchParamsTest() {
        List<GiftCertificateEntity> actualList =  giftCertificateDao.getAll(SEARCH_PARAMS_NOT_EMPTY, 5, 1);
        assertTrue(actualList.size() >= 1);
    }
}
