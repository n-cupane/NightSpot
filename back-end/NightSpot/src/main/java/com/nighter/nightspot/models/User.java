package com.nighter.nightspot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String instagramHandle;
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] photo;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
    @OneToMany(mappedBy = "user")
    private List<Visit> visits;
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(name = "favorites",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "spot_id"))
    private List<Spot> spots;

}
