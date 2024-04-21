package com.nighter.nightspot.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InsertUserDTO {

    @NotBlank(message = "first name must be present")
    private String firstName;
    @NotBlank(message = "last name must be present")
    private String lastName;
    @NotBlank(message = "username must be present")
    private String username;
    @NotBlank(message = "email must be present")
    private String email;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",
            message = "password must include at least 8 characters, one uppercase character," +
                    " one lowercase character, one special character and a number")
    private String password;
    private String instagramHandle;
    @Past(message = "date of birth must be in the past")
    private LocalDate dateOfBirth;
    private byte[] photo;

}
