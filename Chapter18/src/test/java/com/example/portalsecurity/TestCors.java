package com.example.portalsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TestCors {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testCORSForTestEndpoint() throws Exception {
        mvc
                .perform(
                        MockMvcRequestBuilders
                                .options("/test")
                                .header("Access-Control-Request-Method", "GET")
                                .header("Access-Control-Allow-Origin", "https://example.org"))
                .andExpect(
                        MockMvcResultMatchers
                                .header()
                                .exists("Access-Control-Allow-Origin"))
                .andExpect(
                        MockMvcResultMatchers
                                .header()
                                .string("Access-Control-Allow-Origin", "https://example.org"))
                .andExpect(
                        MockMvcResultMatchers
                                .header()
                                .exists("Access-Control-Allow-Methods"))
                .andExpect(
                        MockMvcResultMatchers
                                .header()
                                .string("Access-Control-Allow-Methods", "POST"))
                .andExpect(
                        MockMvcResultMatchers.status().isOk());
    }
}