package com.example.portalsecurity.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//18.4
@Configuration
public class SecurityConfiguration3 {

    //@Bean
    UserDetailsService userDetailsService() {
        var john = User.withUsername("natalie")
                .password("12345")
                .authorities("read")
                .build();
        var mary = User.withUsername("emma")
                .password("54321")
                .authorities("write")
                .build();
        return new InMemoryUserDetailsManager(john, mary);
    }

    //@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}