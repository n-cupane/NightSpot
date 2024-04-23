package com.nighter.nightspot.models;


import java.util.List;


public class Spot {


    private Long id;


    private String name;

    private String position;

    private String contact;

    private  String timetables;

    private Category category;

    private List<User> users;

    private List<Photo> photos;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTimetables() {
        return timetables;
    }

    public void setTimetables(String timetables) {
        this.timetables = timetables;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", contact='" + contact + '\'' +
                ", timetables='" + timetables + '\'' +
                ", category=" + category +
                ", users=" + users +
                ", photos=" + photos +
                '}';
    }
}
