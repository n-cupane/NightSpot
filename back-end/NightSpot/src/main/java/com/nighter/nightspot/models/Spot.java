package com.nighter.nightspot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity(name = "spot")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Column(name = "timetables", nullable = false)
    private  String timetables;

    @ManyToOne
    private Category category;

    @JsonIgnore
    @ManyToMany(mappedBy = "spots")
    private List<User> users;

}
