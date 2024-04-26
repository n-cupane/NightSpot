package com.nighter.nightspot.controller;

import com.nighter.nightspot.dto.spot.SpotWithCategoryDTO;
import com.nighter.nightspot.dto.user.InsertUserDTO;
import com.nighter.nightspot.dto.user.UpdateUserDTO;
import com.nighter.nightspot.dto.user.UserDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.service.definition.SpotService;
import com.nighter.nightspot.service.definition.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "Authorization")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SpotService spotService;


    @PostMapping("/all/user/insert")
    public ResponseEntity<Void> insertUser(@Valid @RequestBody InsertUserDTO user) {
        try {
            userService.save(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/user/update")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UpdateUserDTO user) {
        try {
            userService.save(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/show/id/{id}")
    public ResponseEntity<UserDTO> showUser(@PathVariable Long id) throws NoResultException {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/admin/user/show-all")
    public ResponseEntity<List<UserDTO>> showAllUsers() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/admin/user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws NoResultException {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/show/email/{email}")
    public ResponseEntity<UserDTO> showUserByEmail(@PathVariable String email) throws NoResultException {
        UserDTO user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/auth/user/show/username/{username}")
    public ResponseEntity<UserDTO> showUserByUsername(@PathVariable String username) throws NoResultException {
        UserDTO user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/all/user/login")
    public ResponseEntity<String> login(@RequestBody UserDTO loggingUser) throws Exception {
        String token = userService.login(loggingUser.getUsername(), loggingUser.getPassword());
        return ResponseEntity
                .ok()
                .header("Authorization", token)
                .build();
    }

    @PostMapping("auth/user/add-favorite/{spotId}")
    public ResponseEntity<Void> addFavorite(@PathVariable Long spotId, UsernamePasswordAuthenticationToken upat) throws NoResultException {
        UserDetails userDetails = (UserDetails) upat.getPrincipal();
        UserDTO user = userService.findByUsername(userDetails.getUsername());
        SpotWithCategoryDTO spot = spotService.findByIdWithCategory(spotId);
        userService.addFavorite(spot, user);
        return ResponseEntity.ok().build();
    }

}
