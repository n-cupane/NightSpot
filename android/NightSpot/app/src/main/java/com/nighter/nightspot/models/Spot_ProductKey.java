package com.nighter.nightspot.models;



import java.io.Serializable;


public class Spot_ProductKey implements Serializable {


    private Long spotId;

    private Long productId;

    public Long getSpotId() {
        return spotId;
    }

    public void setSpotId(Long spotId) {
        this.spotId = spotId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Spot_ProductKey{" +
                "spotId=" + spotId +
                ", productId=" + productId +
                '}';
    }
}
