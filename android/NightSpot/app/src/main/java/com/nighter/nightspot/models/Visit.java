package com.nighter.nightspot.models;



import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class Visit implements Serializable {

    private Long id;

    private User user;

    private Spot spot;

    private LocalDate visitDate;

    private LocalTime visitTime;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public LocalTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalTime visitTime) {
        this.visitTime = visitTime;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", user=" + user +
                ", spot=" + spot +
                ", visitDate=" + visitDate +
                ", visitTime=" + visitTime +
                '}';
    }
}

