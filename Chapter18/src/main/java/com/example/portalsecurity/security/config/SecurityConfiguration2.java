package com.example.portalsecurity.security.config;

import com.example.portalsecurity.security.authenticationProvider.AuthenticationProviderImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//18.3, //18.5 httpBasic
@Configuration
@AllArgsConstructor
public class SecurityConfiguration2 {

//    private final AuthenticationProviderImpl authenticationProvider;

    //@Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic(Customizer.withDefaults());
//        httpSecurity.authenticationProvider(authenticationProvider);
        httpSecurity.authorizeHttpRequests(authorization -> authorization.anyRequest().authenticated());
        return httpSecurity.build();
    }
}