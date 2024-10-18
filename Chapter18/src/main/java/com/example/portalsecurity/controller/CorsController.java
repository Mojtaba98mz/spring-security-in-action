package com.example.portalsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CorsController {

    @GetMapping("/cors")
    public String cors() {
        return "home";
    }

    @PostMapping("/test")
    @ResponseBody
//    @CrossOrigin("http://localhost:8080")
    public String test() {
        System.out.println("#########> Test method called");
        return "HELLO";
    }
}