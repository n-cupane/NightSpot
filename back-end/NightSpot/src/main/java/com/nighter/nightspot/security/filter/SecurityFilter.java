package com.nighter.nightspot.security.filter;

import com.nighter.nightspot.security.jwt.JWTUtilities;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JWTUtilities jwtUtilities;
    private final HandlerExceptionResolver resolver;

    public SecurityFilter(UserDetailsService userDetailsService, JWTUtilities jwtUtilities, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {

        this.userDetailsService = userDetailsService;
        this.jwtUtilities = jwtUtilities;
        this.resolver = resolver;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        SecurityContext context = SecurityContextHolder.getContext();
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer") || context.getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authorizationHeader.substring(7);
            String username = jwtUtilities.extractClaim(token, Claims::getSubject);
            UserDetails user = userDetailsService.loadUserByUsername(username);

            if (user == null) {
                filterChain.doFilter(request, response);
                return;
            }

            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            context.setAuthentication(upat);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
        }

    }
}
