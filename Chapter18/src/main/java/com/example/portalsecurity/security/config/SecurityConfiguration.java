package com.example.portalsecurity.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//18.2
@Configuration
public class SecurityConfiguration {

//    @Bean
    UserDetailsService userDetailsService() {
        var john = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        var mary = User.withUsername("Jane")
                .password("54321")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(john, mary);
    }
}