package com.epam.mjc.web.controller;

import com.epam.mjc.web.config.TestAppConfig;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebMvcTest
@ContextConfiguration(classes = {TestAppConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/migration/insert-data.sql")
public class TagControllerTest {
    private static final String TAGS_URL = "/tags";

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllTagsTest() throws Exception {
        this.mvc.perform(get(TAGS_URL)).andDo(print())
                .andExpect(status().isOk());
                //.andExpect(content().json(getResourcesAsString()));

    }

    private String getResourcesAsString(String name) throws IOException {

        return new String(IOUtils.toByteArray(TagControllerTest.class.getResourceAsStream(name)));
    }

}
