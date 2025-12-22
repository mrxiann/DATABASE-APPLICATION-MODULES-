package views;

import javax.swing.*;
import views.components.*;
import java.awt.*;
import java.awt.event.*;

public class YouthDashboard extends JFrame {
    private ModernSidebar sidebar;
    
    public YouthDashboard() {
        setTitle("Youth Dashboard | SK Connect");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        // Main container with gradient
        GradientPanel mainContainer = new GradientPanel(
            new Color(249, 250, 251),
            new Color(243, 244, 246)
        );
        mainContainer.setLayout(new BorderLayout());
        
        // Create modern sidebar
        sidebar = new ModernSidebar("SK Youth Portal", "Empowering Young Leaders");
        
        // Add sidebar items
        sidebar.addItem("🏠", "Dashboard", "dashboard", true);
        sidebar.addItem("📅", "View Events", "events", false);
        sidebar.addItem("💼", "Opportunities", "opportunities", false);
        sidebar.addItem("📱", "My QR Code", "qr", false);
        sidebar.addItem("💬", "Feedback", "feedback", false);
        sidebar.addItem("👤", "My Profile", "profile", false);
        
        sidebar.setSidebarClickListener(itemId -> navigateToPage(itemId));
        
        // User info panel for sidebar footer
        RoundedPanel userPanel = new RoundedPanel();
        userPanel.setLayout(new BorderLayout(15, 0));
        userPanel.setBackgroundColor(new Color(255, 255, 255, 20));
        userPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel avatar = new JLabel("👤");
        avatar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        infoPanel.setOpaque(false);
        
        JLabel nameLabel = new JLabel("Juan D. Dela Cruz");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        nameLabel.setForeground(Color.WHITE);
        
        JLabel roleLabel = new JLabel("Youth Resident • Purok 5");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        roleLabel.setForeground(new Color(255, 255, 255, 180));
        
        infoPanel.add(nameLabel);
        infoPanel.add(roleLabel);
        
        ModernButton logoutBtn = new ModernButton("🚪");
        logoutBtn.setPreferredSize(new Dimension(40, 40));
        logoutBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        logoutBtn.addActionListener(e -> logout());
        
        userPanel.add(avatar, BorderLayout.WEST);
        userPanel.add(infoPanel, BorderLayout.CENTER);
        userPanel.add(logoutBtn, BorderLayout.EAST);
        
        sidebar.addFooter(userPanel);
        
        mainContainer.add(sidebar, BorderLayout.WEST);
        
        // Main content area
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        // Welcome header
        RoundedPanel headerPanel = new RoundedPanel(20);
        headerPanel.setLayout(new BorderLayout(20, 0));
        headerPanel.setBackgroundColor(Color.WHITE);
        headerPanel.setShadow(true);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setOpaque(false);
        
        JLabel welcomeLabel = new JLabel("Welcome Back, Juan! 👋");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(31, 41, 55));
        
        JLabel dateLabel = new JLabel("Today: " + java.time.LocalDate.now());
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateLabel.setForeground(new Color(107, 114, 128));
        
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);
        welcomePanel.add(dateLabel, BorderLayout.SOUTH);
        
        ModernButton qrButton = new ModernButton("📱 View QR Code");
        qrButton.setPreferredSize(new Dimension(180, 50));
        qrButton.addActionListener(e -> showQRCode());
        
        headerPanel.add(welcomePanel, BorderLayout.CENTER);
        headerPanel.add(qrButton, BorderLayout.EAST);
        
        // Stats cards grid
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        statsPanel.setOpaque(false);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        
        statsPanel.add(new StatCard("Events Attended", "6", "📅", 
            new Color(79, 70, 229), new Color(129, 140, 248)));
        
        statsPanel.add(new StatCard("Volunteer Hours", "24", "⏰", 
            new Color(34, 197, 94), new Color(134, 239, 172)));
        
        statsPanel.add(new StatCard("Applications", "2", "💼", 
            new Color(245, 158, 11), new Color(253, 230, 138)));
        
        statsPanel.add(new StatCard("Recognition", "Youth Star", "🏆", 
            new Color(239, 68, 68), new Color(254, 205, 211)));
        
        // Quick actions and recent activity
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        bottomPanel.setOpaque(false);
        
        bottomPanel.add(createQuickActionsPanel());
        bottomPanel.add(createRecentActivityPanel());
        
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(statsPanel, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        add(mainContainer);
    }
    
    private JPanel createQuickActionsPanel() {
        RoundedPanel panel = new RoundedPanel(20);
        panel.setLayout(new BorderLayout());
        panel.setBackgroundColor(Color.WHITE);
        panel.setShadow(true);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        JLabel title = new JLabel("Quick Actions");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(31, 41, 55));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JPanel actionsPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        actionsPanel.setOpaque(false);
        
        String[][] actions = {
            {"📅", "Register for Events", "events"},
            {"👤", "Update Profile", "profile"},
            {"💬", "Submit Feedback", "feedback"}
        };
        
        for (String[] action : actions) {
            ModernButton btn = new ModernButton(action[0] + "  " + action[1]);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setPreferredSize(new Dimension(300, 50));
            btn.addActionListener(e -> navigateToPage(action[2]));
            actionsPanel.add(btn);
        }
        
        panel.add(title, BorderLayout.NORTH);
        panel.add(actionsPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createRecentActivityPanel() {
        RoundedPanel panel = new RoundedPanel(20);
        panel.setLayout(new BorderLayout());
        panel.setBackgroundColor(Color.WHITE);
        panel.setShadow(true);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        JLabel title = new JLabel("Recent Activity");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(31, 41, 55));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JPanel activitiesPanel = new JPanel();
        activitiesPanel.setLayout(new BoxLayout(activitiesPanel, BoxLayout.Y_AXIS));
        activitiesPanel.setOpaque(false);
        
        String[][] activities = {
            {"✅", "Registered for Tree Planting Drive", "Today, 10:30 AM"},
            {"💼", "Applied for SK Admin Assistant", "Yesterday, 2:15 PM"},
            {"🎉", "Completed 24 volunteer hours", "3 days ago"},
            {"📝", "Submitted event feedback", "1 week ago"}
        };
        
        for (String[] activity : activities) {
            JPanel activityItem = new JPanel(new BorderLayout(15, 0));
            activityItem.setOpaque(false);
            activityItem.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
            
            JLabel icon = new JLabel(activity[0]);
            icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
            
            JPanel textPanel = new JPanel(new BorderLayout());
            textPanel.setOpaque(false);
            
            JLabel text = new JLabel(activity[1]);
            text.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            text.setForeground(new Color(55, 65, 81));
            
            JLabel time = new JLabel(activity[2]);
            time.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            time.setForeground(new Color(156, 163, 175));
            
            textPanel.add(text, BorderLayout.NORTH);
            textPanel.add(time, BorderLayout.SOUTH);
            
            activityItem.add(icon, BorderLayout.WEST);
            activityItem.add(textPanel, BorderLayout.CENTER);
            
            activitiesPanel.add(activityItem);
            
            // Add separator except for last item
            if (!activity.equals(activities[activities.length - 1])) {
                JSeparator separator = new JSeparator();
                separator.setForeground(new Color(229, 231, 235));
                activitiesPanel.add(separator);
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(activitiesPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void navigateToPage(String pageId) {
        dispose();
        
        switch (pageId) {
            case "dashboard":
                new YouthDashboard();
                break;
            case "events":
                // new YouthEventsFrame();
                JOptionPane.showMessageDialog(this, "Opening Events...", "Navigation", 
                    JOptionPane.INFORMATION_MESSAGE);
                break;
            case "opportunities":
                // new YouthOpportunitiesFrame();
                JOptionPane.showMessageDialog(this, "Opening Opportunities...", "Navigation", 
                    JOptionPane.INFORMATION_MESSAGE);
                break;
            case "qr":
                showQRCode();
                break;
            case "feedback":
                // new YouthFeedbackFrame();
                JOptionPane.showMessageDialog(this, "Opening Feedback...", "Navigation", 
                    JOptionPane.INFORMATION_MESSAGE);
                break;
            case "profile":
                // new YouthProfileFrame();
                JOptionPane.showMessageDialog(this, "Opening Profile...", "Navigation", 
                    JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
    
    private void showQRCode() {
        // Create a modern dialog
        JDialog qrDialog = new JDialog(this, "Digital Youth ID", true);
        qrDialog.setSize(450, 550);
        qrDialog.setLocationRelativeTo(this);
        qrDialog.setUndecorated(true);
        
        RoundedPanel mainPanel = new RoundedPanel(25);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackgroundColor(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235), 2));
        
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        
        JLabel title = new JLabel("Your Digital Youth ID");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        
        JButton closeBtn = new JButton("✕");
        closeBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        closeBtn.setForeground(new Color(107, 114, 128));
        closeBtn.setContentAreaFilled(false);
        closeBtn.setBorderPainted(false);
        closeBtn.setFocusPainted(false);
        closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeBtn.addActionListener(e -> qrDialog.dispose());
        
        header.add(title, BorderLayout.WEST);
        header.add(closeBtn, BorderLayout.EAST);
        
        // Content
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        content.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // QR Code placeholder with animation
        RoundedPanel qrPanel = new RoundedPanel(20);
        qrPanel.setLayout(new BorderLayout());
        qrPanel.setBackgroundColor(new Color(249, 250, 251));
        qrPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        qrPanel.setPreferredSize(new Dimension(250, 250));
        
        JLabel qrCode = new JLabel("🔳🔲🔳🔲", SwingConstants.CENTER);
        qrCode.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        
        qrPanel.add(qrCode, BorderLayout.CENTER);
        
        // ID Display
        JLabel idLabel = new JLabel("SK-YOUTH-00123");
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        idLabel.setForeground(new Color(31, 41, 55));
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel nameLabel = new JLabel("Juan D. Dela Cruz");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setForeground(new Color(107, 114, 128));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Download button
        ModernButton downloadBtn = new ModernButton("📥 Download QR Code");
        downloadBtn.setPreferredSize(new Dimension(200, 50));
        downloadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        content.add(qrPanel);
        content.add(Box.createVerticalStrut(25));
        content.add(idLabel);
        content.add(Box.createVerticalStrut(10));
        content.add(nameLabel);
        content.add(Box.createVerticalStrut(30));
        content.add(downloadBtn);
        
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(content, BorderLayout.CENTER);
        
        qrDialog.add(mainPanel);
        qrDialog.setVisible(true);
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new LoginFrame();
        }
    }
}