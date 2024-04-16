package com.nighter.nightspot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] photo;

    @ManyToOne
    private Spot spot;
}
