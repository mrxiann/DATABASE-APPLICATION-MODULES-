package views.youth;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class YouthDashboardFrame extends JFrame {
    
    public YouthDashboardFrame() {
        setTitle("Youth Dashboard | SK Portal");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        // Main container with sidebar and content
        JPanel mainContainer = new JPanel(new BorderLayout());
        
        // Create sidebar (simplified for now)
        JPanel sidebar = createSidebar();
        mainContainer.add(sidebar, BorderLayout.WEST);
        
        // Create main content area
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Welcome header
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome Back, Juan D. Dela Cruz!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        JLabel infoLabel = new JLabel("Purok 5 Youth Resident, registered since 2025");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        infoLabel.setForeground(Color.GRAY);
        
        headerPanel.add(welcomeLabel, BorderLayout.NORTH);
        headerPanel.add(infoLabel, BorderLayout.CENTER);
        
        // QR Code button
        JButton qrButton = new JButton("View My Digital ID (QR)");
        qrButton.setBackground(new Color(79, 70, 229));
        qrButton.setForeground(Color.WHITE);
        qrButton.setFont(new Font("Arial", Font.BOLD, 14));
        qrButton.addActionListener(e -> showQRCode());
        headerPanel.add(qrButton, BorderLayout.EAST);
        
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Stats panel
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 15));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Stat 1: Events Attended
        statsPanel.add(createStatCard("Events Attended", "6", Color.BLUE, "calendar-check"));
        
        // Stat 2: Volunteer Hours
        statsPanel.add(createStatCard("Volunteer Hours", "24", Color.GREEN, "clock"));
        
        // Stat 3: Applications Sent
        statsPanel.add(createStatCard("Applications Sent", "2", Color.ORANGE, "briefcase"));
        
        // Stat 4: Latest Recognition
        statsPanel.add(createStatCard("Latest Recognition", "SK Youth of the Month", Color.RED, "trophy"));
        
        contentPanel.add(statsPanel, BorderLayout.CENTER);
        
        // Quick links and activities
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        
        // Quick Links
        JPanel quickLinksPanel = new JPanel(new BorderLayout());
        quickLinksPanel.setBorder(BorderFactory.createTitledBorder("Quick Links"));
        
        JPanel linksPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        linksPanel.add(createLinkButton("Register for New Events", Color.BLUE));
        linksPanel.add(createLinkButton("Update My Profile", Color.GREEN));
        linksPanel.add(createLinkButton("Submit Feedback/Inquiry", Color.RED));
        
        quickLinksPanel.add(linksPanel, BorderLayout.CENTER);
        
        // Latest Activity
        JPanel activityPanel = new JPanel(new BorderLayout());
        activityPanel.setBorder(BorderFactory.createTitledBorder("My Latest Activity"));
        
        JTextArea activityArea = new JTextArea();
        activityArea.setEditable(false);
        activityArea.setText("• Feb 15, 2026: Successfully registered for Tree Planting Drive\n" +
                           "• Feb 10, 2026: Applied for SK Admin Assistant position");
        activityArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(activityArea);
        activityPanel.add(scrollPane, BorderLayout.CENTER);
        
        bottomPanel.add(quickLinksPanel);
        bottomPanel.add(activityPanel);
        
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        
        add(mainContainer);
    }
    
    private JPanel createStatCard(String title, String value, Color color, String iconName) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setBackground(Color.WHITE);
        
        // Icon (using text for simplicity)
        JLabel iconLabel = new JLabel(getIconText(iconName));
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        iconLabel.setForeground(color);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Value
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 32));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setForeground(Color.GRAY);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        card.add(iconLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        card.add(titleLabel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private JButton createLinkButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 20));
        button.setForeground(color);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        
        // Add appropriate action listeners
        if (text.contains("Events")) {
            button.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opening Events..."));
        } else if (text.contains("Profile")) {
            button.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opening Profile..."));
        } else if (text.contains("Feedback")) {
            button.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opening Feedback..."));
        }
        
        return button;
    }
    
    private String getIconText(String iconName) {
        switch(iconName) {
            case "calendar-check": return "📅";
            case "clock": return "⏰";
            case "briefcase": return "💼";
            case "trophy": return "🏆";
            default: return "📊";
        }
    }
    
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(79, 70, 229)); // Indigo color
        sidebar.setPreferredSize(new Dimension(250, getHeight()));
        
        // Logo/Title
        JLabel title = new JLabel("SK Youth Portal");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20));
        sidebar.add(title);
        
        // Navigation buttons
        String[] menuItems = {"Dashboard", "View Events", "Opportunities", 
                              "My QR Code", "Submit Feedback", "My Profile"};
        
        for (String item : menuItems) {
            JButton menuButton = new JButton(item);
            menuButton.setForeground(Color.WHITE);
            menuButton.setBackground(new Color(79, 70, 229));
            menuButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            menuButton.setHorizontalAlignment(SwingConstants.LEFT);
            menuButton.setFocusPainted(false);
            
            // Highlight Dashboard button
            if (item.equals("Dashboard")) {
                menuButton.setBackground(new Color(67, 56, 202));
            }
            
            sidebar.add(menuButton);
        }
        
        // Spacer
        sidebar.add(Box.createVerticalGlue());
        
        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(220, 53, 69));
        logoutButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        logoutButton.addActionListener(e -> logout());
        
        sidebar.add(logoutButton);
        sidebar.add(Box.createVerticalStrut(20));
        
        return sidebar;
    }
    
    private void showQRCode() {
        JDialog qrDialog = new JDialog(this, "Your Official SK Youth QR ID", true);
        qrDialog.setSize(400, 500);
        qrDialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // QR Code placeholder
        JLabel qrLabel = new JLabel("QR CODE IMAGE HERE");
        qrLabel.setFont(new Font("Arial", Font.BOLD, 24));
        qrLabel.setHorizontalAlignment(SwingConstants.CENTER);
        qrLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        qrLabel.setPreferredSize(new Dimension(200, 200));
        
        // ID Display
        JLabel idLabel = new JLabel("SK-PH-0000452");
        idLabel.setFont(new Font("Arial", Font.BOLD, 20));
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Description
        JLabel descLabel = new JLabel("Present this code for event check-in/out and identification.");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> qrDialog.dispose());
        
        panel.add(qrLabel, BorderLayout.CENTER);
        panel.add(idLabel, BorderLayout.NORTH);
        panel.add(descLabel, BorderLayout.SOUTH);
        panel.add(closeButton, BorderLayout.PAGE_END);
        
        qrDialog.add(panel);
        qrDialog.setVisible(true);
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to logout?", 
            "Confirm Logout", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Close current window
            new LoginFrame(); // Go back to login
        }
    }
}