package views.components;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int cornerRadius = 20;
    private Color backgroundColor = Color.WHITE;
    private Color borderColor = new Color(229, 231, 235);
    private int borderWidth = 1;
    private boolean shadow = false;
    
    public RoundedPanel() {
        setOpaque(false);
    }
    
    public RoundedPanel(int radius) {
        this.cornerRadius = radius;
        setOpaque(false);
    }
    
    public RoundedPanel(int radius, Color bgColor) {
        this.cornerRadius = radius;
        this.backgroundColor = bgColor;
        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw shadow
        if (shadow) {
            g2.setColor(new Color(0, 0, 0, 20));
            g2.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, cornerRadius, cornerRadius);
        }
        
        // Draw background
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        
        // Draw border
        if (borderWidth > 0) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderWidth));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, cornerRadius, cornerRadius);
        }
        
        g2.dispose();
        super.paintComponent(g);
    }
    
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }
    
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }
    
    public void setShadow(boolean shadow) {
        this.shadow = shadow;
        repaint();
    }
}