package com.kgpalle.moneymind.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserDetails {

    @Value("${app.username}")
    private String username;

    @Value("${app.password}")
    private String password;

    @GetMapping("/user-details")
    public String getUserDetails() {
        // Placeholder for actual user details retrieval logic
        return "User details will be displayed here." + "\nUsername: " + username + "\nPassword: " + password;
    }
    
}
