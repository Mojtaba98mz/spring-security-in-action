package com.example.portalsecurity.authorizationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//18.1
@SpringBootTest
@AutoConfigureMockMvc
public class TestAuthorizationWithAnnotations {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloUnauthenticated() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/hello"))
                .andExpect(
                        MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void helloAuthenticated() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/hello"))
                .andExpect(
                        MockMvcResultMatchers.content().string("hello"))
                .andExpect(
                        MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "Mary")
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