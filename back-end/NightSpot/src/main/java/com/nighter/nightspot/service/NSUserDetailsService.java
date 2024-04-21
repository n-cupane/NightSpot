package com.nighter.nightspot.service;

import com.nighter.nightspot.repository.UserRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NSUserDetailsService implements UserDetailsService {

    private final UserRepositoryJPA repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Username not found: " + username)
                );
    }

}
