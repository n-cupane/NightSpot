package com.nighter.nightspot.security.config;

import com.nighter.nightspot.models.Role;
import com.nighter.nightspot.security.filter.SecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class NSWebSecurityConfig {

    private final SecurityFilter filter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(t -> {
                    t.requestMatchers("/super/**").hasRole(Role.SUPER_ADMIN.getName());
                    t.requestMatchers("/admin/**").hasAnyRole(Role.ADMIN.getName(), Role.SUPER_ADMIN.getName());
                    t.requestMatchers("/auth/**").authenticated();
                    t.requestMatchers("/all/**").permitAll();
                    t.requestMatchers("/images/**").permitAll();
                })
                .csrf(t -> t.disable())
                .sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return t-> t.ignoring().requestMatchers("/images/**");
//    }

}
