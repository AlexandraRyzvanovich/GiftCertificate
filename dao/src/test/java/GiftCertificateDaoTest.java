import com.epam.mjc.dao.dao.GiftCertificateDao;
import com.epam.mjc.dao.dao.TagDao;
import com.epam.mjc.dao.entity.*;
import com.epam.mjc.dao.exception.DaoNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import config.TestAppConfig;

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
    @Autowired
    private TagDao tagDao;

    @Test
    public void getByIdValidTest() throws DaoNotFoundException {
        GiftCertificate actual = giftCertificateDao.getById(2);
        assertThat(actual.getId(), is(2L));
        assertEquals(actual.getName(),"Computer");
        assertEquals(actual.getDescription(), "Discount for Computer");
        assertEquals(actual.getPrice(), 10);
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
    @Test
    public void updateTest() {

    }

}
