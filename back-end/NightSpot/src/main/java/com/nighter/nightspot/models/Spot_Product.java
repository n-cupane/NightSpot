package com.nighter.nightspot.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@Entity(name = "spot_product")
public class Spot_Product {

    @EmbeddedId
    private Spot_ProductKey id;

    @ManyToOne
    @MapsId("spotId")
    @JoinColumn(name = "spot_id")
    private Spot spot;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer price;


}
