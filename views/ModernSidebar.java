package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ModernSidebar extends JPanel {
    private List<SidebarItem> items = new ArrayList<>();
    private SidebarClickListener listener;
    
    public ModernSidebar(String title, Color bgColor, Color highlightColor) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(bgColor);
        setPreferredSize(new Dimension(280, 700));
        
        // Logo/Title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(bgColor);
        titlePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255, 255, 255, 50)),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        add(titlePanel);
        
        // Add some spacing
        add(Box.createVerticalStrut(10));
    }
    
    public void addItem(String icon, String text, String id, boolean isActive) {
        SidebarItem item = new SidebarItem(icon, text, id, isActive);
        items.add(item);
        add(item);
        add(Box.createVerticalStrut(5));
    }
    
    public void setSidebarClickListener(SidebarClickListener listener) {
        this.listener = listener;
        for (SidebarItem item : items) {
            item.addActionListener(e -> {
                if (this.listener != null) {
                    this.listener.onItemClick(item.getId());
                }
            });
        }
    }
    
    public interface SidebarClickListener {
        void onItemClick(String itemId);
    }
    
    // Inner class for sidebar items
    class SidebarItem extends JButton {
        private String id;
        private boolean active;
        
        public SidebarItem(String icon, String text, String id, boolean active) {
            super(" " + icon + "   " + text);
            this.id = id;
            this.active = active;
            
            setHorizontalAlignment(SwingConstants.LEFT);
            setFont(new Font("Segoe UI", active ? Font.BOLD : Font.PLAIN, 14));
            setForeground(Color.WHITE);
            setBackground(getBackground().darker());
            setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
            setFocusPainted(false);
            setContentAreaFilled(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setMaximumSize(new Dimension(280, 50));
            
            if (active) {
                setBackground(new Color(255, 255, 255, 30));
            }
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!active) {
                        setBackground(new Color(255, 255, 255, 20));
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!active) {
                        setBackground(getParent().getBackground());
                    }
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (active) {
                // Draw active background
                g2.setColor(new Color(255, 255, 255, 30));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                // Draw accent line
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, 5, getHeight(), 5, 5);
            }
            
            super.paintComponent(g2);
            g2.dispose();
        }
        
        public String getId() {
            return id;
        }
    }
}