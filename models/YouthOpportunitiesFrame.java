package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class YouthOpportunitiesFrame extends JFrame {
    private JTable opportunitiesTable;
    private DefaultTableModel tableModel;
    
    public YouthOpportunitiesFrame() {
        setTitle("SK Youth Opportunities | Jobs & Volunteer Work");
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
        
        // Content area - matches PHP youth_opportunities.php
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(249, 250, 251));
        
        // Header - matches PHP
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Youth Opportunities");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(31, 41, 55));
        
        JLabel subtitleLabel = new JLabel("Browse job openings, internships, and volunteer positions");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.GRAY);
        
        JButton myApplicationsButton = new JButton("📋 My Applications");
        myApplicationsButton.setBackground(new Color(79, 70, 229));
        myApplicationsButton.setForeground(Color.WHITE);
        myApplicationsButton.setFont(new Font("Arial", Font.BOLD, 12));
        myApplicationsButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);
        headerPanel.add(myApplicationsButton, BorderLayout.EAST);
        
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Stats cards - matches PHP
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 15, 15));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        statsPanel.setBackground(new Color(249, 250, 251));
        
        statsPanel.add(createOppStatCard("Total Opportunities", "12", "💼", Color.BLUE));
        statsPanel.add(createOppStatCard("Applications Sent", "2", "📤", Color.GREEN));
        statsPanel.add(createOppStatCard("Under Review", "1", "⏳", Color.ORANGE));
        
        contentPanel.add(statsPanel, BorderLayout.CENTER);
        
        // Opportunities Table - matches PHP layout
        String[] columns = {"Position", "Type", "Location", "Compensation", "Deadline", "Status", "Action"};
        Object[][] data = {
            {"SK Admin Assistant", "Part-time Job", "SK Office", "P50/hour", "Feb 28, 2026", "Open", "Apply"},
            {"Event Coordinator Volunteer", "Volunteer", "Various", "Certificate", "Mar 10, 2026", "Open", "Apply"},
            {"Web Dev Intern", "Internship", "Remote", "Allowance", "Mar 15, 2026", "Open", "Apply"},
            {"Tutorial Assistant", "Part-time Job", "School", "P3,000/month", "Feb 25, 2026", "Closing Soon", "Apply"}
        };
        
        tableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };
        
        opportunitiesTable = new JTable(tableModel);
        opportunitiesTable.setRowHeight(50);
        
        // Style the table
        opportunitiesTable.getColumnModel().getColumn(6).setCellRenderer(new OppButtonRenderer());
        opportunitiesTable.getColumnModel().getColumn(6).setCellEditor(new OppButtonEditor(new JCheckBox()));
        
        JScrollPane scrollPane = new JScrollPane(opportunitiesTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Available Opportunities"));
        
        contentPanel.add(scrollPane, BorderLayout.SOUTH);
        
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        add(mainContainer);
    }
    
    private JPanel createOppStatCard(String title, String value, String icon, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 4, 0, 0, color),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setBackground(Color.WHITE);
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        iconLabel.setForeground(color);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 32));
        valueLabel.setForeground(new Color(31, 41, 55));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setForeground(Color.GRAY);
        
        card.add(iconLabel, BorderLayout.WEST);
        card.add(valueLabel, BorderLayout.CENTER);
        card.add(titleLabel, BorderLayout.SOUTH);
        
        return card;
    }
    
    class OppButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public OppButtonRenderer() {
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
    
    class OppButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;
        
        public OppButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }
        
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            
            String status = (String) tableModel.getValueAt(row, 5);
            if (status.equals("Open") || status.equals("Closing Soon")) {
                button.setBackground(new Color(34, 197, 94)); // Green
            } else {
                button.setBackground(Color.GRAY); // Gray for closed
            }
            
            button.setForeground(Color.WHITE);
            isPushed = true;
            return button;
        }
        
        public Object getCellEditorValue() {
            if (isPushed) {
                int row = opportunitiesTable.getEditingRow();
                String position = (String) tableModel.getValueAt(row, 0);
                String status = (String) tableModel.getValueAt(row, 5);
                
                if (status.equals("Open") || status.equals("Closing Soon")) {
                    showApplicationDialog(position);
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "This opportunity is no longer accepting applications.",
                        "Application Closed", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
            isPushed = false;
            return label;
        }
        
        private void showApplicationDialog(String position) {
            JDialog dialog = new JDialog(YouthOpportunitiesFrame.this, "Apply for: " + position, true);
            dialog.setSize(500, 400);
            dialog.setLocationRelativeTo(YouthOpportunitiesFrame.this);
            
            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            JLabel titleLabel = new JLabel("Application Form");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            JPanel formPanel = new JPanel();
            formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
            
            JTextArea coverLetterArea = new JTextArea(5, 30);
            coverLetterArea.setBorder(BorderFactory.createTitledBorder("Cover Letter *"));
            coverLetterArea.setLineWrap(true);
            coverLetterArea.setWrapStyleWord(true);
            
            JTextField emailField = new JTextField();
            emailField.setBorder(BorderFactory.createTitledBorder("Contact Email *"));
            emailField.setMaximumSize(new Dimension(400, 40));
            
            JTextField phoneField = new JTextField();
            phoneField.setBorder(BorderFactory.createTitledBorder("Contact Phone"));
            phoneField.setMaximumSize(new Dimension(400, 40));
            
            JButton attachResumeButton = new JButton("📎 Attach Resume/CV");
            attachResumeButton.setMaximumSize(new Dimension(400, 40));
            
            JButton submitButton = new JButton("Submit Application");
            submitButton.setBackground(new Color(34, 197, 94));
            submitButton.setForeground(Color.WHITE);
            submitButton.setFont(new Font("Arial", Font.BOLD, 14));
            submitButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(dialog, 
                    "Application submitted successfully!\nYou will be notified via email.",
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            });
            
            formPanel.add(coverLetterArea);
            formPanel.add(Box.createVerticalStrut(10));
            formPanel.add(emailField);
            formPanel.add(Box.createVerticalStrut(10));
            formPanel.add(phoneField);
            formPanel.add(Box.createVerticalStrut(10));
            formPanel.add(attachResumeButton);
            formPanel.add(Box.createVerticalStrut(20));
            formPanel.add(submitButton);
            
            panel.add(titleLabel, BorderLayout.NORTH);
            panel.add(formPanel, BorderLayout.CENTER);
            
            dialog.add(panel);
            dialog.setVisible(true);
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
            
            if (item[1].equals("Opportunities")) {
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