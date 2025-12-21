package controllers;

import java.sql.*;
import models.DatabaseConnection;
import models.User;

public class AuthController {
    
    public static User login(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("user_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("role"),
                    rs.getString("purok"),
                    rs.getInt("age"),
                    rs.getString("status"),
                    rs.getString("qr_code_id")
                );
            }
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return null;
    }
    
    public static boolean register(String firstName, String lastName, String email, 
                                  String password, String purok, int age) {
        String query = "INSERT INTO users (first_name, last_name, email, password, role, purok, age, qr_code_id) " +
                      "VALUES (?, ?, ?, ?, 'youth', ?, ?, ?)";
        
        String qrCodeId = "SK-YOUTH-" + System.currentTimeMillis();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setString(5, purok);
            stmt.setInt(6, age);
            stmt.setString(7, qrCodeId.substring(0, Math.min(qrCodeId.length(), 20)));
            
            int rows = stmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.out.println("Registration error: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean emailExists(String email) {
        String query = "SELECT user_id FROM users WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            System.out.println("Email check error: " + e.getMessage());
            return false;
        }
    }
}