package views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class YouthProfileFrame extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField ageField;
    private JComboBox<String> purokCombo;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    
    public YouthProfileFrame() {
        setTitle("My Profile | SK Youth Portal");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        JPanel mainContainer = new JPanel(new BorderLayout());
        
        // Sidebar
        JPanel sidebar = createSidebar();
        mainContainer.add(sidebar, BorderLayout.WEST);
        
        // Content area - matches PHP profile.php
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(249, 250, 251));
        
        // Header - matches PHP
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("My Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(31, 41, 55));
        
        JLabel subtitleLabel = new JLabel("Manage your personal information and account settings");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.GRAY);
        
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);
        
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Main profile panel
        JPanel profilePanel = new JPanel(new GridLayout(1, 2, 20, 0));
        profilePanel.setBackground(new Color(249, 250, 251));
        
        // Left: Profile info
        profilePanel.add(createProfileInfoPanel());
        
        // Right: Account settings
        profilePanel.add(createAccountSettingsPanel());
        
        contentPanel.add(profilePanel, BorderLayout.CENTER);
        
        // Bottom: Action buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        actionPanel.setBackground(new Color(249, 250, 251));
        
        JButton saveButton = new JButton("💾 Save Changes");
        saveButton.setBackground(new Color(34, 197, 94));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setPreferredSize(new Dimension(200, 45));
        saveButton.addActionListener(e -> saveProfile());
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(239, 68, 68));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(new Dimension(150, 45));
        cancelButton.addActionListener(e -> dispose());
        
        actionPanel.add(saveButton);
        actionPanel.add(cancelButton);
        
        contentPanel.add(actionPanel, BorderLayout.SOUTH);
        
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        add(mainContainer);
    }
    
    private JPanel createProfileInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Personal Information"));
        panel.setBackground(Color.WHITE);
        
        // Profile picture - matches PHP
        JPanel picturePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        picturePanel.setBackground(Color.WHITE);
        
        JLabel avatarLabel = new JLabel("👤");
        avatarLabel.setFont(new Font("Arial", Font.PLAIN, 64));
        avatarLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        avatarLabel.setPreferredSize(new Dimension(100, 100));
        
        JButton changePictureButton = new JButton("Change Profile Picture");
        changePictureButton.setFont(new Font("Arial", Font.PLAIN, 11));
        
        picturePanel.add(avatarLabel);
        picturePanel.add(Box.createHorizontalStrut(20));
        picturePanel.add(changePictureButton);
        
        // Form fields
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Name fields
        JPanel namePanel = new JPanel(new GridLayout(1, 2, 10, 0));
        namePanel.setBackground(Color.WHITE);
        
        firstNameField = new JTextField("Juan");
        firstNameField.setBorder(BorderFactory.createTitledBorder("First Name *"));
        
        lastNameField = new JTextField("Dela Cruz");
        lastNameField.setBorder(BorderFactory.createTitledBorder("Last Name *"));
        
        namePanel.add(firstNameField);
        namePanel.add(lastNameField);
        
        // Email field
        emailField = new JTextField("juan.delacruz@email.com");
        emailField.setBorder(BorderFactory.createTitledBorder("Email Address *"));
        emailField.setMaximumSize(new Dimension(400, 40));
        
        // Age field
        ageField = new JTextField("22");
        ageField.setBorder(BorderFactory.createTitledBorder("Age *"));
        ageField.setMaximumSize(new Dimension(400, 40));
        
        // Purok selection
        String[] puroks = {"Purok 1", "Purok 2", "Purok 3", "Purok 4", "Purok 5"};
        purokCombo = new JComboBox<>(puroks);
        purokCombo.setSelectedItem("Purok 5");
        purokCombo.setBorder(BorderFactory.createTitledBorder("Purok *"));
        purokCombo.setMaximumSize(new Dimension(400, 40));
        
        // Add components
        formPanel.add(namePanel);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(ageField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(purokCombo);
        
        panel.add(picturePanel);
        panel.add(formPanel);
        
        return panel;
    }
    
    private JPanel createAccountSettingsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Account & Security"));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            panel.getBorder(),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        // Current membership info - matches PHP
        JPanel membershipPanel = new JPanel(new BorderLayout());
        membershipPanel.setBackground(new Color(239, 246, 255));
        membershipPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(59, 130, 246)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel membershipTitle = new JLabel("SK Youth Membership");
        membershipTitle.setFont(new Font("Arial", Font.BOLD, 16));
        membershipTitle.setForeground(new Color(30, 64, 175));
        
        JLabel membershipDetails = new JLabel("<html>Status: <b>Active</b> | Since: 2025<br>" +
                                            "QR Code ID: SK-YOUTH-00123<br>" +
                                            "Total Events: 6 | Volunteer Hours: 24</html>");
        membershipDetails.setFont(new Font("Arial", Font.PLAIN, 12));
        
        membershipPanel.add(membershipTitle, BorderLayout.NORTH);
        membershipPanel.add(membershipDetails, BorderLayout.CENTER);
        
        // Password change section
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setBorder(BorderFactory.createTitledBorder("Change Password"));
        
        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("New Password (leave blank to keep current)"));
        passwordField.setMaximumSize(new Dimension(400, 40));
        
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBorder(BorderFactory.createTitledBorder("Confirm New Password"));
        confirmPasswordField.setMaximumSize(new Dimension(400, 40));
        
        passwordPanel.add(passwordField);
        passwordPanel.add(Box.createVerticalStrut(10));
        passwordPanel.add(confirmPasswordField);
        
        // Notification settings - matches PHP
        JPanel notificationPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        notificationPanel.setBackground(Color.WHITE);
        notificationPanel.setBorder(BorderFactory.createTitledBorder("Notification Preferences"));
        
        JCheckBox emailCheckbox = new JCheckBox("Email notifications", true);
        JCheckBox eventCheckbox = new JCheckBox("Event reminders", true);
        JCheckBox opportunityCheckbox = new JCheckBox("Opportunity alerts", true);
        
        notificationPanel.add(emailCheckbox);
        notificationPanel.add(eventCheckbox);
        notificationPanel.add(opportunityCheckbox);
        
        // Danger zone - matches PHP
        JPanel dangerPanel = new JPanel(new BorderLayout());
        dangerPanel.setBackground(new Color(254, 242, 242));
        dangerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(239, 68, 68)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel dangerTitle = new JLabel("⚠️ Account Actions");
        dangerTitle.setFont(new Font("Arial", Font.BOLD, 14));
        dangerTitle.setForeground(new Color(239, 68, 68));
        
        JButton deactivateButton = new JButton("Request Account Deactivation");
        deactivateButton.setBackground(new Color(239, 68, 68));
        deactivateButton.setForeground(Color.WHITE);
        deactivateButton.setFont(new Font("Arial", Font.PLAIN, 11));
        deactivateButton.addActionListener(e -> requestDeactivation());
        
        dangerPanel.add(dangerTitle, BorderLayout.NORTH);
        dangerPanel.add(deactivateButton, BorderLayout.SOUTH);
        
        // Add all panels
        panel.add(membershipPanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(passwordPanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(notificationPanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(dangerPanel);
        
        return panel;
    }
    
    private void saveProfile() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String ageStr = ageField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        // Basic validation
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || ageStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please fill in all required fields (marked with *).",
                "Incomplete Information",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!password.isEmpty() && !password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                "Passwords do not match.",
                "Password Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int age = Integer.parseInt(ageStr);
            if (age < 15 || age > 40) {
                JOptionPane.showMessageDialog(this,
                    "Age must be between 15-40 for youth registration.",
                    "Invalid Age",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid age.",
                "Invalid Age",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Save changes (in real app, this would update database)
        JOptionPane.showMessageDialog(this,
            "✅ Profile updated successfully!\n\n" +
            "Changes will take effect immediately.",
            "Profile Saved",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void requestDeactivation() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "⚠️ Account Deactivation Request\n\n" +
            "This will:\n" +
            "• Disable your login access\n" +
            "• Remove you from active events\n" +
            "• Keep your records for 1 year\n" +
            "• Require SK Council approval\n\n" +
            "Continue with deactivation request?",
            "Confirm Deactivation Request",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this,
                "Deactivation request submitted.\n" +
                "SK Council will review your request within 3-5 business days.\n\n" +
                "You will receive an email confirmation.",
                "Request Submitted",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(79, 70, 229));
        sidebar.setPreferredSize(new Dimension(250, 700));
        
        JLabel logo = new JLabel("SK Youth Portal");
        logo.setFont(new Font("Arial", Font.BOLD, 18));
        logo.setForeground(Color.WHITE);
        logo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(99, 102, 241)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        sidebar.add(logo);
        
        String[][] menuItems = {
            {"🏠", "Dashboard", "dashboard"},
            {"📅", "View Events", "events"},
            {"💼", "Opportunities", "opportunities"},
            {"📱", "My QR Code", "qr"},
            {"💬", "Submit Feedback", "feedback"},
            {"👤", "My Profile", "profile"}
        };
        
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(new Color(79, 70, 229));
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        for (String[] item : menuItems) {
            JButton menuButton = new JButton(item[0] + "  " + item[1]);
            menuButton.setForeground(Color.WHITE);
            menuButton.setBackground(new Color(79, 70, 229));
            menuButton.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
            menuButton.setHorizontalAlignment(SwingConstants.LEFT);
            menuButton.setFont(new Font("Arial", Font.PLAIN, 14));
            menuButton.setFocusPainted(false);
            menuButton.setMaximumSize(new Dimension(230, 45));
            
            if (item[1].equals("My Profile")) {
                menuButton.setBackground(new Color(67, 56, 202));
                menuButton.setFont(new Font("Arial", Font.BOLD, 14));
            }
            
            String page = item[2];
            menuButton.addActionListener(e -> navigateToPage(page));
            
            navPanel.add(menuButton);
            navPanel.add(Box.createVerticalStrut(5));
        }
        
        sidebar.add(navPanel);
        sidebar.add(Box.createVerticalGlue());
        
        // User info
        JPanel userPanel = new JPanel(new BorderLayout(10, 0));
        userPanel.setBackground(new Color(79, 70, 229));
        userPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(99, 102, 241)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel avatar = new JLabel("👤");
        avatar.setFont(new Font("Arial", Font.PLAIN, 24));
        
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setBackground(new Color(79, 70, 229));
        JLabel nameLabel = new JLabel("Juan D. Dela Cruz");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        
        JLabel roleLabel = new JLabel("Youth Resident");
        roleLabel.setForeground(new Color(199, 210, 254));
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        
        infoPanel.add(nameLabel);
        infoPanel.add(roleLabel);
        
        JButton logoutButton = new JButton("🚪");
        logoutButton.setForeground(new Color(199, 210, 254));
        logoutButton.setBackground(new Color(79, 70, 229));
        logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        logoutButton.addActionListener(e -> logout());
        
        userPanel.add(avatar, BorderLayout.WEST);
        userPanel.add(infoPanel, BorderLayout.CENTER);
        userPanel.add(logoutButton, BorderLayout.EAST);
        
        sidebar.add(userPanel);
        
        return sidebar;
    }
    
    private void navigateToPage(String page) {
        dispose();
        
        switch (page) {
            case "dashboard":
                new YouthDashboard();
                break;
            case "events":
                new YouthEventsFrame();
                break;
            case "opportunities":
                new YouthOpportunitiesFrame();
                break;
            case "qr":
                new YouthDashboard();
                break;
            case "feedback":
                new YouthFeedbackFrame();
                break;
            case "profile":
                new YouthProfileFrame();
                break;
        }
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to logout?", 
            "Confirm Logout", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new LoginFrame();
        }
    }
}