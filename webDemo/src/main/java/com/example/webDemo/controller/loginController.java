package com.example.webDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {
    @RequestMapping("/login")
    public String greet(){
        return "hi moin hh";
    }
}
