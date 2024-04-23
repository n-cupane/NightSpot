package com.nighter.nightspot.models;



import java.io.Serializable;


public class VisitKey implements Serializable {


    private Long userId;

    private Long spotId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSpotId() {
        return spotId;
    }

    public void setSpotId(Long spotId) {
        this.spotId = spotId;
    }

    @Override
    public String toString() {
        return "VisitKey{" +
                "userId=" + userId +
                ", spotId=" + spotId +
                '}';
    }
}
