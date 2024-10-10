package com.example.portalsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TestCsrf {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloPost() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/hello"))
                .andExpect(
                        MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testHelloPostWithCsrf() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/hello")
                                .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(
                        MockMvcResultMatchers.status().isOk());
    }
}