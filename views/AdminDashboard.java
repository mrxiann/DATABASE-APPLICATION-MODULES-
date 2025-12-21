package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboard extends JFrame {
    private JTable userTable;
    private DefaultTableModel tableModel;
    
    public AdminDashboard() {
        setTitle("Admin Dashboard | SK Admin Portal");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        // Main container with sidebar - matches PHP layout
        JPanel mainContainer = new JPanel(new BorderLayout());
        
        // Sidebar - blue like PHP
        JPanel sidebar = createSidebar();
        mainContainer.add(sidebar, BorderLayout.WEST);
        
        // Content area - matches PHP admin_dashboard.php
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(249, 250, 251)); // Gray-50 from PHP
        
        // Header - matches PHP
        JLabel headerLabel = new JLabel("Admin Dashboard Overview");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        contentPanel.add(headerLabel, BorderLayout.NORTH);
        
        // Stats cards - exactly like PHP admin_dashboard.php
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 15));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        statsPanel.setBackground(new Color(249, 250, 251));
        
        statsPanel.add(createStatCard("Total Youth Users", "452", "437 Verified", 
            new Color(59, 130, 246), Color.BLUE)); // Blue-500
        
        statsPanel.add(createStatCard("Ongoing Events", "3", "Check Event Management", 
            new Color(34, 197, 94), Color.GREEN)); // Green-500
        
        statsPanel.add(createStatCard("Open Opportunities", "5", "Jobs/Volunteer Slots", 
            new Color(245, 158, 11), Color.ORANGE)); // Yellow-500
        
        statsPanel.add(createStatCard("Total Attendance", "1500", "Total check-in/out records", 
            new Color(139, 92, 246), new Color(79, 70, 229))); // Indigo
        
        contentPanel.add(statsPanel, BorderLayout.CENTER);
        
        // Bottom panels - matches PHP 2-column layout
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        bottomPanel.setBackground(new Color(249, 250, 251));
        
        // Recent Activity - matches PHP
        bottomPanel.add(createRecentActivityPanel());
        
        // Pending Actions - matches PHP
        bottomPanel.add(createPendingActionsPanel());
        
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        add(mainContainer);
    }
    
    private JPanel createStatCard(String title, String value, String subtitle, Color borderColor, Color iconColor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 0, 4, borderColor),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(200, 120));
        
        // Top row: title and icon
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        titleLabel.setForeground(Color.GRAY);
        
        // Icon - using emoji like PHP
        String icon = "";
        if (title.contains("Youth")) icon = "👥";
        else if (title.contains("Events")) icon = "📅";
        else if (title.contains("Opportunities")) icon = "💼";
        else icon = "📋";
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        iconLabel.setForeground(iconColor);
        
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(iconLabel, BorderLayout.EAST);
        
        // Middle: value
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 36));
        
        // Bottom: subtitle
        JLabel subLabel = new JLabel(subtitle);
        subLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subLabel.setForeground(Color.GRAY);
        
        card.add(topPanel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        card.add(subLabel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private JPanel createRecentActivityPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Recent Activity Log"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // Activity items - matches PHP list
        JPanel activitiesPanel = new JPanel();
        activitiesPanel.setLayout(new BoxLayout(activitiesPanel, BoxLayout.Y_AXIS));
        activitiesPanel.setBackground(Color.WHITE);
        
        String[][] activities = {
            {"Coastal Clean-Up Drive", "85 Attendees checked in.", "Feb 15, 2026", "green"},
            {"SK Admin Assistant Hiring", "12 New applications received.", "Feb 10, 2026", "blue"},
            {"Youth Leadership Summit", "120 Attendees checked in.", "Jan 28, 2026", "gray"}
        };
        
        for (String[] activity : activities) {
            activitiesPanel.add(createActivityItem(activity[0], activity[1], activity[2], activity[3]));
            activitiesPanel.add(Box.createVerticalStrut(10));
        }
        
        JScrollPane scrollPane = new JScrollPane(activitiesPanel);
        scrollPane.setBorder(null);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // View all link - matches PHP
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        linkPanel.setBackground(Color.WHITE);
        JLabel viewAll = new JLabel("View All Events →");
        viewAll.setForeground(new Color(79, 70, 229));
        viewAll.setFont(new Font("Arial", Font.BOLD, 12));
        linkPanel.add(viewAll);
        panel.add(linkPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createActivityItem(String title, String details, String date, String color) {
        Color bgColor, textColor;
        switch(color) {
            case "green":
                bgColor = new Color(240, 253, 244);
                textColor = new Color(22, 101, 52);
                break;
            case "blue":
                bgColor = new Color(239, 246, 255);
                textColor = new Color(30, 64, 175);
                break;
            default:
                bgColor = new Color(243, 244, 246);
                textColor = new Color(55, 65, 81);
        }
        
        JPanel item = new JPanel(new BorderLayout());
        item.setBackground(bgColor);
        item.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.BLACK);
        
        JLabel detailsLabel = new JLabel(details);
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        detailsLabel.setForeground(Color.DARK_GRAY);
        
        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 11));
        dateLabel.setForeground(textColor);
        
        item.add(titleLabel, BorderLayout.NORTH);
        item.add(detailsLabel, BorderLayout.CENTER);
        item.add(dateLabel, BorderLayout.EAST);
        
        return item;
    }
    
    private JPanel createPendingActionsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Pending Admin Actions"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // Actions list - matches PHP
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        actionsPanel.setBackground(Color.WHITE);
        
        String[][] actions = {
            {"15 new youth users pending verification.", "Review", "red"},
            {"7 pieces of youth feedback require a reply.", "Manage", "yellow"},
            {"2 new opportunity applications received.", "Check", "green"}
        };
        
        for (String[] action : actions) {
            actionsPanel.add(createActionItem(action[0], action[1], action[2]));
            actionsPanel.add(Box.createVerticalStrut(10));
        }
        
        JScrollPane scrollPane = new JScrollPane(actionsPanel);
        scrollPane.setBorder(null);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createActionItem(String text, String buttonText, String color) {
        Color borderColor, bgColor;
        switch(color) {
            case "red":
                borderColor = new Color(239, 68, 68);
                bgColor = new Color(254, 242, 242);
                break;
            case "yellow":
                borderColor = new Color(245, 158, 11);
                bgColor = new Color(254, 252, 232);
                break;
            case "green":
                borderColor = new Color(34, 197, 94);
                bgColor = new Color(240, 253, 244);
                break;
            default:
                borderColor = Color.GRAY;
                bgColor = new Color(243, 244, 246);
        }
        
        JPanel item = new JPanel(new BorderLayout());
        item.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 4, 0, 0, borderColor),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        item.setBackground(bgColor);
        
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Arial", Font.BOLD, 12));
        
        JButton actionButton = new JButton(buttonText);
        actionButton.setFont(new Font("Arial", Font.PLAIN, 11));
        actionButton.setForeground(borderColor);
        actionButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        actionButton.setContentAreaFilled(false);
        
        item.add(textLabel, BorderLayout.CENTER);
        item.add(actionButton, BorderLayout.EAST);
        
        return item;
    }
    
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(30, 64, 175)); // Blue-900 from PHP
        sidebar.setPreferredSize(new Dimension(250, 700));
        
        // Logo - matches PHP
        JLabel logo = new JLabel("SK Admin Portal");
        logo.setFont(new Font("Arial", Font.BOLD, 18));
        logo.setForeground(Color.WHITE);
        logo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(59, 130, 246)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        sidebar.add(logo);
        
        // Navigation - matches PHP admin_sidebar.php
        String[][] menuItems = {
            {"📊", "Dashboard", "admin_dashboard.php"},
            {"👥", "User Management", "admin_user_management.php"},
            {"📅", "Event Management", "admin_event_management.php"},
            {"💼", "Opportunities Mgmt", "admin_opportunities.php"},
            {"📋", "Attendance Record", "admin_attendance_record.php"},
            {"💬", "Manage Feedback", "admin_feedback_management.php"}
        };
        
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(new Color(30, 64, 175));
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        for (String[] item : menuItems) {
            JButton menuButton = new JButton(item[0] + "  " + item[1]);
            menuButton.setForeground(Color.WHITE);
            menuButton.setBackground(new Color(30, 64, 175));
            menuButton.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
            menuButton.setHorizontalAlignment(SwingConstants.LEFT);
            menuButton.setFont(new Font("Arial", Font.PLAIN, 14));
            menuButton.setFocusPainted(false);
            menuButton.setMaximumSize(new Dimension(230, 45));
            
            // Highlight Dashboard (current page)
            if (item[1].equals("Dashboard")) {
                menuButton.setBackground(new Color(37, 99, 235)); // Blue-800
                menuButton.setFont(new Font("Arial", Font.BOLD, 14));
            }
            
            navPanel.add(menuButton);
            navPanel.add(Box.createVerticalStrut(5));
        }
        
        sidebar.add(navPanel);
        sidebar.add(Box.createVerticalGlue());
        
        // User info - matches PHP
        JPanel userPanel = new JPanel(new BorderLayout(10, 0));
        userPanel.setBackground(new Color(30, 64, 175));
        userPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(59, 130, 246)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Avatar
        JLabel avatar = new JLabel("👤");
        avatar.setFont(new Font("Arial", Font.PLAIN, 24));
        
        // User info
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setBackground(new Color(30, 64, 175));
        JLabel nameLabel = new JLabel("SK Admin Officer");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        
        JLabel roleLabel = new JLabel("SK Secretary, Zone 1");
        roleLabel.setForeground(new Color(191, 219, 254));
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        
        infoPanel.add(nameLabel);
        infoPanel.add(roleLabel);
        
        // Logout button
        JButton logoutButton = new JButton("🚪");
        logoutButton.setForeground(new Color(191, 219, 254));
        logoutButton.setBackground(new Color(30, 64, 175));
        logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        logoutButton.addActionListener(e -> logout());
        
        userPanel.add(avatar, BorderLayout.WEST);
        userPanel.add(infoPanel, BorderLayout.CENTER);
        userPanel.add(logoutButton, BorderLayout.EAST);
        
        sidebar.add(userPanel);
        
        return sidebar;
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