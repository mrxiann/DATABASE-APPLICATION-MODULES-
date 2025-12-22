package views.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModernButton extends JButton {
    private Color normalColor = new Color(79, 70, 229);
    private Color hoverColor = new Color(67, 56, 202);
    private Color pressColor = new Color(55, 48, 163);
    private Color textColor = Color.WHITE;
    private int cornerRadius = 15;
    private boolean elevated = true;
    
    public ModernButton(String text) {
        super(text);
        init();
    }
    
    public ModernButton(String text, Color bgColor) {
        super(text);
        this.normalColor = bgColor;
        this.hoverColor = bgColor.darker();
        this.pressColor = bgColor.darker().darker();
        init();
    }
    
    private void init() {
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setForeground(textColor);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                repaint();
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Color currentColor = normalColor;
        int yOffset = 0;
        
        if (getModel().isPressed()) {
            currentColor = pressColor;
            yOffset = 2; // Pressed down effect
        } else if (getModel().isRollover()) {
            currentColor = hoverColor;
        }
        
        // Draw shadow for elevated effect
        if (elevated && !getModel().isPressed()) {
            g2.setColor(new Color(0, 0, 0, 30));
            g2.fillRoundRect(2, 4, getWidth()-4, getHeight()-4, cornerRadius, cornerRadius);
        }
        
        // Draw button
        g2.setColor(currentColor);
        g2.fillRoundRect(0, yOffset, getWidth(), getHeight()-yOffset, cornerRadius, cornerRadius);
        
        // Draw text
        g2.setColor(getForeground());
        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();
        String text = getText();
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent() + yOffset;
        g2.drawString(text, x, y);
        
        g2.dispose();
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        // No border
    }
}