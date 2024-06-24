package com.example.portalsecurity.security.config;

import com.example.portalsecurity.security.entryPoint.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class WebAuthorizationConfig2 {

    /*
    //start 6.1.3
    private final AuthenticationProvider authenticationProvider;

    public WebAuthorizationConfig2(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    //@Bean
    public SecurityFilterChain securityFilterChain1(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .anyRequest()
                                        .authenticated());
        return httpSecurity.build();
    }
    //end 6.1.3

    //start 6.3.1
    //@Bean
    public SecurityFilterChain securityFilterChain2(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic(httpBasic -> {
                    httpBasic.realmName("OTHER");
                    httpBasic.authenticationEntryPoint(new AuthenticationEntryPointImpl());
                })
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .anyRequest()
                                        .authenticated());
        return httpSecurity.build();
    }
    //end 6.3.1
 */

    //start 6.3.2
    //@Bean
    public SecurityFilterChain securityFilterChain3(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .anyRequest()
                                        .authenticated());
        return httpSecurity.build();
    }


   //@Bean
    public SecurityFilterChain securityFilterChain4(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(
                        formLoginConfigurer ->
                                formLoginConfigurer
                                        .defaultSuccessUrl("/home", true))
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .anyRequest()
                                        .authenticated());
        return httpSecurity.build();
    }


    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    public WebAuthorizationConfig2(AuthenticationSuccessHandler authenticationSuccessHandler,
                                   AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    //@Bean
    public SecurityFilterChain securityFilterChain5(HttpSecurity httpSecurity) throws Exception {
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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(
                        formLoginConfigurer ->
                                formLoginConfigurer
                                        .successHandler(authenticationSuccessHandler)
                                        .failureHandler(authenticationFailureHandler))
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .anyRequest()
                                        .authenticated());
        return httpSecurity.build();
    }
    //end 6.3.2
}