package views.admin;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class AdminDashboardFrame extends JFrame {
    
    public AdminDashboardFrame() {
        setTitle("Admin Dashboard | SK Admin Portal");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        // Main container
        JPanel mainContainer = new JPanel(new BorderLayout());
        
        // Sidebar
        JPanel sidebar = new views.components.SidebarPanel("admin");
        mainContainer.add(sidebar, BorderLayout.WEST);
        
        // Content area
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header
        JLabel headerLabel = new JLabel("Admin Dashboard Overview");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        contentPanel.add(headerLabel, BorderLayout.NORTH);
        
        // Stats cards
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 15));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        statsPanel.add(createStatCard("Total Youth Users", "452", "437 Verified", Color.BLUE, "users"));
        statsPanel.add(createStatCard("Ongoing Events", "3", "Check Event Management", Color.GREEN, "calendar-check"));
        statsPanel.add(createStatCard("Open Opportunities", "5", "Jobs/Volunteer Slots", Color.YELLOW, "briefcase"));
        statsPanel.add(createStatCard("Total Attendance", "1500", "Total check-in/out records", new Color(79, 70, 229), "clipboard-list"));
        
        contentPanel.add(statsPanel, BorderLayout.CENTER);
        
        // Bottom panels
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        
        // Recent Activity
        bottomPanel.add(createRecentActivityPanel());
        
        // Pending Actions
        bottomPanel.add(createPendingActionsPanel());
        
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        
        add(mainContainer);
    }
    
    private JPanel createStatCard(String title, String value, String subtitle, Color color, String icon) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 0, 4, color),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setBackground(Color.WHITE);
        
        // Top row: title and icon
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        titleLabel.setForeground(Color.GRAY);
        
        JLabel iconLabel = new JLabel(getIconText(icon));
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        iconLabel.setForeground(color);
        
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
        panel.setBorder(BorderFactory.createTitledBorder("Recent Activity Log"));
        
        // Table for activities
        String[] columns = {"Event", "Details", "Date"};
        Object[][] data = {
            {"Coastal Clean-Up Drive", "85 Attendees checked in.", "Feb 15, 2026"},
            {"SK Admin Assistant Hiring", "12 New applications received.", "Feb 10, 2026"},
            {"Youth Leadership Summit", "120 Attendees checked in.", "Jan 28, 2026"}
        };
        
        JTable activityTable = new JTable(data, columns);
        activityTable.setRowHeight(40);
        activityTable.setEnabled(false);
        
        JScrollPane scrollPane = new JScrollPane(activityTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createPendingActionsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Pending Admin Actions"));
        
        // List of actions
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        
        actionsPanel.add(createActionItem("15 new youth users pending verification.", "Review", Color.RED));
        actionsPanel.add(Box.createVerticalStrut(10));
        actionsPanel.add(createActionItem("7 pieces of youth feedback require a reply.", "Manage", Color.YELLOW));
        actionsPanel.add(Box.createVerticalStrut(10));
        actionsPanel.add(createActionItem("2 new opportunity applications received.", "Check", Color.GREEN));
        
        panel.add(actionsPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createActionItem(String text, String buttonText, Color borderColor) {
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 4, 0, 0, borderColor),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        itemPanel.setBackground(new Color(borderColor.getRed(), borderColor.getGreen(), borderColor.getBlue(), 20));
        
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JButton actionButton = new JButton(buttonText);
        actionButton.setFont(new Font("Arial", Font.PLAIN, 11));
        
        itemPanel.add(textLabel, BorderLayout.CENTER);
        itemPanel.add(actionButton, BorderLayout.EAST);
        
        return itemPanel;
    }
    
    private String getIconText(String icon) {
        switch(icon) {
            case "users": return "👥";
            case "calendar-check": return "📅";
            case "briefcase": return "💼";
            case "clipboard-list": return "📋";
            default: return "📊";
        }
    }
}