package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class YouthEventsFrame extends JFrame {
    private JTable eventsTable;
    private DefaultTableModel tableModel;
    
    public YouthEventsFrame() {
        setTitle("SK Youth Events | View & Register");
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
        
        // Content area - matches PHP events.php
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(249, 250, 251));
        
        // Header - matches PHP
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Upcoming SK Youth Events");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(31, 41, 55));
        
        JLabel subtitleLabel = new JLabel("Browse and register for upcoming youth activities");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.GRAY);
        
        JButton myEventsButton = new JButton("📋 My Event Registrations");
        myEventsButton.setBackground(new Color(79, 70, 229));
        myEventsButton.setForeground(Color.WHITE);
        myEventsButton.setFont(new Font("Arial", Font.BOLD, 12));
        myEventsButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);
        headerPanel.add(myEventsButton, BorderLayout.EAST);
        
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Filters - matches PHP
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        filterPanel.setBackground(Color.WHITE);
        filterPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel filterLabel = new JLabel("Filter by:");
        filterLabel.setFont(new Font("Arial", Font.BOLD, 12));
        
        String[] categories = {"All Events", "Volunteer Work", "Trainings", "Social Activities", "Sports"};
        JComboBox<String> categoryCombo = new JComboBox<>(categories);
        categoryCombo.setPreferredSize(new Dimension(150, 30));
        
        String[] statuses = {"All Status", "Open", "Full", "Ongoing", "Completed"};
        JComboBox<String> statusCombo = new JComboBox<>(statuses);
        statusCombo.setPreferredSize(new Dimension(120, 30));
        
        JButton applyFilterButton = new JButton("Apply Filters");
        applyFilterButton.setBackground(new Color(79, 70, 229));
        applyFilterButton.setForeground(Color.WHITE);
        
        filterPanel.add(filterLabel);
        filterPanel.add(categoryCombo);
        filterPanel.add(statusCombo);
        filterPanel.add(applyFilterButton);
        
        contentPanel.add(filterPanel, BorderLayout.CENTER);
        
        // Events Table - matches PHP layout
        String[] columns = {"Event", "Date & Time", "Location", "Type", "Slots", "Status", "Action"};
        Object[][] data = {
            {"Coastal Clean-Up Drive", "Feb 20, 2026 8:00 AM", "Beachfront", "Volunteer", "85/100", "Open", "Register"},
            {"Youth Leadership Summit", "Mar 5, 2026 9:00 AM", "SK Hall", "Training", "120/150", "Open", "Register"},
            {"Basketball Tournament", "Mar 15, 2026 2:00 PM", "Court", "Sports", "24/24", "Full", "View"},
            {"Tree Planting Activity", "Feb 28, 2026 7:00 AM", "Hill Area", "Volunteer", "45/50", "Open", "Register"}
        };
        
        tableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6; // Only action column is editable
            }
        };
        
        eventsTable = new JTable(tableModel);
        eventsTable.setRowHeight(50);
        eventsTable.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        eventsTable.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox()));
        
        JScrollPane scrollPane = new JScrollPane(eventsTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Available Events"));
        
        contentPanel.add(scrollPane, BorderLayout.SOUTH);
        
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        add(mainContainer);
    }
    
    // Button renderer for table actions
    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            setBackground(new Color(79, 70, 229));
            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 11));
            return this;
        }
    }
    
    // Button editor for table actions
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;
        
        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }
        
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            button.setBackground(new Color(34, 197, 94));
            button.setForeground(Color.WHITE);
            isPushed = true;
            return button;
        }
        
        public Object getCellEditorValue() {
            if (isPushed) {
                // Handle registration action
                int row = eventsTable.getEditingRow();
                String eventName = (String) tableModel.getValueAt(row, 0);
                JOptionPane.showMessageDialog(null, 
                    "Registering for: " + eventName + "\n\nRegistration form will appear.",
                    "Event Registration", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
            isPushed = false;
            return label;
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
            
            // Highlight current page
            if (item[1].equals("View Events")) {
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
                // Return to dashboard which will show QR
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