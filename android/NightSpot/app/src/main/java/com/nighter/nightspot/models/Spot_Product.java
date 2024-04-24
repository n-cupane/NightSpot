package com.nighter.nightspot.models;



import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;



public class Spot_Product implements Serializable {

    private Spot_ProductKey id;

    private Spot spot;

    private Product product;

    private Double price;

    public Spot_ProductKey getId() {
        return id;
    }

    public void setId(Spot_ProductKey id) {
        this.id = id;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Spot_Product{" +
                "id=" + id +
                ", spot=" + spot +
                ", product=" + product +
                ", price=" + price +
                '}';
    }
}
