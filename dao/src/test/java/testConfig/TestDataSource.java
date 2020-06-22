package testConfig;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDataSource {
    private static final String PATH = "C:/Users/Alexandra/IdeaProjects/GiftCertificate/dao/src/test/resources/testDb.properties";

    @Autowired
    public TestDataSource() {
    }

    public static HikariDataSource getDataSource() {
        HikariConfig config = new HikariConfig(PATH);
        return new HikariDataSource(config);
    }
}