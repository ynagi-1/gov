package org.example;

public class Room {
    private int id;
    private String roomNumber;
    private double area;
    private Building building;

    public Room(String roomNumber, double area, Building building) {
        this.roomNumber = roomNumber;
        this.area = area;
        this.building = building;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {  // Метод для установки ID
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getArea() {
        return area;
    }

    public Building getBuilding() {
        return building;
    }
}
