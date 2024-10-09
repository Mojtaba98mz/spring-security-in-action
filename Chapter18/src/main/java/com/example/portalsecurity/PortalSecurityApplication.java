package com.example.portalsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class PortalSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalSecurityApplication.class, args);
    }
}