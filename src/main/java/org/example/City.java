package org.example;

import java.util.ArrayList;
import java.util.List;

public class City {
    private int id;
    private String name;
    private List<Building> buildings = new ArrayList<>();
    public int getId() {
        return id;
    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
    }



    public void updateBuilding(String street, String house, double payment) {
        Building building = findBuilding(street, house);
        if (building != null) {
            building.updatePayment(payment);
        }
    }



    public Building findBuilding(String streetName, String houseNumber) {
        return buildings.stream()
                .filter(b -> b.getStreetName().equals(streetName) && b.getHouseNumber().equals(houseNumber))
                .findFirst()
                .orElse(null);
    }

    public List<Building> getBuildings() {
        return buildings;
    }


}

