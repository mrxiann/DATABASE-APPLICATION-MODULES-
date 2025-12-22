package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class YouthDashboard extends JFrame {
    private ModernSidebar sidebar;
    
    public YouthDashboard() {
        setTitle("Youth Dashboard | SK Connect");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        // Main container
        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setBackground(new Color(249, 250, 251));
        
        // Create modern sidebar
        sidebar = new ModernSidebar("SK Youth Portal", 
            new Color(79, 70, 229), new Color(99, 102, 241));
        
        // Add sidebar items
        sidebar.addItem("🏠", "Dashboard", "dashboard", true);
        sidebar.addItem("📅", "View Events", "events", false);
        sidebar.addItem("💼", "Opportunities", "opportunities", false);
        sidebar.addItem("📱", "My QR Code", "qr", false);
        sidebar.addItem("💬", "Submit Feedback", "feedback", false);
        sidebar.addItem("👤", "My Profile", "profile", false);
        
        sidebar.setSidebarClickListener(itemId -> navigateToPage(itemId));
        
        // Add user info at bottom of sidebar
        sidebar.add(Box.createVerticalGlue());
        JPanel userPanel = createUserPanel();
        sidebar.add(userPanel);
        
        mainContainer.add(sidebar, BorderLayout.WEST);
        
        // Main content area
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(249, 250, 251));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        // Welcome header with gradient
        JPanel headerPanel = createWelcomeHeader();
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Stats cards in grid
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        statsPanel.setBackground(new Color(249, 250, 251));
        
        statsPanel.add(createModernStatCard("Events Attended", "6", "📅", 
            new Color(79, 70, 229), new Color(129, 140, 248)));
        statsPanel.add(createModernStatCard("Volunteer Hours", "24", "⏰", 
            new Color(34, 197, 94), new Color(134, 239, 172)));
        statsPanel.add(createModernStatCard("Applications Sent", "2", "💼", 
            new Color(245, 158, 11), new Color(253, 230, 138)));
        statsPanel.add(createModernStatCard("Recognition", "Youth of the Month", "🏆", 
            new Color(239, 68, 68), new Color(254, 205, 211)));
        
        contentPanel.add(statsPanel, BorderLayout.CENTER);
        
        // Bottom panels
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        bottomPanel.setBackground(new Color(249, 250, 251));
        
        bottomPanel.add(createQuickActionsPanel());
        bottomPanel.add(createRecentActivityPanel());
        
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        add(mainContainer);
    }
    
    private JPanel createWelcomeHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1, true),
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));
        
        // Left side - Welcome text
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(Color.WHITE);
        
        JLabel welcomeLabel = new JLabel("Welcome Back, Juan!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(31, 41, 55));
        
        JLabel infoLabel = new JLabel("Purok 5 Youth Resident • Member since 2025");
        infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        infoLabel.setForeground(new Color(107, 114, 128));
        
        textPanel.add(welcomeLabel, BorderLayout.NORTH);
        textPanel.add(infoLabel, BorderLayout.CENTER);
        
        // Right side - QR Code button
        JButton qrButton = new RoundedButton("View My QR Code", new Color(79, 70, 229));
        qrButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        qrButton.setPreferredSize(new Dimension(180, 50));
        qrButton.addActionListener(e -> showQRCode());
        
        panel.add(textPanel, BorderLayout.CENTER);
        panel.add(qrButton, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel createModernStatCard(String title, String value, String icon, 
                                       Color startColor, Color endColor) {
        JPanel card = new GradientCard(startColor, endColor);
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        // Top: Icon and title
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 32));
        iconLabel.setForeground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(new Color(255, 255, 255, 220));
        
        topPanel.add(iconLabel, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.EAST);
        
        // Middle: Value
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        card.add(topPanel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createQuickActionsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1, true),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel title = new JLabel("Quick Actions");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(31, 41, 55));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        JPanel actionsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        actionsPanel.setBackground(Color.WHITE);
        
        String[][] actions = {
            {"📅", "Register for Events", "events"},
            {"👤", "Update Profile", "profile"},
            {"💬", "Submit Feedback", "feedback"}
        };
        
        for (String[] action : actions) {
            JButton actionButton = new RoundedButton(action[0] + "  " + action[1], 
                new Color(243, 244, 246));
            actionButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            actionButton.setForeground(new Color(55, 65, 81));
            actionButton.setHorizontalAlignment(SwingConstants.LEFT);
            actionButton.addActionListener(e -> navigateToPage(action[2]));
            actionsPanel.add(actionButton);
        }
        
        panel.add(title, BorderLayout.NORTH);
        panel.add(actionsPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createRecentActivityPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1, true),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel title = new JLabel("Recent Activity");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(31, 41, 55));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        JPanel activitiesPanel = new JPanel();
        activitiesPanel.setLayout(new BoxLayout(activitiesPanel, BoxLayout.Y_AXIS));
        activitiesPanel.setBackground(Color.WHITE);
        
        String[][] activities = {
            {"✅", "Registered for Tree Planting Drive", "Today"},
            {"💼", "Applied for SK Admin Assistant", "2 days ago"},
            {"🎉", "Completed 24 volunteer hours", "1 week ago"}
        };
        
        for (String[] activity : activities) {
            JPanel activityItem = new JPanel(new BorderLayout(10, 0));
            activityItem.setBackground(Color.WHITE);
            activityItem.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            
            JLabel iconLabel = new JLabel(activity[0]);
            iconLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            
            JPanel textPanel = new JPanel(new BorderLayout());
            textPanel.setBackground(Color.WHITE);
            
            JLabel textLabel = new JLabel(activity[1]);
            textLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            
            JLabel dateLabel = new JLabel(activity[2]);
            dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            dateLabel.setForeground(new Color(156, 163, 175));
            
            textPanel.add(textLabel, BorderLayout.NORTH);
            textPanel.add(dateLabel, BorderLayout.SOUTH);
            
            activityItem.add(iconLabel, BorderLayout.WEST);
            activityItem.add(textPanel, BorderLayout.CENTER);
            
            activitiesPanel.add(activityItem);
            activitiesPanel.add(Box.createVerticalStrut(5));
        }
        
        JScrollPane scrollPane = new JScrollPane(activitiesPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createUserPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(new Color(79, 70, 229, 150));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(255, 255, 255, 50)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel avatar = new JLabel("👤");
        avatar.setFont(new Font("Arial", Font.PLAIN, 24));
        
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setBackground(new Color(79, 70, 229, 0));
        JLabel nameLabel = new JLabel("Juan D. Dela Cruz");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        JLabel roleLabel = new JLabel("Youth Resident");
        roleLabel.setForeground(new Color(255, 255, 255, 180));
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        
        infoPanel.add(nameLabel);
        infoPanel.add(roleLabel);
        
        JButton logoutButton = new JButton("🚪");
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(255, 255, 255, 30));
        logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        logoutButton.setFocusPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(e -> logout());
        
        panel.add(avatar, BorderLayout.WEST);
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(logoutButton, BorderLayout.EAST);
        
        return panel;
    }
    
    private void navigateToPage(String pageId) {
        dispose();
        
        switch (pageId) {
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
                new YouthDashboard(); // Return to dashboard
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
        JDialog qrDialog = new JDialog(this, "Your Digital Youth ID", true);
        qrDialog.setSize(400, 500);
        qrDialog.setLocationRelativeTo(this);
        qrDialog.setUndecorated(true);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(79, 70, 229), 2, true));
        
        // Close button
        JButton closeButton = new JButton("✕");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setForeground(Color.GRAY);
        closeButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        closeButton.setFocusPainted(false);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener(e -> qrDialog.dispose());
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        topPanel.add(new JLabel("Your Digital Youth ID", SwingConstants.CENTER), BorderLayout.CENTER);
        topPanel.add(closeButton, BorderLayout.EAST);
        
        // QR Content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        contentPanel.setBackground(Color.WHITE);
        
        // QR Code placeholder with animation
        JLabel qrPlaceholder = new JLabel("🔳🔲🔳🔲", SwingConstants.CENTER);
        qrPlaceholder.setFont(new Font("Arial", Font.PLAIN, 72));
        qrPlaceholder.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(79, 70, 229), 2, true),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        
        JLabel idLabel = new JLabel("SK-YOUTH-00123");
        idLabel.setFont(new Font("Monospace", Font.BOLD, 18));
        idLabel.setForeground(new Color(31, 41, 55));
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel nameLabel = new JLabel("Juan D. Dela Cruz");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setForeground(new Color(107, 114, 128));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton downloadButton = new RoundedButton("📥 Download QR Code", new Color(79, 70, 229));
        downloadButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        downloadButton.setPreferredSize(new Dimension(200, 45));
        
        contentPanel.add(qrPlaceholder);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(idLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(nameLabel);
        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(downloadButton);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        qrDialog.add(panel);
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
    
    // Custom Components
    class GradientCard extends JPanel {
        private Color startColor, endColor;
        
        public GradientCard(Color start, Color end) {
            this.startColor = start;
            this.endColor = end;
            setOpaque(false);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Create gradient
            GradientPaint gradient = new GradientPaint(
                0, 0, startColor,
                getWidth(), getHeight(), endColor
            );
            g2.setPaint(gradient);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            
            g2.dispose();
            super.paintComponent(g);
        }
    }
    
    class RoundedButton extends JButton {
        private Color backgroundColor;
        
        public RoundedButton(String text, Color bgColor) {
            super(text);
            this.backgroundColor = bgColor;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (getModel().isPressed()) {
                g2.setColor(backgroundColor.darker());
            } else if (getModel().isRollover()) {
                g2.setColor(backgroundColor.brighter());
            } else {
                g2.setColor(backgroundColor);
            }
            
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            g2.setColor(getForeground());
            g2.setFont(getFont());
            
            FontMetrics fm = g2.getFontMetrics();
            String text = getText();
            int x = (getWidth() - fm.stringWidth(text)) / 2;
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            g2.drawString(text, x, y);
            
            g2.dispose();
        }
        
        @Override
        protected void paintBorder(Graphics g) {
            // No border
        }
    }
}