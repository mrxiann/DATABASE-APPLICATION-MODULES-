package views.components;

import javax.swing.*;
import java.awt.*;

public class StatCard extends RoundedPanel {
    private String title;
    private String value;
    private String icon;
    private Color gradientStart;
    private Color gradientEnd;
    
    public StatCard(String title, String value, String icon, Color start, Color end) {
        this.title = title;
        this.value = value;
        this.icon = icon;
        this.gradientStart = start;
        this.gradientEnd = end;
        
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        setBackgroundColor(start); // Temporary, will be painted with gradient
        
        initComponents();
    }
    
    private void initComponents() {
        // Top: Icon and title
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        iconLabel.setForeground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(new Color(255, 255, 255, 220));
        
        topPanel.add(iconLabel, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.EAST);
        
        // Center: Value
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        add(topPanel, BorderLayout.NORTH);
        add(valueLabel, BorderLayout.CENTER);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw gradient background
        GradientPaint gradient = new GradientPaint(
            0, 0, gradientStart,
            getWidth(), getHeight(), gradientEnd
        );
        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), getCornerRadius(), getCornerRadius());
        
        g2.dispose();
        super.paintComponent(g);
    }
}