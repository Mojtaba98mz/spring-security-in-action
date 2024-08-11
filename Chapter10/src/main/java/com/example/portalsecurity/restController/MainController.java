package com.example.portalsecurity.restController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "home";
    }

    @PostMapping("/test")
    @ResponseBody
//    @CrossOrigin("http://localhost:9090")
    public String test() {
        System.out.println("#################################> Test method called");
        return "HELLO";
    }

}