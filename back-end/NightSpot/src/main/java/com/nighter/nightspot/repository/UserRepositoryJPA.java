package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJPA extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

}
