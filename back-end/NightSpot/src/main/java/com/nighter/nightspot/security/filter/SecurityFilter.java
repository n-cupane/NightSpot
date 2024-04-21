package com.nighter.nightspot.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        SecurityContext securityContext = SecurityContextHolder.getContext();

        if (username == null || password == null || securityContext.getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (user == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        securityContext.setAuthentication(upat);
        filterChain.doFilter(request, response);

    }
}
