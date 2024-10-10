package com.example.portalsecurity.authenticationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//18.5 httpBasic
@SpringBootTest
@AutoConfigureMockMvc
public class TestAuthenticationWithHttpBasic {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloAuthenticatingWithValidUser() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/hello")
                                .with(SecurityMockMvcRequestPostProcessors.httpBasic("john", "12345")))
                .andExpect(
                        MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void helloAuthenticatingWithInvalidUser() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/hello")
                                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rosa", "123456")))
                .andExpect(
                        MockMvcResultMatchers.status().isUnauthorized());
    }
}