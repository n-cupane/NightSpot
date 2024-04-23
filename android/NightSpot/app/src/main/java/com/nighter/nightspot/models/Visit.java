package com.nighter.nightspot.models;



import java.time.LocalDate;
import java.time.LocalTime;


public class Visit {

    private VisitKey id = new VisitKey();

    private User user;

    private Spot spot;

    private LocalDate visitDate;

    private LocalTime visitTime;

    public VisitKey getId() {
        return id;
    }

    public void setId(VisitKey id) {
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

