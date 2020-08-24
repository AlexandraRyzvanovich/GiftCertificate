package com.epam.mjc.web.controller;

import com.epam.mjc.web.config.TestAppConfig;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebMvcTest
@ContextConfiguration(classes = {TestAppConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/insert-data.sql")
public class AuthenticationControllerTest {

    private static final String LOGIN_URL = "/auth/login";
    private static final String SIGN_UP_URL = "/auth/signup";

    @Autowired
    private MockMvc mvc;

    @Test
    public void testLogin() throws Exception {
        this.mvc.perform(post(LOGIN_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getResourcesAsString("testLogin.json"))
        ).andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testSignup() throws Exception {
        this.mvc.perform(post(SIGN_UP_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getResourcesAsString("testSignup.json"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(getResourcesAsString("testSignupResponse.json")));
    }

    private String getResourcesAsString(String name) throws IOException {

        return new String(IOUtils.toByteArray(AuthenticationControllerTest.class.getResourceAsStream(name)));
    }
}