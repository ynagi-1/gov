package org.example;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private int id;
    private String streetName;
    private String houseNumber;
    private double paymentPerSqM;
    private City city;
    private List<Room> rooms = new ArrayList<>();

    public Building(String streetName, String houseNumber, double paymentPerSqM, City city) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.paymentPerSqM = paymentPerSqM;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public double getPaymentPerSqM() {
        return paymentPerSqM;
    }

    public City getCity() {
        return city;
    }
}
