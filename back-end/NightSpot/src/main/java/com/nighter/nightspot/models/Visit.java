package com.nighter.nightspot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Visit {

    @EmbeddedId
    private VisitKey id = new VisitKey();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("spotId")
    @JoinColumn(name = "spot_id")
    private Spot spot;

    @Column(nullable = false)
    private LocalDate visitDate;
    @Column(nullable = false)
    private LocalTime visitTime;

}

