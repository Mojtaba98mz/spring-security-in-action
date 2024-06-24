package com.example.portalsecurity.security.user;

import com.example.portalsecurity.controller.dto.UserDto;
import com.example.portalsecurity.security.password.Sha512PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class UserManagementConfig {

    //start 2.3.5
    //@Bean
    public PasswordEncoder passwordEncoder1() {
        return NoOpPasswordEncoder.getInstance();
    }
    //end 2.3.5

    //start 4.1.2
    @Bean
    public PasswordEncoder passwordEncoder2() {
        return new Sha512PasswordEncoder();
    }
    //end 4.1.2

    //start 4.1.4
    //@Bean         TODO : doesn't worked
    public PasswordEncoder passwordEncoder3() {
        Map<String, PasswordEncoder> encoderMap = new HashMap<>();
        encoderMap.put("noop", NoOpPasswordEncoder.getInstance());
        encoderMap.put("bcrypt", new BCryptPasswordEncoder());
        encoderMap.put("scrypt", new SCryptPasswordEncoder(
                16384, 8, 11, 32, 64));
        return new DelegatingPasswordEncoder("bcrypt", encoderMap);
    }
    //end 4.1.4


    //start 4.2.2 TODO : work on it later ?
    //@Bean
    public PasswordEncoder passwordEncoder4() {
        String string = KeyGenerators.string().generateKey();
        byte[] secure = KeyGenerators.secureRandom().generateKey();
        byte[] shared = KeyGenerators.shared(16).generateKey();

        String password = "secret";
        String valueToEncrypt = "HELLO";

        BytesEncryptor e = Encryptors.standard(password, string);
        byte[] encrypted = e.encrypt(valueToEncrypt.getBytes());
        byte[] decrypted = e.decrypt(encrypted);
        return null;
    }
    //end 4.2.2

    //----------------------------------------------------------------

    //@Bean
    public UserDetailsService userDetailsService1() {
        UserDetails admin = User
                .withUsername("admin")
                .password("123")
                .roles("read")
                //start 3.2.2
                .authorities(() -> "Read") // Or :
                .authorities(new SimpleGrantedAuthority("Read"))
                //end 3.2.2
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
    //end 2.3.5

    //start 3.3.2
    //@Bean
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