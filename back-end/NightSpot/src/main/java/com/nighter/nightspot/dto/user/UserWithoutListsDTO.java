package com.nighter.nightspot.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserWithoutListsDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String instagramHandle;
    private LocalDate dateOfBirth;
    private byte[] photo;

}
