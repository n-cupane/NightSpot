package com.nighter.nightspot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "spot_id")
    private Spot spot;

    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private Integer rating;
    @Column(nullable = false)
    private LocalDate date;
}
