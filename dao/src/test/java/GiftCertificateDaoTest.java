import com.epam.mjc.dao.dao.GiftCertificateDao;
import com.epam.mjc.dao.dao.TagDao;
import com.epam.mjc.dao.entity.GiftCertificate;
import com.epam.mjc.dao.exception.DaoNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import testConfig.TestAppConfig;
import testConfig.TestDataSource;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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

}
