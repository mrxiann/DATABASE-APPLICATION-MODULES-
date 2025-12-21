import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SK Community Management System ===");
        System.out.println("Starting application...");
        
        SwingUtilities.invokeLater(() -> {
            createLoginWindow();
        });
    }
    
    private static void createLoginWindow() {
        JFrame frame = new JFrame("SK System - Login");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header
        JLabel header = new JLabel("SK Connect", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setForeground(new Color(79, 70, 229));
        
        JLabel subheader = new JLabel("Sign in to your account", SwingConstants.CENTER);
        subheader.setFont(new Font("Arial", Font.PLAIN, 14));
        subheader.setForeground(Color.GRAY);
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        
        // Email
        JTextField emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(300, 35));
        emailField.setBorder(BorderFactory.createTitledBorder("Email Address"));
        
        // Password
        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 35));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        
        // Role selection
        String[] roles = {"Youth User", "SK Official"};
        JComboBox<String> roleCombo = new JComboBox<>(roles);
        roleCombo.setMaximumSize(new Dimension(300, 35));
        
        // Login button
        JButton loginButton = new JButton("Log In");
        loginButton.setMaximumSize(new Dimension(300, 40));
        loginButton.setBackground(new Color(79, 70, 229));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String role = (String) roleCombo.getSelectedItem();
            
            if (email.equals("admin@sk.com") && role.equals("SK Official")) {
                JOptionPane.showMessageDialog(frame, "Welcome Admin!");
                frame.dispose();
                createAdminDashboard();
            } else if (email.equals("youth@sk.com") && role.equals("Youth User")) {
                JOptionPane.showMessageDialog(frame, "Welcome Youth!");
                frame.dispose();
                createYouthDashboard();
            } else {
                JOptionPane.showMessageDialog(frame, "Use: admin@sk.com or youth@sk.com");
            }
        });
        
        // Add components
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(roleCombo);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(loginButton);
        
        // Add to main panel
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(subheader, BorderLayout.CENTER);
        mainPanel.add(formPanel, BorderLayout.SOUTH);
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    private static void createAdminDashboard() {
        JFrame frame = new JFrame("Admin Dashboard");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(30, 64, 175));
        sidebar.setPreferredSize(new Dimension(200, 600));
        
        // Content
        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        
        JTextArea info = new JTextArea();
        info.setText("✅ Admin Dashboard is working!\n\n" +
                    "Features:\n" +
                    "• User Management\n" +
                    "• Event Management\n" +
                    "• Attendance Tracking\n" +
                    "• Feedback Management");
        info.setEditable(false);
        info.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> {
            frame.dispose();
            createLoginWindow();
        });
        
        content.add(title, BorderLayout.NORTH);
        content.add(info, BorderLayout.CENTER);
        content.add(logout, BorderLayout.SOUTH);
        
        frame.add(sidebar, BorderLayout.WEST);
        frame.add(content, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    private static void createYouthDashboard() {
        JFrame frame = new JFrame("Youth Dashboard");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(79, 70, 229));
        sidebar.setPreferredSize(new Dimension(200, 600));
        
        // Content
        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("Youth Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        
        JTextArea info = new JTextArea();
        info.setText("✅ Youth Dashboard is working!\n\n" +
                    "Features:\n" +
                    "• View Events\n" +
                    "• Job Opportunities\n" +
                    "• My QR Code\n" +
                    "• Submit Feedback\n" +
                    "• Update Profile");
        info.setEditable(false);
        info.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> {
            frame.dispose();
            createLoginWindow();
        });
        
        content.add(title, BorderLayout.NORTH);
        content.add(info, BorderLayout.CENTER);
        content.add(logout, BorderLayout.SOUTH);
        
        frame.add(sidebar, BorderLayout.WEST);
        frame.add(content, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}