package views;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    private Color backgroundColor;
    private Color hoverColor;
    private Color pressedColor;
    private int cornerRadius;
    
    public RoundedButton(String text) {
        this(text, new Color(79, 70, 229));
    }
    
    public RoundedButton(String text, Color bgColor) {
        this(text, bgColor, 15);
    }
    
    public RoundedButton(String text, Color bgColor, int radius) {
        super(text);
        this.backgroundColor = bgColor;
        this.hoverColor = bgColor.brighter();
        this.pressedColor = bgColor.darker();
        this.cornerRadius = radius;
        
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Color color;
        if (getModel().isPressed()) {
            color = pressedColor;
        } else if (getModel().isRollover()) {
            color = hoverColor;
        } else {
            color = backgroundColor;
        }
        
        g2.setColor(color);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        
        // Draw text
        g2.setColor(getForeground());
        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();
        String text = getText();
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
        g2.drawString(text, x, y);
        
        g2.dispose();
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        // if add border in future
        // Graphics2D g2 = (Graphics2D) g.create(); 
        // g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // g2.setColor(backgroundColor.darker());
        // g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, cornerRadius, cornerRadius);
        // g2.dispose();
    }
}