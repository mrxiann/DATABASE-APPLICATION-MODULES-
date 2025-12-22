package views;

import javax.swing.*;
import java.awt.*;

public class YouthDashboard extends JFrame {
    
    public YouthDashboard() {
        setTitle("Youth Dashboard | SK Youth Portal");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        JPanel mainContainer = new JPanel(new BorderLayout());
        
        // Sidebar - indigo like PHP youth_sidebar.php
        JPanel sidebar = createSidebar();
        mainContainer.add(sidebar, BorderLayout.WEST);
        
        // Content area - matches PHP youth_dashboard.php
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(249, 250, 251));
        
        // Welcome header - matches PHP exactly
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 0, 4, new Color(79, 70, 229)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("Welcome Back, Juan D. Dela Cruz!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(31, 41, 55));
        
        JLabel infoLabel = new JLabel("Purok 5 Youth Resident, registered since 2025");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoLabel.setForeground(new Color(107, 114, 128));
        
        textPanel.add(welcomeLabel, BorderLayout.NORTH);
        textPanel.add(infoLabel, BorderLayout.CENTER);
        
        // QR Code button - matches PHP
        JButton qrButton = new JButton("View My Digital ID (QR)");
        qrButton.setBackground(new Color(79, 70, 229));
        qrButton.setForeground(Color.WHITE);
        qrButton.setFont(new Font("Arial", Font.BOLD, 14));
        qrButton.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        qrButton.setFocusPainted(false);
        qrButton.addActionListener(e -> showQRCode());
        
        headerPanel.add(textPanel, BorderLayout.CENTER);
        headerPanel.add(qrButton, BorderLayout.EAST);
        
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Stats cards - 4 cards like PHP
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 15));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        statsPanel.setBackground(new Color(249, 250, 251));
        
        statsPanel.add(createYouthStatCard("Events Attended", "6", "📅", Color.BLUE));
        statsPanel.add(createYouthStatCard("Volunteer Hours", "24", "⏰", Color.GREEN));
        statsPanel.add(createYouthStatCard("Applications Sent", "2", "💼", Color.ORANGE));
        statsPanel.add(createYouthStatCard("Latest Recognition", "SK Youth of the Month", "🏆", Color.RED));
        
        contentPanel.add(statsPanel, BorderLayout.CENTER);
        
        // Bottom panels - matches PHP 2-column layout
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        bottomPanel.setBackground(new Color(249, 250, 251));
        
        // Left: Quick Links & Opportunities
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(249, 250, 251));
        
        // Quick Links - matches PHP
        leftPanel.add(createQuickLinksPanel(), BorderLayout.NORTH);
        leftPanel.add(Box.createVerticalStrut(20));
        
        // Opportunities - matches PHP
        leftPanel.add(createOpportunitiesPanel(), BorderLayout.CENTER);
        
        // Right: Latest Activity - matches PHP
        JPanel rightPanel = createActivityPanel();
        
        bottomPanel.add(leftPanel);
        bottomPanel.add(rightPanel);
        
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        add(mainContainer);
    }
    
    private JPanel createYouthStatCard(String title, String value, String icon, Color borderColor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 4, 0, borderColor),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(200, 150));
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 32));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setForeground(Color.GRAY);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        card.add(iconLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        card.add(titleLabel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private JPanel createQuickLinksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Quick Links"));
        
        JPanel linksPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        linksPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        linksPanel.setBackground(Color.WHITE);
        
        Object[][] links = {
            {"Register for New Events", "📅", new Color(59, 130, 246)},
            {"Update My Profile", "👤", new Color(34, 197, 94)},
            {"Submit Feedback/Inquiry", "💬", new Color(239, 68, 68)}
        };
        
        for (Object[] link : links) {
            String text = (String) link[0];
            String icon = (String) link[1];
            Color color = (Color) link[2];
            
            JButton linkButton = new JButton(icon + "  " + text);
            linkButton.setHorizontalAlignment(SwingConstants.LEFT);
            linkButton.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 20));
            linkButton.setForeground(color);
            linkButton.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));
            linkButton.setFont(new Font("Arial", Font.BOLD, 13));
            linkButton.setFocusPainted(false);
            
            // Add action listeners
            if (text.contains("Events")) {
                linkButton.addActionListener(e -> new YouthEventsFrame());
            } else if (text.contains("Profile")) {
                linkButton.addActionListener(e -> new YouthProfileFrame());
            } else if (text.contains("Feedback")) {
                linkButton.addActionListener(e -> new YouthFeedbackFrame());
            }
            
            linksPanel.add(linkButton);
        }
        
        panel.add(linksPanel, BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createOpportunitiesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("New Opportunities"));
        panel.setPreferredSize(new Dimension(300, 200));
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        contentPanel.setBackground(Color.WHITE);
        
        // Opportunity card - matches PHP youth_opportunities.php
        JPanel oppCard = new JPanel(new BorderLayout());
        oppCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 0, 4, Color.BLUE),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        oppCard.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("SK Admin Assistant (P/T)");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(31, 41, 55));
        
        JLabel descLabel = new JLabel("Support SK council with clerical work");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(new Color(107, 114, 128));
        
        JPanel detailsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.add(createDetailLabel("💼", "Compensation: P50/hr"));
        detailsPanel.add(createDetailLabel("📅", "Deadline: Feb 28, 2026"));
        
        oppCard.add(titleLabel, BorderLayout.NORTH);
        oppCard.add(descLabel, BorderLayout.CENTER);
        oppCard.add(detailsPanel, BorderLayout.SOUTH);
        
        contentPanel.add(oppCard);
        contentPanel.add(Box.createVerticalStrut(10));
        
        // View all link
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        linkPanel.setBackground(Color.WHITE);
        JButton viewAllButton = new JButton("View All Opportunities →");
        viewAllButton.setForeground(new Color(79, 70, 229));
        viewAllButton.setFont(new Font("Arial", Font.BOLD, 12));
        viewAllButton.setBorderPainted(false);
        viewAllButton.setContentAreaFilled(false);
        viewAllButton.addActionListener(e -> new YouthOpportunitiesFrame());
        linkPanel.add(viewAllButton);
        
        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(linkPanel);
        
        panel.add(contentPanel, BorderLayout.CENTER);
        return panel;
    }
    
    private JLabel createDetailLabel(String icon, String text) {
        JLabel label = new JLabel(icon + " " + text);
        label.setFont(new Font("Arial", Font.PLAIN, 10));
        label.setForeground(new Color(107, 114, 128));
        return label;
    }
    
    private JPanel createActivityPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("My Latest Activity"));
        
        JPanel activitiesPanel = new JPanel();
        activitiesPanel.setLayout(new BoxLayout(activitiesPanel, BoxLayout.Y_AXIS));
        activitiesPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        activitiesPanel.setBackground(Color.WHITE);
        
        String[][] activities = {
            {"✅", "Successfully registered for the Tree Planting Drive.", "Feb 15, 2026"},
            {"💼", "Applied for the SK Admin Assistant position.", "Feb 10, 2026"}
        };
        
        for (String[] activity : activities) {
            JPanel activityItem = new JPanel(new BorderLayout(10, 0));
            activityItem.setBackground(Color.WHITE);
            activityItem.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            
            JLabel iconLabel = new JLabel(activity[0]);
            iconLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            iconLabel.setForeground(new Color(79, 70, 229));
            
            JPanel textPanel = new JPanel(new BorderLayout());
            textPanel.setBackground(Color.WHITE);
            textPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(229, 231, 235)));
            
            JLabel textLabel = new JLabel("<html>" + activity[1] + "</html>");
            textLabel.setFont(new Font("Arial", Font.PLAIN, 13));
            
            JLabel dateLabel = new JLabel(activity[2]);
            dateLabel.setFont(new Font("Arial", Font.PLAIN, 11));
            dateLabel.setForeground(Color.GRAY);
            
            textPanel.add(textLabel, BorderLayout.CENTER);
            textPanel.add(dateLabel, BorderLayout.SOUTH);
            
            activityItem.add(iconLabel, BorderLayout.WEST);
            activityItem.add(textPanel, BorderLayout.CENTER);
            
            activitiesPanel.add(activityItem);
            activitiesPanel.add(Box.createVerticalStrut(15));
        }
        
        JScrollPane scrollPane = new JScrollPane(activitiesPanel);
        scrollPane.setBorder(null);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
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
            
            // Highlight current page
            if (item[1].equals("Dashboard")) {
                menuButton.setBackground(new Color(67, 56, 202));
                menuButton.setFont(new Font("Arial", Font.BOLD, 14));
            }
            
            // Add navigation
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
        dispose(); // Close current window
        
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
                showQRCode();
                // Don't create new dashboard here, just show dialog
                break;
            case "feedback":
                new YouthFeedbackFrame();
                break;
            case "profile":
                new YouthProfileFrame();
                break;
        }
    }
    
    private void showQRCode() {
        JDialog qrDialog = new JDialog(this, "Your Official SK Youth QR ID", true);
        qrDialog.setSize(400, 450);
        qrDialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel qrPlaceholder = new JLabel("[QR CODE IMAGE]", SwingConstants.CENTER);
        qrPlaceholder.setFont(new Font("Arial", Font.BOLD, 16));
        qrPlaceholder.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(79, 70, 229), 4),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        qrPlaceholder.setPreferredSize(new Dimension(200, 200));
        
        JLabel idLabel = new JLabel("SK-YOUTH-00123");
        idLabel.setFont(new Font("Monospace", Font.BOLD, 18));
        idLabel.setForeground(new Color(31, 41, 55));
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel descLabel = new JLabel("This code verifies your identity as a registered youth.");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(Color.GRAY);
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel warningLabel = new JLabel("⚠️ Keep this code private. Do not share screenshots with others.");
        warningLabel.setFont(new Font("Arial", Font.BOLD, 11));
        warningLabel.setForeground(new Color(239, 68, 68));
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        JButton downloadButton = new JButton("📥 Download Image");
        downloadButton.setBackground(new Color(34, 197, 94));
        downloadButton.setForeground(Color.WHITE);
        
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> qrDialog.dispose());
        
        buttonPanel.add(downloadButton);
        buttonPanel.add(closeButton);
        
        panel.add(qrPlaceholder, BorderLayout.CENTER);
        panel.add(idLabel, BorderLayout.NORTH);
        panel.add(descLabel, BorderLayout.SOUTH);
        panel.add(warningLabel, BorderLayout.PAGE_END);
        panel.add(buttonPanel, BorderLayout.PAGE_START);
        
        qrDialog.add(panel);
        qrDialog.setVisible(true);
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