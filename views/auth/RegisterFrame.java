package views.auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame {
    private JTextField fullNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    
    public RegisterFrame() {
        setTitle("SK Connect - Register");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header
        JLabel headerLabel = new JLabel("Create Your SK Connect Account", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        
        // Full Name
        fullNameField = new JTextField();
        fullNameField.setBorder(BorderFactory.createTitledBorder("Full Name *"));
        
        // Email
        emailField = new JTextField();
        emailField.setBorder(BorderFactory.createTitledBorder("Email Address *"));
        
        // Password
        JPanel passwordPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password *"));
        
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBorder(BorderFactory.createTitledBorder("Confirm Password *"));
        
        passwordPanel.add(passwordField);
        passwordPanel.add(confirmPasswordField);
        
        // Register button
        JButton registerButton = new JButton("Register Account");
        registerButton.setBackground(new Color(40, 167, 69));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.addActionListener(e -> attemptRegister());
        
        // Back to login
        JButton backButton = new JButton("Already have an account? Log In Here");
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setForeground(new Color(0, 102, 204));
        backButton.addActionListener(e -> backToLogin());
        
        // Add components
        formPanel.add(fullNameField);
        formPanel.add(emailField);
        formPanel.add(passwordPanel);
        formPanel.add(registerButton);
        formPanel.add(backButton);
        
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private void attemptRegister() {
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        // Validation
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "All fields are required.",
                "Registration Failed",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                "Passwords do not match.",
                "Registration Failed",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(this,
            "Registration successful! You can now log in.",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
            
        // Go back to login
        backToLogin();
    }
    
    private void backToLogin() {
        new LoginFrame();
        this.dispose();
    }
}