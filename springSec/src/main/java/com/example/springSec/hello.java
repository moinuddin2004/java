package com.example.springSec;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {
    @GetMapping("/")
    public String greet(HttpServletRequest request) {
        return "hi moin"+request.getSession().getId();
    }
}
