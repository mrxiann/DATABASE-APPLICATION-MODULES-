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
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Header
        JLabel headerLabel = new JLabel("Create Your SK Connect Account", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(new Color(0, 102, 204));
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        
        // Full Name
        fullNameField = new JTextField();
        fullNameField.setMaximumSize(new Dimension(300, 30));
        fullNameField.setBorder(BorderFactory.createTitledBorder("Full Name *"));
        
        // Email
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(300, 30));
        emailField.setBorder(BorderFactory.createTitledBorder("Email Address *"));
        
        // Password
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 30));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password *"));
        
        // Confirm Password
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setMaximumSize(new Dimension(300, 30));
        confirmPasswordField.setBorder(BorderFactory.createTitledBorder("Confirm Password *"));
        
        // Register button
        JButton registerButton = new JButton("Register Account");
        registerButton.setMaximumSize(new Dimension(300, 40));
        registerButton.setBackground(new Color(40, 167, 69));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.addActionListener(e -> attemptRegister());
        
        // Back to login
        JButton backButton = new JButton("Already have an account? Log In Here");
        backButton.setMaximumSize(new Dimension(300, 30));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setForeground(new Color(0, 102, 204));
        backButton.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });
        
        // Add components with spacing
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(fullNameField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(confirmPasswordField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(registerButton);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(backButton);
        
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        
        // Enter key to register
        getRootPane().setDefaultButton(registerButton);
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
        
        // DEMO: Show success
        JOptionPane.showMessageDialog(this,
            "Registration successful! You can now log in.",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
            
        // Go back to login
        new LoginFrame();
        this.dispose();
    }
}