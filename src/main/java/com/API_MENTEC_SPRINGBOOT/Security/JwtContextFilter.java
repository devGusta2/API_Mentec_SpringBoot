package com.API_MENTEC_SPRINGBOOT.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Só entra se já tiver JWT validado pelo oauth2ResourceServer
        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            Jwt jwt = jwtAuth.getToken();

            // SUBJECT = userId (como você já faz no TokenController)
            String userId = jwt.getSubject();

            // Recria Authentication com principal = userId
            var newAuth = new JwtAuthenticationToken(
                    jwt,
                    jwtAuth.getAuthorities(),
                    userId
            );

            SecurityContextHolder.getContext().setAuthentication(newAuth);
        }

        filterChain.doFilter(request, response);
    }
}
