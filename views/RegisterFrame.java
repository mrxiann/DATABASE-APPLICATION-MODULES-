package views;

import javax.swing.*;
import views.components.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame {
    private JTextField firstNameField, lastNameField, emailField, ageField;
    private JPasswordField passwordField, confirmPasswordField;
    private JComboBox<String> purokCombo;
    
    public RegisterFrame() {
        setTitle("SK Connect - Register");
        setSize(550, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        // Main panel with gradient
        GradientPanel mainPanel = new GradientPanel(
            new Color(99, 102, 241),
            new Color(139, 92, 246)
        );
        mainPanel.setLayout(new BorderLayout());
        
        // Header
        RoundedPanel headerPanel = new RoundedPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackgroundColor(new Color(255, 255, 255, 30));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 30, 0));
        
        JLabel logo = new JLabel("🚀", SwingConstants.CENTER);
        logo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        logo.setForeground(Color.WHITE);
        
        JLabel title = new JLabel("Join SK Connect", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        
        JLabel subtitle = new JLabel("Create your youth account", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(255, 255, 255, 180));
        
        headerPanel.add(logo);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subtitle);
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        
        // Form container
        RoundedPanel formContainer = new RoundedPanel(25);
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setBackgroundColor(new Color(255, 255, 255, 230));
        formContainer.setShadow(true);
        formContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Name fields
        JPanel namePanel = new JPanel(new GridLayout(1, 2, 15, 0));
        namePanel.setOpaque(false);
        
        firstNameField = createStyledTextField("First Name");
        lastNameField = createStyledTextField("Last Name");
        
        namePanel.add(firstNameField);
        namePanel.add(lastNameField);
        
        // Other fields
        emailField = createStyledTextField("Email Address");
        ageField = createStyledTextField("Age");
        
        // Purok selection
        JLabel purokLabel = new JLabel("Purok:");
        purokLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        purokLabel.setForeground(new Color(55, 65, 81));
        
        String[] puroks = {"Purok 1", "Purok 2", "Purok 3", "Purok 4", "Purok 5"};
        purokCombo = new JComboBox<>(puroks);
        styleComboBox(purokCombo);
        
        // Password fields
        passwordField = createStyledPasswordField("Password");
        confirmPasswordField = createStyledPasswordField("Confirm Password");
        
        // Register button
        ModernButton registerButton = new ModernButton("CREATE ACCOUNT", new Color(34, 197, 94));
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerButton.setPreferredSize(new Dimension(300, 50));
        registerButton.addActionListener(e -> register());
        
        // Back to login
        JButton backButton = createLinkButton("← Already have an account? Log In Here", 
            e -> {
                new LoginFrame();
                dispose();
            });
        
        // Add components
        formContainer.add(createLabel("Name"));
        formContainer.add(Box.createVerticalStrut(5));
        formContainer.add(namePanel);
        formContainer.add(Box.createVerticalStrut(15));
        formContainer.add(createLabel("Email"));
        formContainer.add(Box.createVerticalStrut(5));
        formContainer.add(emailField);
        formContainer.add(Box.createVerticalStrut(15));
        formContainer.add(createLabel("Age"));
        formContainer.add(Box.createVerticalStrut(5));
        formContainer.add(ageField);
        formContainer.add(Box.createVerticalStrut(15));
        formContainer.add(purokLabel);
        formContainer.add(Box.createVerticalStrut(5));
        formContainer.add(purokCombo);
        formContainer.add(Box.createVerticalStrut(15));
        formContainer.add(createLabel("Password"));
        formContainer.add(Box.createVerticalStrut(5));
        formContainer.add(passwordField);
        formContainer.add(Box.createVerticalStrut(15));
        formContainer.add(createLabel("Confirm Password"));
        formContainer.add(Box.createVerticalStrut(5));
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
        footer.setForeground(new Color(255, 255, 255, 150));
        footer.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Enter key to register
        getRootPane().setDefaultButton(registerButton);
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(new Color(55, 65, 81));
        return label;
    }
    
    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setMaximumSize(new Dimension(400, 45));
        return field;
    }
    
    private JPasswordField createStyledPasswordField(String placeholder) {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setMaximumSize(new Dimension(400, 45));
        return field;
    }
    
    private void styleComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        combo.setMaximumSize(new Dimension(400, 45));
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
    
    private void register() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String ageStr = ageField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String purok = (String) purokCombo.getSelectedItem(); // Now used in success message
        
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
        
        // Success - USE the purok variable
        showMessage("✅ Registration Successful!\n\n" +
            "Name: " + firstName + " " + lastName + "\n" +
            "Email: " + email + "\n" +
            "Age: " + ageStr + "\n" +
            "Purok: " + purok + "\n\n" +
            "Welcome to SK Connect!", 
            "🎉 Success", JOptionPane.INFORMATION_MESSAGE);
            
        new LoginFrame();
        dispose();
    }
    
    private void showMessage(String message, String title, int type) {
        JOptionPane.showMessageDialog(this, message, title, type);
    }
}