package com.example.portalsecurity.security.user;

import com.example.portalsecurity.controller.dto.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class UserManagementConfig {

    //start 3.2.2
    //@Bean
    public UserDetailsService userDetailsService1() {
        UserDetails admin = User
                .withUsername("admin")
                .password("123")
                .roles("read")
                .authorities(() -> "Read") // Or :
                .authorities(new SimpleGrantedAuthority("Read"))
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
    //end 3.2.2

    //start 3.3.2
    @Bean
    public UserDetailsService userDetailsService2() {
        UserDetails admin = new UserDto(
                null,
                "admin",
                "123",
                "read",
                true,
                true,
                true,
                true);
        UserDetails user = new UserDto(
                null,
                "user",
                "123",
                "read",
                true,
                true,
                true,
                true);
        UserDetails employee = new UserDto(
                null,
                "employee",
                "123",
                "read",
                true,
                true,
                true,
                true);
        return new InMemoryUserDetailsServiceImpl(List.of(admin, user, employee));
    }
    //end 3.3.2

    //start 3.3.3  with Jdbc
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {

        //TODO Check : inja vaqti alias gozashtam error bad grammer sql dad ->
        // "select u.id, u.username, u.password, u.is_enabled from users u where username = ?"
        String usernameQuery = "select username, password, is_enabled from users where username = ?";

        String authQuery = "select * from user_role ur " +
                "join users u on ur.users = u.id " +
                "join role r on ur.role = r.id " +
                "where u.username = ?";

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(usernameQuery);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(authQuery);
        return jdbcUserDetailsManager;
    }
    //end 3.3.3
}