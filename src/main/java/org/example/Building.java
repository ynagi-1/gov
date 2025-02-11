package org.example;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private String streetName;
    private String houseNumber;
    private double paymentPerSqM;
    private City city;
    private List<Room> rooms = new ArrayList<>();
    public double getPaymentPerSqM() {
        return paymentPerSqM;
    }

    public Building(String streetName, String houseNumber, double paymentPerSqM, City city) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.paymentPerSqM = paymentPerSqM;
        this.city = city;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public void updatePayment(double newPayment) {
        this.paymentPerSqM = newPayment;
    }

    public double getTotalArea() {
        return rooms.stream().mapToDouble(Room::getArea).sum();
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public City getCity() {
        return city;
    }
}
