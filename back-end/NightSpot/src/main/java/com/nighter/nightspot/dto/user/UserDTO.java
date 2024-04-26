package com.nighter.nightspot.dto.user;

import com.nighter.nightspot.dto.ticket.TicketWithoutUserDTO;
import com.nighter.nightspot.dto.visit.VisitWithoutUser;
import com.nighter.nightspot.models.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserDTO {


    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String instagramHandle;
    private LocalDate dateOfBirth;
    private byte[] photo;

    private List<TicketWithoutUserDTO> tickets;
    private List<VisitWithoutUser> visits;
    private List<Review> reviews;
    private List<Spot> spots; // favorites

    private Role role;
}