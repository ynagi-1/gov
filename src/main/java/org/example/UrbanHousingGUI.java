package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UrbanHousingGUI extends JFrame {
    private City city = new City("Metropolis");
    private JTextArea outputArea = new JTextArea();
    private JButton cityButton, buildingButton, roomButton;
    private JPanel actionPanel;

    public UrbanHousingGUI() {
        setTitle("Urban Housing Registry");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Панель для выбора (City, Building, Room)
        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        selectPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cityButton = new JButton("City");
        buildingButton = new JButton("Building");
        roomButton = new JButton("Room");

        selectPanel.add(cityButton);
        selectPanel.add(buildingButton);
        selectPanel.add(roomButton);
        add(selectPanel, BorderLayout.NORTH);

        // Панель для действий
        actionPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(actionPanel, BorderLayout.CENTER);

        // Текстовое поле для вывода информации
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        outputArea.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        cityButton.addActionListener(e -> showCityActions());
        buildingButton.addActionListener(e -> showBuildingActions());
        roomButton.addActionListener(e -> showRoomActions());
    }

    private void showCityActions() {
        actionPanel.removeAll();
        actionPanel.add(createButton("Add City", e -> addCity()));
        actionPanel.add(createButton("Show City", e -> showCity()));
        actionPanel.revalidate();
        actionPanel.repaint();
    }

    private void showBuildingActions() {
        actionPanel.removeAll();
        actionPanel.add(createButton("Add Building", e -> addBuilding()));
        actionPanel.add(createButton("Show Buildings", e -> showBuildings()));
        actionPanel.add(createButton("Remove Building", e -> removeBuilding()));
        actionPanel.add(createButton("Update Building", e -> updateBuilding()));
        actionPanel.revalidate();
        actionPanel.repaint();
    }

    private void showRoomActions() {
        actionPanel.removeAll();
        actionPanel.add(createButton("Add Room", e -> addRoom()));
        actionPanel.add(createButton("Show Rooms", e -> showRooms()));
        actionPanel.revalidate();
        actionPanel.repaint();
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(actionListener);
        return button;
    }

    private void addCity() {
        String cityName = JOptionPane.showInputDialog("Enter City Name:");
        if (cityName != null && !cityName.isEmpty()) {
            city.setName(cityName);
            outputArea.append("City updated to: " + cityName + "\n");
        }
    }

    private void showCity() {
        outputArea.setText("Current City: " + city.getName() + "\n");
    }
    

    private void addBuilding() {
        String street = JOptionPane.showInputDialog("Enter Street Name:");
        String house = JOptionPane.showInputDialog("Enter House Number:");
        double payment = Double.parseDouble(JOptionPane.showInputDialog("Enter Payment per SqM:"));

        Building building = new Building(street, house, payment, city);
        city.addBuilding(building);
        outputArea.append("Building added: " + street + " " + house + "\n");
    }

    private void showBuildings() {
        outputArea.setText("");
        if (city.getBuildings().isEmpty()) {
            outputArea.append("No buildings found.\n");
        } else {
            for (Building b : city.getBuildings()) {
                outputArea.append("Building: " + b.getStreetName() + " " + b.getHouseNumber() + ", Total Area: " + b.getTotalArea() + "\n");
            }
        }
    }

    private void removeBuilding() {
        String street = JOptionPane.showInputDialog("Enter Street Name:");
        String house = JOptionPane.showInputDialog("Enter House Number:");
        Building building = city.findBuilding(street, house);
        if (building != null) {
            city.removeBuilding(building);
            outputArea.append("Building removed: " + street + " " + house + "\n");
        } else {
            outputArea.append("Building not found: " + street + " " + house + "\n");
        }
    }

    private void updateBuilding() {
        String street = JOptionPane.showInputDialog("Enter Street Name:");
        String house = JOptionPane.showInputDialog("Enter House Number:");
        double payment = Double.parseDouble(JOptionPane.showInputDialog("Enter new Payment per SqM:"));

        Building building = city.findBuilding(street, house);
        if (building != null) {
            city.updateBuilding(street, house, payment);
            outputArea.append("Building updated: " + street + " " + house + "\n");
        } else {
            outputArea.append("Building not found: " + street + " " + house + "\n");
        }
    }

    private void addRoom() {
        String street = JOptionPane.showInputDialog("Enter the street name:");
        String house = JOptionPane.showInputDialog("Enter the house number:");
        Building building = city.findBuilding(street, house);
        if (building != null) {
            String roomNumber = JOptionPane.showInputDialog("Enter the room number:");
            double area = Double.parseDouble(JOptionPane.showInputDialog("Enter the room area:"));
            Room room = new Room(roomNumber, area, building);
            building.addRoom(room);
            outputArea.append("Room added: " + roomNumber + " to " + street + " " + house + "\n");
        } else {
            outputArea.append("Building not found: " + street + " " + house + "\n");
        }
    }

    private void showRooms() {
        String street = JOptionPane.showInputDialog("Enter Street Name:");
        String house = JOptionPane.showInputDialog("Enter House Number:");
        Building building = city.findBuilding(street, house);
        if (building != null) {
            outputArea.setText("Rooms in " + street + " " + house + ":\n");
            if (building.getRooms().isEmpty()) {
                outputArea.append("No rooms found.\n");
            } else {
                for (Room r : building.getRooms()) {
                    outputArea.append("Room: " + r.getRoomNumber() + ", Area: " + r.getArea() + "\n");
                }
            }
        } else {
            outputArea.append("Building not found: " + street + " " + house + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UrbanHousingGUI().setVisible(true));
    }
}
