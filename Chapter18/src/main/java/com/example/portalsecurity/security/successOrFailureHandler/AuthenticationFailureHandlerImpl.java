package com.example.portalsecurity.security.successOrFailureHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

//18.5 formLogin
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) {
        try {
            response.setHeader("failed", LocalDateTime.now().toString());
            response.sendRedirect("/error");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}