package views.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ModernSidebar extends RoundedPanel {
    private List<SidebarItem> items = new ArrayList<>();
    private SidebarClickListener listener;
    private String title;
    private String subtitle;
    private Color activeColor = new Color(79, 70, 229);
    
    public ModernSidebar(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackgroundColor(new Color(30, 41, 59));
        setCornerRadius(0);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        initHeader();
    }
    
    private void initHeader() {
        RoundedPanel header = new RoundedPanel();
        header.setLayout(new BorderLayout());
        header.setBackgroundColor(new Color(15, 23, 42));
        header.setBorder(BorderFactory.createEmptyBorder(30, 25, 30, 25));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(148, 163, 184));
        
        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);
        
        JLabel logo = new JLabel("🏛️");
        logo.setFont(new Font("Arial", Font.PLAIN, 36));
        
        header.add(logo, BorderLayout.WEST);
        header.add(textPanel, BorderLayout.CENTER);
        
        add(header);
        add(Box.createVerticalStrut(20));
    }
    
    public void addItem(String icon, String text, String id, boolean active) {
        SidebarItem item = new SidebarItem(icon, text, id, active);
        items.add(item);
        add(item);
        add(Box.createVerticalStrut(5));
    }
    
    public void setSidebarClickListener(SidebarClickListener listener) {
        this.listener = listener;
        for (SidebarItem item : items) {
            item.addActionListener(e -> {
                if (this.listener != null) {
                    // Deactivate all items
                    for (SidebarItem i : items) {
                        i.setActive(false);
                    }
                    // Activate clicked item
                    item.setActive(true);
                    this.listener.onItemClick(item.getId());
                }
            });
        }
    }
    
    public void addFooter(JPanel footerPanel) {
        add(Box.createVerticalGlue());
        add(footerPanel);
    }
    
    public interface SidebarClickListener {
        void onItemClick(String itemId);
    }
    
    class SidebarItem extends JPanel {
        private String id;
        private boolean active;
        private JLabel iconLabel;
        private JLabel textLabel;
        
        public SidebarItem(String icon, String text, String id, boolean active) {
            this.id = id;
            this.active = active;
            
            setLayout(new BorderLayout(15, 0));
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
            setMaximumSize(new Dimension(280, 55));
            
            iconLabel = new JLabel(icon);
            iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
            
            textLabel = new JLabel(text);
            textLabel.setFont(new Font("Segoe UI", active ? Font.BOLD : Font.PLAIN, 14));
            
            JPanel textPanel = new JPanel(new BorderLayout());
            textPanel.setOpaque(false);
            textPanel.add(textLabel, BorderLayout.CENTER);
            
            add(iconLabel, BorderLayout.WEST);
            add(textPanel, BorderLayout.CENTER);
            
            updateAppearance();
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!active) {
                        setBackground(new Color(255, 255, 255, 15));
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!active) {
                        setBackground(new Color(255, 255, 255, 0));
                    }
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    setBackground(new Color(255, 255, 255, 25));
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    if (active) {
                        setBackground(new Color(255, 255, 255, 30));
                    } else {
                        setBackground(new Color(255, 255, 255, 0));
                    }
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    fireActionPerformed();
                }
            });
        }
        
        private void updateAppearance() {
            if (active) {
                setBackground(new Color(255, 255, 255, 30));
                textLabel.setForeground(Color.WHITE);
                iconLabel.setForeground(Color.WHITE);
                
                // Add active indicator
                JPanel indicator = new JPanel();
                indicator.setPreferredSize(new Dimension(4, 20));
                indicator.setBackground(activeColor);
                indicator.setOpaque(true);
                add(indicator, BorderLayout.EAST);
            } else {
                setBackground(new Color(255, 255, 255, 0));
                textLabel.setForeground(new Color(203, 213, 225));
                iconLabel.setForeground(new Color(203, 213, 225));
                // Remove indicator if present
                remove(indicator);
            }
            revalidate();
            repaint();
        }
        
        private JPanel indicator; // Add this field
        
        public void setActive(boolean active) {
            this.active = active;
            updateAppearance();
        }
        
        public String getId() {
            return id;
        }
        
        public void addActionListener(ActionListener listener) {
            // Store listener and fire when clicked
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    listener.actionPerformed(new ActionEvent(SidebarItem.this, 
                        ActionEvent.ACTION_PERFORMED, id));
                }
            });
        }
        
        private void fireActionPerformed() {
            // Fire action event for any listeners
            ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, id);
            for (MouseListener ml : getMouseListeners()) {
                if (ml instanceof MouseAdapter) {
                    // Mouse clicked event already handled
                }
            }
        }
    }
}