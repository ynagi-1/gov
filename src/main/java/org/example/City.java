package org.example;

import java.util.ArrayList;
import java.util.List;

public class City {
    private int id;
    private String name;
    private List<Building> buildings = new ArrayList<>();

    public City(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
