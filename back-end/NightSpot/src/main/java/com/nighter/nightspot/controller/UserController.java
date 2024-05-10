package com.nighter.nightspot.controller;

import com.nighter.nightspot.dto.spot.SpotWithCategoryDTO;
import com.nighter.nightspot.dto.user.InsertUserDTO;
import com.nighter.nightspot.dto.user.UpdateUserDTO;
import com.nighter.nightspot.dto.user.UserDTO;
import com.nighter.nightspot.dto.validation.ValidationErrorMessageDTO;
import com.nighter.nightspot.error.exception.NoResultException;
import com.nighter.nightspot.models.Role;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.UploadImageRequest;
import com.nighter.nightspot.models.User;
import com.nighter.nightspot.service.definition.SpotService;
import com.nighter.nightspot.service.definition.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "Authorization")
@Tag(name = "User API", description = "User management API")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SpotService spotService;


    @PostMapping("/all/user/insert")
    @Operation(summary = "user sign up", description = "Given a InsertUserDTO object it will be inserted provided all" +
            " mandatory fields are populated, password is complex enough and username and email are not already in use")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "user is inserted"),
            @ApiResponse(responseCode = "400", description = "User fields are not correctly populated",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ValidationErrorMessageDTO.class,
                                    description = "error message displaying invalid fields"))),
    })
    public ResponseEntity<Void> insertUser(@Valid @RequestBody InsertUserDTO user) {
        try {
            userService.save(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/auth/user/update")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UpdateUserDTO user) {
        try {
            userService.save(user);
            System.out.println(user);
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

    @GetMapping("/admin/user/show-admins")
    public ResponseEntity<List<UserDTO>> showAdmins() {
        List<UserDTO> users = userService.findAllAdmins();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/super/user/role/{id}/{role}")
    public ResponseEntity<Void> updateRole(@PathVariable Long id, @PathVariable int role) throws NoResultException {
        Role roleAsEnum = (role == 1) ? Role.ADMIN : Role.BASE;
        userService.updateRole(id, roleAsEnum);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/all/photo/upload/{username}")
    public ResponseEntity<Void> uploadPhoto(@RequestBody UploadImageRequest image, @PathVariable String username) {
        try {
            userService.uploadPhoto(image, username);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



    @PostMapping("auth/user/remove-favorite/{userId}")
    public ResponseEntity<Void> removeFavorite (@PathVariable Long userId, @RequestBody Spot spot) throws NoResultException {
        userService.updateUserSpots(userId, spot);
        return ResponseEntity.ok().build();
    }

}
