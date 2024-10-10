package com.example.portalsecurity.authenticationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//18.5 formLogin
@SpringBootTest
@AutoConfigureMockMvc
public class TestAuthenticationWithToken {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void nonOpaqueSuccessfulAuthenticationTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/demo")
                                .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(
                        MockMvcResultMatchers.status().isOk());
    }

    @Test
    void opaqueSuccessfulAuthenticationTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/demo")
                                .with(SecurityMockMvcRequestPostProcessors.opaqueToken()))
                .andExpect(
                        MockMvcResultMatchers.status().isOk());
    }
}