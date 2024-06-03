package com.nighter.nightspot.models;


import java.io.Serializable;
import java.util.List;


public class Category implements Serializable {


    private Long id;

    private String name;

    List<Spot> spots;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", spots=" + spots +
                '}';
    }
}
