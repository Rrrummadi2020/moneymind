package com.kgpalle.moneymind.service;

import com.kgpalle.moneymind.dto.UserDTO;
import com.kgpalle.moneymind.util.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.vault.authentication.UsernamePasswordAuthentication;

@Service
public class TokenService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public TokenService(AuthenticationManager authenticationManager,
                        JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(UserDTO userDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDTO.getUsername(),
                            userDTO.getPassword()
                    )
            );
            return jwtUtil.generateToken(userDTO.getUsername());
        } catch (Exception ex) {
            throw ex;
        }
    }
}
