package com.kgpalle.moneymind.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {
    private final String KEY = "my-secret-token123456789@_abcdefghijklmnopqrstu"; // Replace with your own secret key
    private final SecretKey Key = Keys.hmacShaKeyFor(KEY.getBytes());

    public String generateToken(String username) {
        return Jwts.builder()
            .subject(username)
            .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 60 * 1000))
            .signWith(Key)
            .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }

    public Claims getClaimsFromToken(String token) {
     return Jwts
             .parser()
             .verifyWith(Key)
             .build()
             .parseSignedClaims(token)
             .getPayload();
    }
}
