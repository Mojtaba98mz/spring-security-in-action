package com.example.portalsecurity.authorizationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//18.1
@SpringBootTest
@AutoConfigureMockMvc
public class TestSecurityWithRequestPostProcessor {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloWithNameAuthenticated() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/helloWithName")
                                .with(SecurityMockMvcRequestPostProcessors.user("Mary")))
                .andExpect(
                        MockMvcResultMatchers.content().string("hello Mary"))
                .andExpect(
                        MockMvcResultMatchers.status().isOk());
    }

}