package com.example.portalsecurity.authorizationTests;

import com.example.portalsecurity.authorizationTests.costomSecurityContext.WithCustomUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//18.3
@SpringBootTest
@AutoConfigureMockMvc
public class TestAuthorizationWithCustomAuthentication {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithCustomUser(userName = "Mary")
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