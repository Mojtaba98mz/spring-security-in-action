package com.example.portalsecurity.security.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserManagementConfig {

    //start 2.3.5
    @Bean
    public PasswordEncoder passwordEncoder1() {
        return NoOpPasswordEncoder.getInstance();
    }
    //end 2.3.5

    @Bean
    public UserDetailsService userDetailsService1() {
        UserDetails admin = User
                .withUsername("admin")
                .password("123")
                .roles("read")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
    //end 2.3.5
}