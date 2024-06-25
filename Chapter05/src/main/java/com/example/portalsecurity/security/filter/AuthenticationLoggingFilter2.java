package com.example.portalsecurity.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class AuthenticationLoggingFilter2 extends OncePerRequestFilter {
    //start 5.5
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String reqId = request.getHeader("req-Id");
        logger.info("Successfully authenticated request with id " + reqId);
        filterChain.doFilter(request, response);
    }
    //end 5.5
}