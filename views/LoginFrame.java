package views;

import javax.swing.*;
import java.awt.*;
import models.DatabaseConnection;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleCombo;
    
    public LoginFrame() {
        setTitle("SK System - Login");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header - Matches PHP style
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("SK Connect", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(new Color(79, 70, 229)); // Indigo from PHP
        
        JLabel subLabel = new JLabel("Sign in to your account", SwingConstants.CENTER);
        subLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subLabel.setForeground(Color.GRAY);
        
        headerPanel.add(headerLabel, BorderLayout.NORTH);
        headerPanel.add(subLabel, BorderLayout.CENTER);
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        
        // Role selection - Like PHP
        JPanel rolePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel roleLabel = new JLabel("Login as:");
        roleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        String[] roles = {"Youth User", "SK Official"};
        roleCombo = new JComboBox<>(roles);
        roleCombo.setPreferredSize(new Dimension(150, 30));
        rolePanel.add(roleLabel);
        rolePanel.add(roleCombo);
        
        // Email field
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(300, 40));
        emailField.setBorder(BorderFactory.createTitledBorder("Email Address"));
        
        // Password field
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 40));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        
        // Login button - Same color as PHP
        JButton loginButton = new JButton("Log In");
        loginButton.setMaximumSize(new Dimension(300, 45));
        loginButton.setBackground(new Color(79, 70, 229)); // Indigo
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(e -> login());
        
        // Links panel - Matches PHP layout
        JPanel linksPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        linksPanel.setMaximumSize(new Dimension(300, 60));
        
        JButton registerButton = new JButton("New Youth User? Create an Account");
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setForeground(new Color(79, 70, 229));
        registerButton.addActionListener(e -> openRegister());
        
        JButton dbButton = new JButton("Test Database Connection");
        dbButton.setBorderPainted(false);
        dbButton.setContentAreaFilled(false);
        dbButton.setForeground(Color.DARK_GRAY);
        dbButton.addActionListener(e -> testDatabase());
        
        linksPanel.add(registerButton);
        linksPanel.add(dbButton);
        
        // Add components with spacing
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(rolePanel);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(loginButton);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(linksPanel);
        
        // Add to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        
        // Test database on startup
        new Thread(() -> {
            DatabaseConnection.testQuery();
        }).start();
        
        // Enter key to login
        getRootPane().setDefaultButton(loginButton);
    }
    
    private void login() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = (String) roleCombo.getSelectedItem();
        
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter both email and password.", 
                "Login Failed", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        //Demo
        if (email.equals("admin@sk.com") && password.equals("admin123") && role.equals("SK Official")) {
            JOptionPane.showMessageDialog(this, "Welcome Admin!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new AdminDashboard();
        } else if (email.equals("yth1@sk.com") && password.equals("youth123") && role.equals("Youth User")) {
            JOptionPane.showMessageDialog(this, "Welcome Youth!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new YouthDashboard();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Invalid email or password.", 
                "Login Failed", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void openRegister() {
        new RegisterFrame();
        dispose();
    }
    
    private void testDatabase() {
        DatabaseConnection.testQuery();
    }
}