package com.ass.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FoodTruck {
    @Id
    @Column(name = "location_id")
    private int locationId;
    @Column(name = "applicant", nullable = true)
    private String applicant;
    @Column(name = "address", nullable = true)
    private String address;
    @Column(name = "block_lot", nullable = true)
    private String blockLot;
    @Column(name = "x")
    private double x;
    @Column(name = "y")
    private double y;

    public FoodTruck() {
    }

    public FoodTruck(int locationId, String applicant, String address, String blockLot, double x, double y) {
        this.locationId = locationId;
        this.applicant = applicant;
        this.address = address;
        this.blockLot = blockLot;
        this.x = x;
        this.y = y;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBlockLot() {
        return blockLot;
    }

    public void setBlockLot(String blockLot) {
        this.blockLot = blockLot;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
