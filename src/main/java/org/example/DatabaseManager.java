package org.example;
import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/goverment?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Aza1983aza";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void addBuilding(Building building) {
        String query = "INSERT INTO Building (street_name, house_number, payment_per_sqm, city_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, building.getStreetName());
            stmt.setString(2, building.getHouseNumber());
            stmt.setDouble(3, building.getPaymentPerSqM());
            stmt.setInt(4, building.getCity().getId());  // Пример использования cityId
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


