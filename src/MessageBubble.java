import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

//Edward Fominykh
//Program Description
//Feb 9, 2017
public class MessageBubble
{
    private String message;
    private boolean isTransparent;
    public int increase;
    private String side;
    private Graphics2D g2;
    private JPanel panel;
    private JPanel bubblePanel;
    private Font font;
    private JPanel namePanel;
    
    public MessageBubble(String message, boolean isTransparent, int increase, String side, Graphics2D g2, JPanel panel, Font font)
    {
        super();
        this.message = message;
        this.isTransparent = isTransparent;
        this.increase = increase;
        this.side = side;
        this.g2 = g2;
        this.panel = panel;
        this.font = font;
    }

    public JPanel panel(ChatBox chatBox){
        bubblePanel = new JPanel();
        bubblePanel.setPreferredSize(new Dimension(0 , 100));
        Border border = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.white);
//        Border border2 = BorderFactory.createTitledBorder(border, message.substring(0, message.indexOf(':')));
        bubblePanel.setBorder(border);
        bubblePanel.setLayout(new BorderLayout());
        JLabel label = new JLabel("        "+message.substring(message.indexOf(':')+1));
        label.setFont(font);
        label.setForeground(Color.white);
        bubblePanel.setBackground(new Color(38, 42, 48));
        bubblePanel.add(label, BorderLayout.WEST);
        if(!message.substring(0, message.indexOf(':')).equals(chatBox.lastNameEntered))
        {
//            System.out.println("Was the lastname entered the same?: " + (message.substring(0, message.indexOf(':')) != chatBox.lastNameEntered));
            namePanel = new JPanel();
            
            namePanel.setLayout(new BorderLayout());
            namePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
            namePanel.setBackground(new Color(38, 42, 48));
            JLabel name = new JLabel("      " + message.substring(0, message.indexOf(':')));
            name.setForeground(Color.white);
            name.setFont(JMessaging.getOpenSans3());
            namePanel.add(name, BorderLayout.WEST);
            
            namePanel.setPreferredSize(new Dimension(chatBox.getWidth()/2, 20));
            bubblePanel.add(namePanel, BorderLayout.NORTH);
            chatBox.lastNameEntered = message.substring(0, message.indexOf(':'));
        }
        return bubblePanel;
        
    }
    
//    public static void main(String[] args){
//    	javax.swing.JFrame f = new javax.swing.JFrame();
//    	f.setVisible(true);
//    	f.add(new MessageBubble("Edward:Hi", false, increase, side, g2, this, JMessaging.getOpenSans3()));
//    	
//    }
    
    public void drawMessageBubble(){
        int x;
        if(side.equals("l"))
            x = 10;
        else
            x = panel.getWidth()/2;
        g2.fillRect(0, panel.getHeight()-50, 200, 40);
        
    }



    public String getMessage()
    {
        return message;
    }



    public void setMessage(String message)
    {
        this.message = message;
    }



    public boolean isTransparent()
    {
        return isTransparent;
    }



    public void setTransparent(boolean isTransparent)
    {
        this.isTransparent = isTransparent;
    }



    public String getSide()
    {
        return side;
    }



    public void setSide(String side)
    {
        this.side = side;
    }

    public static void p(String s)
    {
    	System.out.println(s);
    }

    public Graphics2D getG2()
    {
        return g2;
    }



    public void setG2(Graphics2D g2)
    {
        this.g2 = g2;
    }



    public JPanel getPanel()
    {
        return panel;
    }



    public void setPanel(JPanel panel)
    {
        this.panel = panel;
    }

}
