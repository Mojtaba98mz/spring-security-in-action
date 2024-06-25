package com.example.portalsecurity.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class AuthenticationLoggingFilter implements Filter {
    //start 5.3
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String reqId = httpRequest.getHeader("req-id");
        log.info("Successfully authenticated request with id " + reqId);
        chain.doFilter(request, response);
    }
    //end 5.3
}