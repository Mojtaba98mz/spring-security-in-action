package com.example.portalsecurity.security.config;

import com.example.portalsecurity.security.authenticationProvider.AuthenticationProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//18.3
@Configuration
public class SecurityConfiguration2 {

    private final AuthenticationProviderImpl authenticationProvider;

    @Autowired
    public SecurityConfiguration2(AuthenticationProviderImpl authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.authenticationProvider(authenticationProvider);
        httpSecurity.authorizeHttpRequests(authorization -> authorization.anyRequest().authenticated());
        return httpSecurity.build();
    }
}