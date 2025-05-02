package com.Complete_login_module_JWT.Complete_login_module_JWT.JWT;


import com.Complete_login_module_JWT.Complete_login_module_JWT.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtService jwtService;

    @Autowired
    private UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Long userId = null ;
        String JWTtoken = null ;


        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            JWTtoken = authHeader.substring(7);
        }

        if (JWTtoken == null) {
            Cookie[] cookie = request.getCookies();
            if (cookie != null) {
                for (Cookie c : cookie) {
                    if("JWT".equals(c.getName())) {
                        JWTtoken = c.getValue();
                    }
                }
            }
        }


        if (JWTtoken == null) {
            filterChain.doFilter(request, response);
            return;
        }
        // 1.24
    }
}
