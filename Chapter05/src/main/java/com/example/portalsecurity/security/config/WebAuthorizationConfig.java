package com.example.portalsecurity.security.config;

import com.example.portalsecurity.security.filter.AuthenticationLoggingFilter;
import com.example.portalsecurity.security.filter.AuthenticationLoggingFilter2;
import com.example.portalsecurity.security.filter.RequestValidationFilter;
import com.example.portalsecurity.security.filter.StaticKeyAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebAuthorizationConfig {

    //5.2
    private final RequestValidationFilter requestValidationFilter;
    //5.3
    private final AuthenticationLoggingFilter authenticationLoggingFilter;
    //5.4
    private final StaticKeyAuthenticationFilter staticKeyAuthenticationFilter;
    //5.5
    private final AuthenticationLoggingFilter2 authenticationLoggingFilter2;

    @Autowired
    public WebAuthorizationConfig(RequestValidationFilter requestValidationFilter,
                                  AuthenticationLoggingFilter authenticationLoggingFilter,
                                  StaticKeyAuthenticationFilter staticKeyAuthenticationFilter,
                                  AuthenticationLoggingFilter2 authenticationLoggingFilter2) {
        this.requestValidationFilter = requestValidationFilter;
        this.authenticationLoggingFilter = authenticationLoggingFilter;
        this.staticKeyAuthenticationFilter = staticKeyAuthenticationFilter;
        this.authenticationLoggingFilter2 = authenticationLoggingFilter2;
    }

    //start 5.2
    //@Bean
    public SecurityFilterChain securityFilterChain2(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .addFilterBefore(requestValidationFilter, BasicAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .anyRequest()
                                        .authenticated());
        return httpSecurity.build();
    }
    //end 5.2

    //start 5.3
    //@Bean
    public SecurityFilterChain securityFilterChain3(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .addFilterBefore(requestValidationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(authenticationLoggingFilter, BasicAuthenticationFilter.class) //it works before add it here ://
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .anyRequest()
                                        .authenticated());
        return httpSecurity.build();
    }
    //end 5.3

    //start 5.4
    //@Bean
    public SecurityFilterChain securityFilterChain4(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .addFilterAt(staticKeyAuthenticationFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .anyRequest()
                                        .permitAll());
        return httpSecurity.build();
    }
    //end 5.4

    //start 5.5
    //@Bean
    public SecurityFilterChain securityFilterChain5(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .addFilterBefore(requestValidationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(authenticationLoggingFilter2, BasicAuthenticationFilter.class) //it works before add it here ://
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .anyRequest()
                                        .authenticated());
        return httpSecurity.build();
    }
    //end 5.5
}