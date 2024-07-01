package com.example.portalsecurity.controller.restController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController2 {
    //start 6.3.2
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    //end 6.3.2
}