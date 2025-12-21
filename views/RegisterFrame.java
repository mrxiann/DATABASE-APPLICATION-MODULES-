package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField ageField;
    private JComboBox<String> purokCombo;
    
    public RegisterFrame() {
        setTitle("SK System - Register");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        // Main panel - matches PHP style
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header - matches PHP register.php
        JLabel headerLabel = new JLabel("Create Your SK Connect Account", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(new Color(79, 70, 229));
        
        JLabel subLabel = new JLabel("Register as a Youth User to get started.", SwingConstants.CENTER);
        subLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subLabel.setForeground(Color.GRAY);
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(headerLabel, BorderLayout.NORTH);
        headerPanel.add(subLabel, BorderLayout.SOUTH);
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        
        // Name fields - matches PHP
        firstNameField = new JTextField();
        firstNameField.setMaximumSize(new Dimension(300, 40));
        firstNameField.setBorder(BorderFactory.createTitledBorder("First Name *"));
        
        lastNameField = new JTextField();
        lastNameField.setMaximumSize(new Dimension(300, 40));
        lastNameField.setBorder(BorderFactory.createTitledBorder("Last Name *"));
        
        // Email field
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(300, 40));
        emailField.setBorder(BorderFactory.createTitledBorder("Email Address *"));
        
        // Age field
        ageField = new JTextField();
        ageField.setMaximumSize(new Dimension(300, 40));
        ageField.setBorder(BorderFactory.createTitledBorder("Age *"));
        
        // Purok selection - matches PHP filter
        String[] puroks = {"Purok 1", "Purok 2", "Purok 3", "Purok 4", "Purok 5"};
        purokCombo = new JComboBox<>(puroks);
        purokCombo.setMaximumSize(new Dimension(300, 40));
        purokCombo.setBorder(BorderFactory.createTitledBorder("Purok *"));
        
        // Password fields - matches PHP layout
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 40));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password *"));
        
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setMaximumSize(new Dimension(300, 40));
        confirmPasswordField.setBorder(BorderFactory.createTitledBorder("Confirm Password *"));
        
        passwordPanel.add(passwordField);
        passwordPanel.add(Box.createVerticalStrut(10));
        passwordPanel.add(confirmPasswordField);
        
        // Register button - green like PHP
        JButton registerButton = new JButton("Register Account");
        registerButton.setMaximumSize(new Dimension(300, 45));
        registerButton.setBackground(new Color(40, 167, 69)); // Green like PHP
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.addActionListener(e -> register());
        
        // Back link - matches PHP
        JButton backButton = new JButton("Already have an account? Log In Here");
        backButton.setMaximumSize(new Dimension(300, 30));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setForeground(new Color(79, 70, 229));
        backButton.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });
        
        // Add components
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(firstNameField);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(lastNameField);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(ageField);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(purokCombo);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(passwordPanel);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(registerButton);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(backButton);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        
        // Enter key to register
        getRootPane().setDefaultButton(registerButton);
    }
    
    private void register() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String ageStr = ageField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String purok = (String) purokCombo.getSelectedItem();
        
        // Validation - matches PHP validation
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || ageStr.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Error: All fields are required.", 
                "Registration Failed", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, 
                "Error: Passwords do not match.", 
                "Registration Failed", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int age = Integer.parseInt(ageStr);
            if (age < 15 || age > 40) {
                JOptionPane.showMessageDialog(this, 
                    "Age must be between 15-40 for youth registration.", 
                    "Registration Failed", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid age!", 
                "Registration Failed", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Success - matches PHP success message
        JOptionPane.showMessageDialog(this,
            "Registration data received!\n(Will connect to database)",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
            
        // Go back to login
        new LoginFrame();
        dispose();
    }
}