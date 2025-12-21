package views.youth;

import javax.swing.*;
import java.awt.*;
import views.auth.LoginFrame;

public class YouthDashboardFrame extends JFrame {
    
    public YouthDashboardFrame() {
        setTitle("Youth Dashboard | SK Portal");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        // Main container
        JPanel mainContainer = new JPanel(new BorderLayout());
        
        // Create sidebar
        JPanel sidebar = createSidebar();
        mainContainer.add(sidebar, BorderLayout.WEST);
        
        // Create content area
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);
        
        // Welcome header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        
        JLabel welcomeLabel = new JLabel("Welcome Back, Juan D. Dela Cruz!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
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
        
        // Stats cards
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        statsPanel.setBackground(Color.WHITE);
        
        statsPanel.add(createStatCard("Events Attended", "6", Color.BLUE));
        statsPanel.add(createStatCard("Volunteer Hours", "24", Color.GREEN));
        statsPanel.add(createStatCard("Applications Sent", "2", Color.ORANGE));
        statsPanel.add(createStatCard("Latest Recognition", "SK Youth of the Month", Color.RED));
        
        contentPanel.add(statsPanel, BorderLayout.CENTER);
        
        // Quick links
        JPanel linksPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        linksPanel.setBackground(Color.WHITE);
        linksPanel.setBorder(BorderFactory.createTitledBorder("Quick Links"));
        
        linksPanel.add(createLinkButton("Register for New Events", Color.BLUE));
        linksPanel.add(createLinkButton("Update My Profile", Color.GREEN));
        linksPanel.add(createLinkButton("Submit Feedback/Inquiry", Color.RED));
        
        contentPanel.add(linksPanel, BorderLayout.SOUTH);
        
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        
        add(mainContainer);
    }
    
    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(color, 2));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(150, 100));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        titleLabel.setForeground(Color.GRAY);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
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
        
        return button;
    }
    
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(79, 70, 229));
        sidebar.setPreferredSize(new Dimension(200, 600));
        
        // Logo/Title
        JLabel title = new JLabel("SK Youth Portal");
        title.setFont(new Font("Arial", Font.BOLD, 16));
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
            menuButton.setMaximumSize(new Dimension(200, 40));
            
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
        logoutButton.setMaximumSize(new Dimension(200, 40));
        logoutButton.addActionListener(e -> logout());
        
        sidebar.add(logoutButton);
        sidebar.add(Box.createVerticalStrut(20));
        
        return sidebar;
    }
    
    private void showQRCode() {
        JDialog qrDialog = new JDialog(this, "Your Official SK Youth QR ID", true);
        qrDialog.setSize(400, 300);
        qrDialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel qrLabel = new JLabel("[QR CODE IMAGE WOULD BE HERE]");
        qrLabel.setFont(new Font("Arial", Font.BOLD, 16));
        qrLabel.setHorizontalAlignment(SwingConstants.CENTER);
        qrLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        qrLabel.setPreferredSize(new Dimension(150, 150));
        
        JLabel idLabel = new JLabel("SK-PH-0000452");
        idLabel.setFont(new Font("Arial", Font.BOLD, 18));
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> qrDialog.dispose());
        
        panel.add(qrLabel, BorderLayout.CENTER);
        panel.add(idLabel, BorderLayout.NORTH);
        panel.add(closeButton, BorderLayout.SOUTH);
        
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