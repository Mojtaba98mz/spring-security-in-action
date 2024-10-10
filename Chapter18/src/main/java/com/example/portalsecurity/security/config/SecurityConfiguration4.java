package com.example.portalsecurity.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

//18.5 formLogin
@Configuration
@AllArgsConstructor
public class SecurityConfiguration4 {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;

//    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(
                        formLoginConfigurer ->
                                formLoginConfigurer
                                        .successHandler(authenticationSuccessHandler)
                                        .failureHandler(authenticationFailureHandler))
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .anyRequest()
                                        .authenticated());
        return httpSecurity.build();
    }

//    @Bean
    UserDetailsService userDetailsService() {
        var john = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        var bill = User.withUsername("bill")
                .password("12345")
                .authorities("write")
                .build();
        return new InMemoryUserDetailsManager(john, bill);
    }

//    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}