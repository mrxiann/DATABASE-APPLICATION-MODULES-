package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame {
    private JTextField firstNameField, lastNameField, emailField, ageField;
    private JPasswordField passwordField, confirmPasswordField;
    private JComboBox<String> purokCombo;
    
    public RegisterFrame() {
        setTitle("SK Connect - Register");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        // Main panel with gradient
        GradientPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BorderLayout());
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        
        JLabel icon = new JLabel("🚀", SwingConstants.CENTER);
        icon.setFont(new Font("Arial", Font.PLAIN, 48));
        icon.setForeground(Color.WHITE);
        
        JLabel title = new JLabel("Create Your Account", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        
        JLabel subtitle = new JLabel("Join SK Connect as a Youth Member", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(220, 220, 255));
        
        headerPanel.add(icon, BorderLayout.NORTH);
        headerPanel.add(title, BorderLayout.CENTER);
        headerPanel.add(subtitle, BorderLayout.SOUTH);
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        // Form container
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setBackground(new Color(255, 255, 255, 220));
        formContainer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 2, true),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        
        // Name fields in row
        JPanel namePanel = new JPanel(new GridLayout(1, 2, 15, 0));
        namePanel.setOpaque(false);
        
        firstNameField = createStyledTextField("👤 First Name");
        lastNameField = createStyledTextField("Last Name");
        
        namePanel.add(firstNameField);
        namePanel.add(lastNameField);
        
        // Other fields
        emailField = createStyledTextField("📧 Email Address");
        ageField = createStyledTextField("🎂 Age");
        
        // Purok selection
        JPanel purokPanel = new JPanel(new BorderLayout(10, 0));
        purokPanel.setOpaque(false);
        JLabel purokIcon = new JLabel("📍");
        purokIcon.setFont(new Font("Arial", Font.PLAIN, 16));
        String[] puroks = {"Purok 1", "Purok 2", "Purok 3", "Purok 4", "Purok 5"};
        purokCombo = new JComboBox<>(puroks);
        styleComboBox(purokCombo);
        purokPanel.add(purokIcon, BorderLayout.WEST);
        purokPanel.add(purokCombo, BorderLayout.CENTER);
        
        // Password fields
        passwordField = createStyledPasswordField("🔒 Password");
        confirmPasswordField = createStyledPasswordField("✓ Confirm Password");
        
        // Register button
        JButton registerButton = new JButton("CREATE ACCOUNT") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(34, 197, 94));
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
        registerButton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        registerButton.setContentAreaFilled(false);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(e -> register());
        
        // Back to login
        JButton backButton = createLinkButton("← Already have an account? Log In Here", 
            e -> {
                new LoginFrame();
                dispose();
            });
        
        // Add components
        formContainer.add(namePanel);
        formContainer.add(Box.createVerticalStrut(15));
        formContainer.add(emailField);
        formContainer.add(Box.createVerticalStrut(15));
        formContainer.add(ageField);
        formContainer.add(Box.createVerticalStrut(15));
        formContainer.add(purokPanel);
        formContainer.add(Box.createVerticalStrut(15));
        formContainer.add(passwordField);
        formContainer.add(Box.createVerticalStrut(15));
        formContainer.add(confirmPasswordField);
        formContainer.add(Box.createVerticalStrut(25));
        formContainer.add(registerButton);
        formContainer.add(Box.createVerticalStrut(15));
        formContainer.add(backButton);
        
        formPanel.add(formContainer);
        
        // Footer
        JLabel footer = new JLabel("By registering, you agree to our Terms & Conditions", 
            SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        footer.setForeground(new Color(220, 220, 255));
        footer.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Enter key to register
        getRootPane().setDefaultButton(registerButton);
    }
    
    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 220), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setMaximumSize(new Dimension(400, 45));
        return field;
    }
    
    private JPasswordField createStyledPasswordField(String placeholder) {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 220), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setMaximumSize(new Dimension(400, 45));
        return field;
    }
    
    private void styleComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 220), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        combo.setFocusable(false);
        combo.setMaximumSize(new Dimension(400, 45));
    }
    
    private JButton createLinkButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setForeground(new Color(79, 70, 229));
        button.setBackground(new Color(255, 255, 255, 100));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(action);
        return button;
    }
    
    private void register() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String ageStr = ageField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String purok = (String) purokCombo.getSelectedItem();
        
        // Validation
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || 
            ageStr.isEmpty() || password.isEmpty()) {
            showMessage("All fields are required.", "⚠️ Registration Failed", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            showMessage("Passwords do not match.", "❌ Registration Failed", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int age = Integer.parseInt(ageStr);
            if (age < 15 || age > 40) {
                showMessage("Age must be between 15-40 for youth registration.", 
                    "❌ Registration Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid age!", "❌ Registration Failed", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Success
        showMessage("✅ Registration successful!\n\nWelcome to SK Connect, " + 
            firstName + "!\n\nYou can now login with your credentials.", 
            "🎉 Success", JOptionPane.INFORMATION_MESSAGE);
            
        new LoginFrame();
        dispose();
    }
    
    private void showMessage(String message, String title, int type) {
        JOptionPane.showMessageDialog(this, message, title, type);
    }
    
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            GradientPaint gradient = new GradientPaint(
                0, 0, new Color(99, 102, 241),
                getWidth(), getHeight(), new Color(129, 140, 248)
            );
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}