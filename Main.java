public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 SK Community Management System");
        System.out.println("📅 " + new java.util.Date());
        
        // Initialize db
        System.out.println("\n🔧 Initializing database...");
        models.DatabaseConnection.initializeDatabase();
        
        // login screen
        javax.swing.SwingUtilities.invokeLater(() -> {
            new views.LoginFrame();
        });
    }
}