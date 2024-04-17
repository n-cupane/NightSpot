package com.nighter.nightspot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
public class Spot_ProductKey implements Serializable {

    @Column(name = "spot_id")
    private Long spotId;
    @Column(name = "product_id")
    private Long productId;
}
