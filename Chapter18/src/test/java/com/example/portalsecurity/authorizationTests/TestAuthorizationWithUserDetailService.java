package com.example.portalsecurity.authorizationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//18.2
@SpringBootTest
@AutoConfigureMockMvc
public class TestAuthorizationWithUserDetailService {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(value = "Mary")
    void helloWithNameAuthenticated() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/helloWithName"))
                .andExpect(
                        MockMvcResultMatchers.content().string("hello Mary"))
                .andExpect(
                        MockMvcResultMatchers.status().isOk());
    }

}