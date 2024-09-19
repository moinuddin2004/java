package com.example.springSec;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    UserService service;
    @PostMapping("/register")
    public User register(@RequestBody User user ){
        return service.register(user) ;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return service.verifyUser(user );
    }
//    @PostMapping("/logout")
//    public void logout( HttpServletResponse response){
//        service.logout( response);
//    }
}
