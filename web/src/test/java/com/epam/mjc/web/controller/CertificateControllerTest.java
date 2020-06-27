package com.epam.mjc.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}
