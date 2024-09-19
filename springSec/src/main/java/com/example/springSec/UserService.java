package com.example.springSec;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo repo;
    @Autowired
    AuthenticationManager authenticationManager;
@Autowired
JWTService jwtService;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verifyUser(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
    if (authentication.isAuthenticated()){
        String token = jwtService.generateToken(user.getUsername());

//
//        Cookie cookie = new Cookie("authToken", token);
//        cookie.setHttpOnly(true); // Helps prevent XSS attacks
//        cookie.setSecure(true); // Only send over HTTPS
//        cookie.setPath("/"); // Cookie available to all paths
//        cookie.setMaxAge(3600); // Cookie expiration (in seconds)
//
//        response.addCookie(cookie);



        // Return success or any response as per your design
        return "User verified successfully    "+ token;
    }
    return "fail";
    }

//    public void logout(HttpServletResponse response) {
//        SecurityContextHolder.clearContext();
//
//        // Optionally: Provide a response or status
//        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
//    }

//    public String logout(HttpServletResponse response) {
//        Cookie cookie = new Cookie("authToken", null);
//        cookie.setHttpOnly(true); // Ensure the cookie is accessible only by the server
//        cookie.setSecure(true);   // Ensure the cookie is sent over HTTPS
//        cookie.setPath("/");      // Set the path to match the application context
//        cookie.setMaxAge(0);      // Set the cookie's maximum age to 0 to delete it
//
//        // Add the cookie to the response to remove it on the client side
//        response.addCookie(cookie);
//
//        return "logout";
//    }
}
