package com.nighter.nightspot.models;


import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Arrays;

public class Photo implements Serializable {


    private Long id;

    private String photo;

    private Spot spot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  String getPhoto() {
        return photo;
    }

    public void setPhoto( String photo) {
        this.photo = photo;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", photo=" + photo +
                ", spot=" + spot +
                '}';
    }
}
