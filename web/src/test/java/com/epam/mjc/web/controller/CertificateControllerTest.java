package com.epam.mjc.web.controller;

import com.epam.mjc.service.service.GiftCertificateService;
import com.epam.mjc.web.config.WebAppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class CertificateControllerTest {

    private MockMvc mockMvc;

    @Mock
    CertificateController certificateController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(certificateController).build();
    }

    @Test
    public void getAllCertificatesTest() throws Exception {
        this.mockMvc.perform(get("/certificates"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.log())
                .andReturn();
    }

    @Test
    public void getCertificateByExistedIdTest() throws Exception {
        long id = 2;
        this.mockMvc.perform((get("/certificates/" + id)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(ResultMatcher.matchAll())
                .andReturn();
    }

    @Test
    public void getCertificateByNonExistedIdTest() throws Exception {
        long id = 222;
        this.mockMvc.perform((get("/certificates/" + id)))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(ResultMatcher.matchAll())
                .andReturn();
    }
}
