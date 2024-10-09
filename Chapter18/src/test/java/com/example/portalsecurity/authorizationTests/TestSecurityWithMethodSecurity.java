package com.example.portalsecurity.authorizationTests;

import com.example.portalsecurity.service.NameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;

//18.4
@SpringBootTest
public class TestSecurityWithMethodSecurity {

    @Autowired
    private NameService nameService;

    @Test
    void testNameServiceWithNoUser() {
        Assertions.assertThrows(
                AuthenticationException.class,
                () -> nameService.getName());
    }

    @Test
    @WithMockUser(authorities = "read")
    void testNameServiceWithUserButWrongAuthority() {
        Assertions.assertThrows(
                AccessDeniedException.class,
                () -> nameService.getName());
    }

    @Test
    @WithMockUser(authorities = "write")
    void testNameServiceWithUserButCorrectAuthority() {
        Assertions.assertEquals("Fantastico", nameService.getName());
    }
}