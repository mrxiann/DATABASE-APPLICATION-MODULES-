package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import models.DatabaseConnection;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleCombo;
    
    public LoginFrame() {
        setTitle("SK Connect - Login");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        // Main panel with gradient background
        GradientPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BorderLayout());
        
        // Header with logo
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        
        JLabel logo = new JLabel("🏛️", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.PLAIN, 48));
        logo.setForeground(Color.WHITE);
        
        JLabel title = new JLabel("SK CONNECT", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        
        JLabel subtitle = new JLabel("Youth Empowerment Portal", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(220, 220, 255));
        
        headerPanel.add(logo, BorderLayout.NORTH);
        headerPanel.add(title, BorderLayout.CENTER);
        headerPanel.add(subtitle, BorderLayout.SOUTH);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        
        // Center form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        // Form container with glass effect
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setBackground(new Color(255, 255, 255, 200));
        formContainer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 2, true),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));
        
        // Role selection with icon
        JPanel rolePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        rolePanel.setOpaque(false);
        JLabel roleIcon = new JLabel("👤");
        roleIcon.setFont(new Font("Arial", Font.PLAIN, 16));
        String[] roles = {"Youth User", "SK Official"};
        roleCombo = new JComboBox<>(roles);
        styleComboBox(roleCombo);
        rolePanel.add(roleIcon);
        rolePanel.add(roleCombo);
        
        // Email field with icon
        JPanel emailPanel = new JPanel(new BorderLayout(10, 0));
        emailPanel.setOpaque(false);
        emailPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JLabel emailIcon = new JLabel("📧");
        emailIcon.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField = new JTextField();
        styleTextField(emailField, "Email Address");
        emailPanel.add(emailIcon, BorderLayout.WEST);
        emailPanel.add(emailField, BorderLayout.CENTER);
        
        // Password field with icon
        JPanel passwordPanel = new JPanel(new BorderLayout(10, 0));
        passwordPanel.setOpaque(false);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JLabel passwordIcon = new JLabel("🔒");
        passwordIcon.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField = new JPasswordField();
        stylePasswordField(passwordField, "Password");
        passwordPanel.add(passwordIcon, BorderLayout.WEST);
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        
        // Login button with modern style
        JButton loginButton = new JButton("LOGIN") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(79, 70, 229));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setColor(Color.WHITE);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                String text = getText();
                int x = (getWidth() - fm.stringWidth(text)) / 2;
                int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                g2.drawString(text, x, y);
                g2.dispose();
            }
        };
        loginButton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        loginButton.setContentAreaFilled(false);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> login());
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setForeground(new Color(67, 56, 202));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setForeground(Color.WHITE);
            }
        });
        
        // Links panel
        JPanel linksPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        linksPanel.setOpaque(false);
        linksPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        JButton registerButton = createLinkButton("🎯 New Youth? Create Account", e -> openRegister());
        JButton dbButton = createLinkButton("🔧 Test Database Connection", e -> testDatabase());
        
        linksPanel.add(registerButton);
        linksPanel.add(dbButton);
        
        // Add components to form container
        formContainer.add(rolePanel);
        formContainer.add(emailPanel);
        formContainer.add(passwordPanel);
        formContainer.add(Box.createVerticalStrut(20));
        formContainer.add(loginButton);
        formContainer.add(linksPanel);
        
        // Add to main panel
        formPanel.add(formContainer);
        
        // Footer
        JLabel footer = new JLabel("© 2024 SK Connect - Empowering Youth, Building Communities", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        footer.setForeground(new Color(220, 220, 255));
        footer.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Enter key to login
        getRootPane().setDefaultButton(loginButton);
        
        // Test database on startup
        new Thread(() -> {
            DatabaseConnection.testQuery();
        }).start();
    }
    
    private void styleComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 220), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        combo.setFocusable(false);
        combo.setMaximumSize(new Dimension(300, 40));
    }
    
    private void styleTextField(JTextField field, String placeholder) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 220), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.setMaximumSize(new Dimension(300, 40));
    }
    
    private void stylePasswordField(JPasswordField field, String placeholder) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 220), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.setMaximumSize(new Dimension(300, 40));
    }
    
    private JButton createLinkButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setForeground(new Color(79, 70, 229));
        button.setBackground(new Color(255, 255, 255, 150));
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(79, 70, 229, 100), 1, true),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(action);
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(79, 70, 229, 30));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 255, 255, 150));
            }
        });
        
        return button;
    }
    
    private void login() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = (String) roleCombo.getSelectedItem();
        
        if (email.isEmpty() || password.isEmpty()) {
            showMessage("Please enter both email and password.", "⚠️ Login Failed", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Demo login
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
    
    // Gradient Panel Class
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Create gradient
            GradientPaint gradient = new GradientPaint(
                0, 0, new Color(79, 70, 229),
                getWidth(), getHeight(), new Color(99, 102, 241)
            );
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}