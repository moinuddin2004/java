package com.example.webDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {
   @RequestMapping("/")
    public String greet(){
        return "hi moin uddin";
    }
}
