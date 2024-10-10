package com.example.portalsecurity.authenticationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//18.5 formLogin
@SpringBootTest
@AutoConfigureMockMvc
public class TestAuthenticationWithFormLogin {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loggingInWithWrongUser() throws Exception {
        mockMvc.perform(
                        SecurityMockMvcRequestBuilders
                                .formLogin()
                                .user("joey")
                                .password("12345")
                )
                .andExpect(
                        MockMvcResultMatchers.header().exists("failed"))
                .andExpect(
                        SecurityMockMvcResultMatchers.unauthenticated());
    }

    @Test
    public void loggingInWithWrongAuthority() throws Exception {
        mockMvc.perform(
                        SecurityMockMvcRequestBuilders.formLogin()
                                .user("bill").password("12345"))
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/error"))
                .andExpect(
                        MockMvcResultMatchers.status().isFound())
                .andExpect(
                        SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    public void loggingInWithCorrectAuthority() throws Exception {
        mockMvc.perform(
                        SecurityMockMvcRequestBuilders.formLogin()
                                .user("john").password("12345"))
                .andExpect(
                        MockMvcResultMatchers.redirectedUrl("/home"))
                .andExpect(
                        MockMvcResultMatchers.status().isFound())
                .andExpect(
                        SecurityMockMvcResultMatchers.authenticated());
    }
}