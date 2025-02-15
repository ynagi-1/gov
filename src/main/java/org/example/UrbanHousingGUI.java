package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UrbanHousingGUI extends JFrame {
    private JTextArea outputArea = new JTextArea();
    private JButton cityButton, buildingButton, roomButton;
    private JPanel actionPanel;

    public UrbanHousingGUI() {
        setTitle("Urban Housing Registry");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        selectPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cityButton = new JButton("City");
        buildingButton = new JButton("Building");
        roomButton = new JButton("Room");

        selectPanel.add(cityButton);
        selectPanel.add(buildingButton);
        selectPanel.add(roomButton);
        add(selectPanel, BorderLayout.NORTH);

        actionPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(actionPanel, BorderLayout.CENTER);

        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        outputArea.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        cityButton.addActionListener(e -> showCityActions());
        buildingButton.addActionListener(e -> showBuildingActions());
        roomButton.addActionListener(e -> showRoomActions());
    }

    // CITY METHODS
    private void showCityActions() {
        actionPanel.removeAll(); // Очистка панели перед добавлением новых кнопок

        // "Add City"
        JButton addCityButton = new JButton("Add City");
        addCityButton.addActionListener(e -> addCity());
        actionPanel.add(addCityButton);

        // "Show Cities"
        JButton showCitiesButton = new JButton("Show Cities");
        showCitiesButton.addActionListener(e -> showCities());
        actionPanel.add(showCitiesButton);

        actionPanel.revalidate(); // Перерисовываем панель
        actionPanel.repaint();
    }


    private void addCity() {
        String cityName = JOptionPane.showInputDialog("Enter City Name:");
        try (Connection conn = DatabaseManager.getConnection()) {
            String query = "INSERT INTO City (name) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, cityName);
                stmt.executeUpdate();
                outputArea.append("City added: " + cityName + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showCities() {
        outputArea.setText("");
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM City");
             ResultSet rs = stmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                outputArea.append("No cities found.\n");
            } else {
                while (rs.next()) {
                    String cityName = rs.getString("name");
                    outputArea.append("City: " + cityName + "\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //BUILDING METHODS
    private void showBuildingActions() {
        actionPanel.removeAll(); // Очищаем панель перед добавлением новых кнопок

        //"Add Building"
        JButton addBuildingButton = new JButton("Add Building");
        addBuildingButton.addActionListener(e -> addBuilding());
        actionPanel.add(addBuildingButton);

        //"Show Buildings"
        JButton showBuildingsButton = new JButton("Show Buildings");
        showBuildingsButton.addActionListener(e -> showBuildings());
        actionPanel.add(showBuildingsButton);

        //"Remove Building"
        JButton removeBuildingButton = new JButton("Remove Building");
        removeBuildingButton.addActionListener(e -> removeBuilding());
        actionPanel.add(removeBuildingButton);


        actionPanel.revalidate();
        actionPanel.repaint();
    }


    private void addBuilding() {
        String street = JOptionPane.showInputDialog("Enter Street Name:");
        String house = JOptionPane.showInputDialog("Enter House Number:");
        double payment = Double.parseDouble(JOptionPane.showInputDialog("Enter Payment per SqM:"));

        try (Connection conn = DatabaseManager.getConnection()) {
            String query = "INSERT INTO Building (street_name, house_number, payment_per_sqm) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, street);
                stmt.setString(2, house);
                stmt.setDouble(3, payment);
                stmt.executeUpdate();
                outputArea.append("Building added: " + street + " " + house + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showBuildings() {
        outputArea.setText("");
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Building");
             ResultSet rs = stmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                outputArea.append("No buildings found.\n");
            } else {
                while (rs.next()) {
                    String street = rs.getString("street_name");
                    String house = rs.getString("house_number");
                    double payment = rs.getDouble("payment_per_sqm");
                    outputArea.append("Building: " + street + " " + house + ", Payment per SqM: " + payment + "\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeBuilding() {
        String street = JOptionPane.showInputDialog("Enter Street Name:");
        String house = JOptionPane.showInputDialog("Enter House Number:");

        try (Connection conn = DatabaseManager.getConnection()) {
            String query = "DELETE FROM Building WHERE street_name = ? AND house_number = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, street);
                stmt.setString(2, house);
                int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                    outputArea.append("Building removed: " + street + " " + house + "\n");
                } else {
                    outputArea.append("Building not found: " + street + " " + house + "\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //  ROOM METHODS
    private void showRoomActions() {
        actionPanel.removeAll();

        // "Add Room"
        JButton addRoomButton = new JButton("Add Room");
        addRoomButton.addActionListener(e -> addRoom());
        actionPanel.add(addRoomButton);

        //  "Show Rooms"
        JButton showRoomsButton = new JButton("Show Rooms");
        showRoomsButton.addActionListener(e -> showRooms());
        actionPanel.add(showRoomsButton);

        actionPanel.revalidate();
        actionPanel.repaint();
    }


    private void addRoom() {
        String street = JOptionPane.showInputDialog("Enter Building Street:");
        String house = JOptionPane.showInputDialog("Enter Building Number:");
        int roomNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Room Number:"));
        double size = Double.parseDouble(JOptionPane.showInputDialog("Enter Room Size (sqm):"));

        try (Connection conn = DatabaseManager.getConnection()) {
            String query = "INSERT INTO Room (street_name, house_number, room_number, size) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, street);
                stmt.setString(2, house);
                stmt.setInt(3, roomNumber);
                stmt.setDouble(4, size);
                stmt.executeUpdate();
                outputArea.append("Room added: " + roomNumber + " in " + street + " " + house + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showRooms() {
        outputArea.setText("");
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Room");
             ResultSet rs = stmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                outputArea.append("No rooms found.\n");
            } else {
                while (rs.next()) {
                    String street = rs.getString("street_name");
                    String house = rs.getString("house_number");
                    int roomNumber = rs.getInt("room_number");
                    double size = rs.getDouble("size");
                    outputArea.append("Room " + roomNumber + " in " + street + " " + house + ", Size: " + size + " sqm\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UrbanHousingGUI().setVisible(true));
    }
}
