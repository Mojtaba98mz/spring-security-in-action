package com.example.portalsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
//        http.authorizeHttpRequests(req -> req.requestMatchers("/hello").hasRole("ADMIN")
//                .requestMatchers("/ciao").hasRole("MANAGER"));

//        http.authorizeHttpRequests(req->req.requestMatchers(HttpMethod.GET, "/a")
//                .authenticated()
//                .requestMatchers(HttpMethod.POST, "/a")
//                .permitAll()
//                .anyRequest()
//                .denyAll());

//        http.authorizeHttpRequests(
//                c -> c.requestMatchers("/product/{code:^[0-9]*$}")
//                        .permitAll()
//                        .anyRequest()
//                        .denyAll()
//        );

//        http.authorizeHttpRequests(
//                c -> c.requestMatchers("/email/{email:.*(?:.+@.+\\.com)}" ).permitAll()
//                        .anyRequest().denyAll());

        http.authorizeHttpRequests(
                c -> c.requestMatchers(".*/(us|uk|ca)+/(en|fr).*")
                        .authenticated()
                        .anyRequest().hasAuthority("premium"));

        http.csrf(c -> c.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();

//        var user1 = User.withUsername("john")
//                .password("12345")
//                .roles("ADMIN")
//                .build();
//
//        var user2 = User.withUsername("jane")
//                .password("12345")
//                .roles("MANAGER")
//                .build();

        var user1 = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        var user2 = User.withUsername("jane")
                .password("12345")
                .authorities("read", "premium")
                .build();

        manager.createUser(user1);
        manager.createUser(user2);

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
