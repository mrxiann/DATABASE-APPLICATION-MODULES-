package views.components;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {
    private Color startColor = new Color(79, 70, 229);
    private Color endColor = new Color(129, 140, 248);
    private boolean vertical = true;
    
    public GradientPanel() {
        setOpaque(false);
    }
    
    public GradientPanel(Color start, Color end) {
        this.startColor = start;
        this.endColor = end;
        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        GradientPaint gradient;
        if (vertical) {
            gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
        } else {
            gradient = new GradientPaint(0, 0, startColor, getWidth(), 0, endColor);
        }
        
        g2.setPaint(gradient);
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        g2.dispose();
        super.paintComponent(g);
    }
    
    public void setGradient(Color start, Color end) {
        this.startColor = start;
        this.endColor = end;
        repaint();
    }
    
    public void setVertical(boolean vertical) {
        this.vertical = vertical;
        repaint();
    }
}