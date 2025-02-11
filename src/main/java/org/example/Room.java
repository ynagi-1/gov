package org.example;

public class Room {
    private String roomNumber;
    private double area;
    private Building building;

    public Room(String roomNumber, double area, Building building) {
        this.roomNumber = roomNumber;
        this.area = area;
        this.building = building;
    }

    public double getArea() {
        return area;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Building getBuilding() {
        return building;
    }
}
