package views.components;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {
    private String userRole; // "admin" or "youth"
    
    public SidebarPanel(String userRole) {
        this.userRole = userRole;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(getRoleColor());
        setPreferredSize(new Dimension(250, 800));
        
        initComponents();
    }
    
    private Color getRoleColor() {
        return userRole.equals("admin") 
            ? new Color(30, 64, 175) // Blue for admin
            : new Color(79, 70, 229); // Indigo for youth
    }
    
    private void initComponents() {
        // Title
        String title = userRole.equals("admin") 
            ? "SK Admin Portal" 
            : "SK Youth Portal";
            
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20));
        add(titleLabel);
        
        // Menu items based on role
        String[] menuItems;
        if (userRole.equals("admin")) {
            menuItems = new String[]{
                "Dashboard", "User Management", "Event Management",
                "Opportunities Mgmt", "Attendance Record", "Manage Feedback"
            };
        } else {
            menuItems = new String[]{
                "Dashboard", "View Events", "Opportunities",
                "My QR Code", "Submit Feedback", "My Profile"
            };
        }
        
        // Add menu buttons
        for (String item : menuItems) {
            JButton menuButton = createMenuButton(item);
            add(menuButton);
        }
        
        // Spacer
        add(Box.createVerticalGlue());
        
        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(220, 53, 69));
        logoutButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(logoutButton);
        add(Box.createVerticalStrut(20));
    }
    
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(getRoleColor());
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(250, 40));
        
        // Add action listener
        button.addActionListener(e -> handleMenuClick(text));
        
        return button;
    }
    
    private void handleMenuClick(String menuText) {
        // This would be connected to a NavigationController
        System.out.println("Menu clicked: " + menuText);
        // In a full implementation, this would switch between frames
    }
}