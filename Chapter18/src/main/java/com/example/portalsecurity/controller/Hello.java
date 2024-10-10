package com.example.portalsecurity.controller;

import com.example.portalsecurity.service.NameService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Hello {

    @GetMapping(path = "/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/helloWithName")
    public String helloWithName(Authentication authentication) {
        return "hello ".concat(authentication.getName());
    }

    private final NameService nameService;

    @GetMapping("/helloWithNameService")
    public String helloWithNameService() {
        return "hello ".concat(nameService.getName());
    }

    @GetMapping("/demo")
    public String demo() {
        return "Demo";
    }
}