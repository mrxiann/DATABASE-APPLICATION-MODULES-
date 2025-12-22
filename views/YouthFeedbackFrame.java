package views;

import javax.swing.*;
import java.awt.*;

public class YouthFeedbackFrame extends JFrame {
    private JTextArea feedbackArea;
    private JComboBox<String> categoryCombo;
    private JComboBox<String> priorityCombo;
    
    public YouthFeedbackFrame() {
        setTitle("SK Youth Feedback | Submit Inquiry");
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
        
        // Content area - matches PHP feedback.php
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(249, 250, 251));
        
        // Header - matches PHP
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Submit Feedback / Inquiry");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(31, 41, 55));
        
        JLabel subtitleLabel = new JLabel("Share your suggestions, concerns, or questions with SK Council");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.GRAY);
        
        JButton mySubmissionsButton = new JButton("📋 My Previous Submissions");
        mySubmissionsButton.setBackground(new Color(79, 70, 229));
        mySubmissionsButton.setForeground(Color.WHITE);
        mySubmissionsButton.setFont(new Font("Arial", Font.BOLD, 12));
        mySubmissionsButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);
        headerPanel.add(mySubmissionsButton, BorderLayout.EAST);
        
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Main form - matches PHP layout
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        formPanel.setBackground(Color.WHITE);
        
        // Category selection
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categoryPanel.setBackground(Color.WHITE);
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 12));
        categoryLabel.setPreferredSize(new Dimension(100, 30));
        
        String[] categories = {"General Feedback", "Event Suggestion", "Complaint", 
                              "Opportunity Inquiry", "Technical Issue", "Other"};
        categoryCombo = new JComboBox<>(categories);
        categoryCombo.setPreferredSize(new Dimension(200, 30));
        
        categoryPanel.add(categoryLabel);
        categoryPanel.add(categoryCombo);
        
        // Priority selection
        JPanel priorityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        priorityPanel.setBackground(Color.WHITE);
        JLabel priorityLabel = new JLabel("Priority:");
        priorityLabel.setFont(new Font("Arial", Font.BOLD, 12));
        priorityLabel.setPreferredSize(new Dimension(100, 30));
        
        String[] priorities = {"Low", "Medium", "High", "Urgent"};
        priorityCombo = new JComboBox<>(priorities);
        priorityCombo.setPreferredSize(new Dimension(150, 30));
        
        priorityPanel.add(priorityLabel);
        priorityPanel.add(priorityCombo);
        
        // Subject field
        JTextField subjectField = new JTextField();
        subjectField.setMaximumSize(new Dimension(500, 40));
        subjectField.setBorder(BorderFactory.createTitledBorder("Subject *"));
        
        // Feedback text area
        feedbackArea = new JTextArea(10, 50);
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);
        feedbackArea.setBorder(BorderFactory.createTitledBorder("Your Message *"));
        JScrollPane feedbackScroll = new JScrollPane(feedbackArea);
        feedbackScroll.setMaximumSize(new Dimension(500, 250));
        
        // Attachment button
        JButton attachButton = new JButton("📎 Attach File (Optional)");
        attachButton.setMaximumSize(new Dimension(200, 40));
        attachButton.setBackground(new Color(243, 244, 246));
        
        // Submit button
        JButton submitButton = new JButton("Submit Feedback");
        submitButton.setMaximumSize(new Dimension(300, 45));
        submitButton.setBackground(new Color(34, 197, 94));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.addActionListener(e -> submitFeedback(
            (String) categoryCombo.getSelectedItem(),
            (String) priorityCombo.getSelectedItem(),
            subjectField.getText(),
            feedbackArea.getText()
        ));
        
        // Add components with spacing
        formPanel.add(categoryPanel);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(priorityPanel);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(subjectField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(feedbackScroll);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(attachButton);
        formPanel.add(Box.createVerticalStrut(25));
        formPanel.add(submitButton);
        
        // Guidelines panel - matches PHP
        JPanel guidelinesPanel = new JPanel(new BorderLayout());
        guidelinesPanel.setBorder(BorderFactory.createTitledBorder("Submission Guidelines"));
        guidelinesPanel.setBackground(new Color(240, 253, 244));
        guidelinesPanel.setPreferredSize(new Dimension(300, 200));
        
        JTextArea guidelinesText = new JTextArea();
        guidelinesText.setText("📋 Before submitting:\n\n" +
                             "• Be specific and constructive\n" +
                             "• Include relevant details\n" +
                             "• For complaints, provide evidence if possible\n" +
                             "• Allow 3-5 business days for response\n" +
                             "• Check your email for updates\n\n" +
                             "Emergency? Contact SK Office directly.");
        guidelinesText.setFont(new Font("Arial", Font.PLAIN, 12));
        guidelinesText.setBackground(new Color(240, 253, 244));
        guidelinesText.setEditable(false);
        guidelinesText.setLineWrap(true);
        guidelinesText.setWrapStyleWord(true);
        
        guidelinesPanel.add(new JScrollPane(guidelinesText), BorderLayout.CENTER);
        
        // Main content layout
        JPanel mainContent = new JPanel(new BorderLayout(20, 0));
        mainContent.setBackground(new Color(249, 250, 251));
        mainContent.add(formPanel, BorderLayout.CENTER);
        mainContent.add(guidelinesPanel, BorderLayout.EAST);
        
        contentPanel.add(mainContent, BorderLayout.CENTER);
        
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        add(mainContainer);
    }
    
    private void submitFeedback(String category, String priority, String subject, String message) {
        if (subject.isEmpty() || message.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please fill in both subject and message fields.", 
                "Incomplete Form", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Submit feedback?\n\nCategory: " + category + 
            "\nPriority: " + priority + 
            "\nSubject: " + subject,
            "Confirm Submission",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // In real app, this would save to database
            JOptionPane.showMessageDialog(this,
                "✅ Feedback submitted successfully!\n\n" +
                "Reference ID: FB-" + System.currentTimeMillis() + "\n" +
                "You will receive a response within 3-5 business days.",
                "Submission Successful",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Clear form
            feedbackArea.setText("");
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
            
            if (item[1].equals("Submit Feedback")) {
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