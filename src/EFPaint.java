import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JTextField;

//Edward Fominykh
//Program Description
//Feb 2, 2017
class EFPaint extends JPanel {
private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
private JTextField textField;
public JTextField getTextField()
{
    return textField;
}
public void setTextField(JTextField textField)
{
    this.textField = textField;
}
private Font textFieldFont;
//    public Border border;
    
    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(hints);
        g2.setColor(Color.white);
        g2.fillRoundRect(125, getHeight()/5, Math.abs(150-(getWidth())), getHeight()-(getHeight()/5)*2, 10, 10); 
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.gray);
//        g2.drawRoundRect(125, getHeight()/5, Math.abs(150-(getWidth())), getHeight()-(getHeight()/5)*2, 10, 10); 
        textField.setBounds(75+55, getHeight()/5+3, Math.abs(150-(getWidth()-50))+40, (getHeight()-(getHeight()/5)*2)-6); 
        g2.drawRoundRect(125, getHeight()/5, Math.abs(150-(getWidth())), getHeight()-(getHeight()/5)*2, 10, 10); 
//        textField.setBounds(125, getHeight()/5, Math.abs(150-(getWidth())), getHeight()-(getHeight()/5)*2); 
        
    }
//    public EFPaint(Border border){
      public EFPaint(Font font){
//      this.border = border;
//      this.setBorder(border);
        this.textFieldFont = font;
        textField= new JTextField();
        textField.setBorder(null);
        textField.setOpaque(false);
        this.add(textField);
        textField.setFont(textFieldFont);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(0, 60));
    }
    
    
}