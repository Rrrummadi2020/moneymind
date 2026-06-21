package com.kgpalle.moneymind.controller;

import com.kgpalle.moneymind.dto.UserDTO;
import com.kgpalle.moneymind.service.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final TokenService tokenService;

    public AuthenticationController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody UserDTO userDTO) {
        return tokenService.authenticate(userDTO);
    }
}
