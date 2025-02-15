package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/goverment?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Aza1983aza";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static void addCity(City city) {
        String query = "INSERT INTO City (name) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, city.getName());
            stmt.executeUpdate();


            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                city.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateCity(City city) {
        String query = "UPDATE City SET name = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, city.getName());
            stmt.setInt(2, city.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteCity(int cityId) {
        String query = "DELETE FROM City WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cityId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void addBuilding(Building building) {
        String query = "INSERT INTO Building (street_name, house_number, payment_per_sqm, city_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, building.getStreetName());
            stmt.setString(2, building.getHouseNumber());
            stmt.setDouble(3, building.getPaymentPerSqM());
            stmt.setInt(4, building.getCity().getId());
            stmt.executeUpdate();


            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                building.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateBuilding(Building building) {
        String query = "UPDATE Building SET payment_per_sqm = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, building.getPaymentPerSqM());
            stmt.setInt(2, building.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteBuilding(int buildingId) {
        String query = "DELETE FROM Building WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, buildingId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void addRoom(Room room) {
        String query = "INSERT INTO Room (room_number, area, building_id) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, room.getRoomNumber());
            stmt.setDouble(2, room.getArea());
            stmt.setInt(3, room.getBuilding().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteRoom(int roomId) {
        String query = "DELETE FROM Room WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, roomId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
