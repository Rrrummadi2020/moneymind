package com.kgpalle.moneymind.config;

import com.kgpalle.moneymind.service.CustomUserDetailsService;
import com.kgpalle.moneymind.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private CustomUserDetailsService customUserDetailsService;

    public JwtAuthFilter(JWTUtil jwtUtil, CustomUserDetailsService customUserDetailsService ) {
            this.jwtUtil = jwtUtil;
            this.customUserDetailsService = customUserDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = bearerToken.substring(7);
        String username = this.jwtUtil.getUsernameFromToken(token);
        if (username == null || !jwtUtil.isTokenExpired(token)) {
            throw new ServletException("Invalid Token or expired header");
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new ServletException("Invalid Username in token");
        }
        SecurityContext context = SecurityContextHolder
                 .getContext();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
