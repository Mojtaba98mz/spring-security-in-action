package com.example.portalsecurity.authorizationTests.costomSecurityContext;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

//18.3
public class CustomSecurityContextFactory implements WithSecurityContextFactory<WithCustomUser> {

    @Override
    public SecurityContext createSecurityContext(WithCustomUser withCustomUser) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        var authenticationToken = new UsernamePasswordAuthenticationToken(withCustomUser.userName(), null, null);
        securityContext.setAuthentication(authenticationToken);
        return securityContext;
    }
}