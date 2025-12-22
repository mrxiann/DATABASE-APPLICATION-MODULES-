package views;

import javax.swing.*;
import views.components.*;
import java.awt.*;
import java.awt.event.*;
import models.DatabaseConnection;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleCombo;
    
    public LoginFrame() {
        setTitle("SK Connect - Login");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        // Main panel with gradient
        GradientPanel mainPanel = new GradientPanel(
            new Color(79, 70, 229),
            new Color(129, 140, 248)
        );
        mainPanel.setLayout(new BorderLayout());
        
        // Header
        RoundedPanel headerPanel = new RoundedPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackgroundColor(new Color(255, 255, 255, 30));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 30, 0));
        
        JLabel logo = new JLabel("🏛️", SwingConstants.CENTER);
        logo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        logo.setForeground(Color.WHITE);
        
        JLabel title = new JLabel("SK CONNECT", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        
        JLabel subtitle = new JLabel("Youth Empowerment System", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(255, 255, 255, 180));
        
        headerPanel.add(logo);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subtitle);
        
        // Center form panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        
        // Form container with glass effect
        RoundedPanel formContainer = new RoundedPanel(25);
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setBackgroundColor(new Color(255, 255, 255, 230));
        formContainer.setShadow(true);
        formContainer.setBorder(BorderFactory.createEmptyBorder(35, 35, 35, 35));
        
        // Role selection
        JLabel roleLabel = new JLabel("Login As:");
        roleLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        roleLabel.setForeground(new Color(55, 65, 81));
        
        String[] roles = {"Youth User", "SK Official"};
        roleCombo = new JComboBox<>(roles);
        styleComboBox(roleCombo);
        
        // Email field
        JLabel emailLabel = new JLabel("Email Address:");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        emailLabel.setForeground(new Color(55, 65, 81));
        
        emailField = new JTextField();
        styleTextField(emailField);
        
        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        passwordLabel.setForeground(new Color(55, 65, 81));
        
        passwordField = new JPasswordField();
        stylePasswordField(passwordField);
        
        // Login button
        ModernButton loginButton = new ModernButton("SIGN IN");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(300, 50));
        loginButton.addActionListener(e -> login());
        
        // Links panel
        JPanel linksPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        linksPanel.setOpaque(false);
        
        JButton registerButton = createLinkButton("📝 Create New Account", e -> openRegister());
        JButton dbButton = createLinkButton("🔧 Test Database", e -> testDatabase());
        
        linksPanel.add(registerButton);
        linksPanel.add(dbButton);
        
        // Add components to form
        formContainer.add(roleLabel);
        formContainer.add(Box.createVerticalStrut(5));
        formContainer.add(roleCombo);
        formContainer.add(Box.createVerticalStrut(15));
        formContainer.add(emailLabel);
        formContainer.add(Box.createVerticalStrut(5));
        formContainer.add(emailField);
        formContainer.add(Box.createVerticalStrut(15));
        formContainer.add(passwordLabel);
        formContainer.add(Box.createVerticalStrut(5));
        formContainer.add(passwordField);
        formContainer.add(Box.createVerticalStrut(25));
        formContainer.add(loginButton);
        formContainer.add(Box.createVerticalStrut(15));
        formContainer.add(linksPanel);
        
        centerPanel.add(formContainer);
        
        // Footer
        JLabel footer = new JLabel("© 2024 SK Connect | Empowering Youth, Building Communities", 
            SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        footer.setForeground(new Color(255, 255, 255, 150));
        footer.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Enter key to login
        getRootPane().setDefaultButton(loginButton);
        
        // Test database
        new Thread(DatabaseConnection::testQuery).start();
    }
    
    private void styleComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        combo.setMaximumSize(new Dimension(300, 45));
    }
    
    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        field.setMaximumSize(new Dimension(300, 45));
    }
    
    private void stylePasswordField(JPasswordField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        field.setMaximumSize(new Dimension(300, 45));
    }
    
    private JButton createLinkButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setForeground(new Color(79, 70, 229));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(action);
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setForeground(new Color(67, 56, 202));
            }
            public void mouseExited(MouseEvent e) {
                button.setForeground(new Color(79, 70, 229));
            }
        });
        
        return button;
    }
    
    private void login() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = (String) roleCombo.getSelectedItem();
        
        if (email.isEmpty() || password.isEmpty()) {
            showMessage("Please enter both email and password.", "⚠️ Login Failed", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (email.equals("admin@sk.com") && password.equals("admin123") && role.equals("SK Official")) {
            showMessage("Welcome Admin!", "✅ Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new AdminDashboard();
        } else if (email.equals("youth@sk.com") && password.equals("youth123") && role.equals("Youth User")) {
            showMessage("Welcome Youth!", "✅ Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new YouthDashboard();
        } else {
            showMessage("Invalid email or password.", "❌ Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showMessage(String message, String title, int type) {
        JOptionPane.showMessageDialog(this, message, title, type);
    }
    
    private void openRegister() {
        new RegisterFrame();
        dispose();
    }
    
    private void testDatabase() {
        DatabaseConnection.testQuery();
    }
}