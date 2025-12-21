// views/auth/LoginFrame.java
package views.auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    
    public LoginFrame() {
        setTitle("SK Connect - Login");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header
        JLabel headerLabel = new JLabel("SK Connect", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(0, 102, 204));
        
        // Subtitle
        JLabel subLabel = new JLabel("Sign in to your account", SwingConstants.CENTER);
        subLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        
        // Role selection
        String[] roles = {"Youth User", "SK Official"};
        roleComboBox = new JComboBox<>(roles);
        
        // Email field
        emailField = new JTextField();
        emailField.setBorder(BorderFactory.createTitledBorder("Email Address"));
        
        // Password field
        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        
        // Login button
        JButton loginButton = new JButton("Log In");
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(e -> attemptLogin());
        
        // Register link
        JButton registerButton = new JButton("New Youth User? Create an Account");
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setForeground(new Color(0, 102, 204));
        registerButton.addActionListener(e -> openRegister());
        
        // Add components to form
        formPanel.add(roleComboBox);
        formPanel.add(emailField);
        formPanel.add(passwordField);
        formPanel.add(loginButton);
        formPanel.add(registerButton);
        
        // Add to main panel
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(subLabel, BorderLayout.CENTER);
        mainPanel.add(formPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Enter key to login
        getRootPane().setDefaultButton(loginButton);
    }
    
    private void attemptLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = (String) roleComboBox.getSelectedItem();
        
        // Simple validation
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter both email and password.", 
                "Login Failed", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // DEMO LOGIN - Replace with database check later
        if (email.equals("admin@sk.com") && password.equals("admin123") && role.equals("SK Official")) {
            JOptionPane.showMessageDialog(this, "Welcome Admin!");
            dispose(); // Close login window
            new AdminDashboardFrame();
        } else if (email.equals("youth@sk.com") && password.equals("youth123") && role.equals("Youth User")) {
            JOptionPane.showMessageDialog(this, "Welcome Youth User!");
            dispose();
            new YouthDashboardFrame();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Invalid email or password.", 
                "Login Failed", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void openRegister() {
        new RegisterFrame();
        this.dispose();
    }
}