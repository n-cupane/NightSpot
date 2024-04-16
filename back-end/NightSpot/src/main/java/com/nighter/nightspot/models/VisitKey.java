package com.nighter.nightspot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class VisitKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "spot_id")
    private Long spotId;

}
