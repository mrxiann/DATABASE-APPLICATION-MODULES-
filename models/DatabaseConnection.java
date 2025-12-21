package models;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/sk_system";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static Connection connection = null;
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found!");
            System.out.println("   Make sure mysql-connector-j-9.5.0.jar is in project folder");
        }
    }
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed: " + e.getMessage());
            System.out.println("   Make sure:");
            System.out.println("   1. XAMPP is running (Apache & MySQL)");
            System.out.println("   2. Database 'sk_system' exists");
            System.out.println("   3. Run setup_database.sql in phpMyAdmin");
        }
        return connection;
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void initializeDatabase() {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("✅ Connected to database: sk_system");
            
            // Check if tables exist
            try {
                DatabaseMetaData meta = conn.getMetaData();
                ResultSet tables = meta.getTables(null, null, "users", null);
                if (!tables.next()) {
                    System.out.println("⚠️ Tables not found. Please run setup_database.sql");
                } else {
                    System.out.println("✅ Database tables verified");
                }
            } catch (SQLException e) {
                System.out.println("❌ Error checking tables: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Cannot initialize database");
        }
    }
    
    // Test method
    public static void testQuery() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM users")) {
            
            if (rs.next()) {
                System.out.println("✅ Database test successful. Users count: " + rs.getInt("count"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Database test failed: " + e.getMessage());
        }
    }
}