package com.epam.mjc.dao.dao.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSource {
    private static final String PATH = "C:/Users/Alexandra/IdeaProjects/GiftCertificate/dao/src/main/resources/db.properties";

    @Autowired
    public DataSource() {
    }

    public static HikariDataSource getDataSource() {
        HikariConfig config = new HikariConfig(PATH);
        return new HikariDataSource(config);

    }

}
