package com.example.portalsecurity.security.config;

import com.example.portalsecurity.security.authenticationProvider.AuthenticationProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Deprecated
@Configuration
public class SecurityConfig {

    //start 2.3.1
    //@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    //@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("admin")
                .password("123")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
    //end 2.3.1


    //start 2.3.2
    //@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic(Customizer.withDefaults());

        //All the requests require authentication
        httpSecurity.authorizeHttpRequests(authorization -> authorization.anyRequest().authenticated());

        return httpSecurity.build();
    }

    //@Bean
    public SecurityFilterChain securityFilterChain1(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic(Customizer.withDefaults());

        //None of the requests need to be authenticated
        httpSecurity.authorizeHttpRequests(authorization -> authorization.anyRequest().permitAll());

        return httpSecurity.build();
    }
    //end 2.3.2


    //start 2.3.3 Configuring in different ways
    //@Bean
    public SecurityFilterChain securityFilterChain2(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic(Customizer.withDefaults());

        //All the requests require authentication
        httpSecurity.authorizeHttpRequests(authorization -> authorization.anyRequest().authenticated());

        //Defines a user with all its details
        var user = User.withUsername("admin")
                .password("123")
                .authorities("read")
                .build();

        //Declares a UserDetailsSevice to store the users in memory and adds the user to be managed by our UserDetailsSevice
        var userDetailsService = new InMemoryUserDetailsManager(user);

        //The UserDetailsService is now set up using the SecurityFilterChain bean
        httpSecurity.userDetailsService(userDetailsService);

        return httpSecurity.build();
    }
    //end 2.3.3

    //start 2.3.4
    private final AuthenticationProviderImpl authenticationProvider;

    @Autowired
    public SecurityConfig(AuthenticationProviderImpl authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    //@Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.authenticationProvider(authenticationProvider);
        httpSecurity.authorizeHttpRequests(authorization -> authorization.anyRequest().authenticated());
        return httpSecurity.build();
    }
    //end 2.3.4
}