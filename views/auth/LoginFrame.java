package views.auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import views.admin.AdminDashboardFrame;
import views.youth.YouthDashboardFrame;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    
    public LoginFrame() {
        setTitle("SK Connect - Login");
        setSize(400, 450);
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
        mainPanel.setBackground(Color.WHITE);
        
        // Header
        JLabel headerLabel = new JLabel("SK Connect", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(0, 102, 204));
        
        JLabel subLabel = new JLabel("Sign in to your account", SwingConstants.CENTER);
        subLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subLabel.setForeground(Color.GRAY);
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        
        // Role selection
        String[] roles = {"Youth User", "SK Official"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setMaximumSize(new Dimension(300, 30));
        
        // Email field
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(300, 30));
        emailField.setBorder(BorderFactory.createTitledBorder("Email Address"));
        
        // Password field
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 30));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        
        // Login button
        JButton loginButton = new JButton("Log In");
        loginButton.setMaximumSize(new Dimension(300, 40));
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(e -> attemptLogin());
        
        // Register link
        JButton registerButton = new JButton("New Youth User? Create an Account");
        registerButton.setMaximumSize(new Dimension(300, 30));
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setForeground(new Color(0, 102, 204));
        registerButton.addActionListener(e -> {
            new RegisterFrame();
            dispose();
        });
        
        // Add components with spacing
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(roleComboBox);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(loginButton);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(registerButton);
        
        // Add to main panel
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(subLabel, BorderLayout.CENTER);
        mainPanel.add(formPanel, BorderLayout.SOUTH);
        
        // Add main panel to frame
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
}