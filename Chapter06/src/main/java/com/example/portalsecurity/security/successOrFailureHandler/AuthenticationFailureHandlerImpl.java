package com.example.portalsecurity.security.successOrFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    //start 6.3.2
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        try {
            response.setHeader("failed", LocalDateTime.now().toString());
            response.sendRedirect("/error");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    //end 6.3.2
}