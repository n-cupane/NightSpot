package com.nighter.nightspot.models;


import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Arrays;

public class Photo implements Serializable {


    private Long id;

    private String path;

    private Spot spot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  String getPath() {
        return path;
    }

    public void setPath( String path) {
        this.path = path;
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
                ", photo=" + path +
                ", spot=" + spot +
                '}';
    }
}
