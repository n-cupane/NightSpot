package com.nighter.nightspot.controller;

import com.nighter.nightspot.dto.user.InsertUserDTO;
import com.nighter.nightspot.dto.user.UpdateUserDTO;
import com.nighter.nightspot.dto.user.UserDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.service.definition.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/insert")
    public ResponseEntity<Void> insertUser(@Valid @RequestBody InsertUserDTO user) {
        try {
            userService.save(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UpdateUserDTO user) {
        try {
            userService.save(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/show/id/{id}")
    public ResponseEntity<UserDTO> showUser(@PathVariable Long id) throws NoResultException {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/show-all")
    public ResponseEntity<List<UserDTO>> showAllUsers() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws NoResultException {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/show/email/{email}")
    public ResponseEntity<UserDTO> showUserByEmail(@PathVariable String email) throws NoResultException {
        UserDTO user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/show/username/{username}")
    public ResponseEntity<UserDTO> showUserByUsername(@PathVariable String username) throws NoResultException {
        UserDTO user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }
}
