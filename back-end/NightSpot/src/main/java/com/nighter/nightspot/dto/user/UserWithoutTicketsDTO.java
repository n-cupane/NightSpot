package com.nighter.nightspot.dto.user;

import com.nighter.nightspot.models.Review;
import com.nighter.nightspot.models.Spot;
import com.nighter.nightspot.models.Visit;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserWithoutTicketsDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String instagramHandle;
    private LocalDate dateOfBirth;
    private String photo;

//    private List<Visit> visits;
    private List<Review> reviews;
    private List<Spot> spots; // favorites
}
