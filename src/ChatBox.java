
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

//Edward Fominykh
//Program Description
//Feb 3, 2017
public class ChatBox extends JPanel
{
    private Graphics2D g2;
    private MessageBubble bubble;
    private ArrayList<MessageBubble> messages;
    private MessagingHome home;
    private int increase;
    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
    private boolean goUp= false;
    private ProfileBox profileBox;
    private JPanel subPanel;
    private JPanel backPanel;
    private JScrollPane scrollPanel;
    private boolean includeName =true ;
    public String lastNameEntered = "";
    
    @Override
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g2 = (Graphics2D) g;
//        bubble.drawMessageBubble();
        g2.setColor(Color.green);
        g2.setRenderingHints(hints);
//        g2.fillRect(0, 0, 400, 500);;
//        new MessageBubble("HEllo", false, 15, "r", g2, this).drawMessageBubble();
        
        
        
//        for(MessageBubble bubble: messages){
//        	if (!(bubble.increase > (getHeight()-10)+bubble.increase)){
//            if (goUp){
//                bubble.increase -= 50;            
//            }
//            if (bubble.getSide().equals("r")){
//            g2.setColor(new Color(163, 203, 202));
////            g2.drawOval(getWidth()/2, getHeight()/2, 200, 200);
//            
//            g2.fillRoundRect(getWidth()-410, (getHeight()-10)+bubble.increase, 400, 40, 10, 10);
//            g2.setColor(Color.black);
//            g2.setFont(JMessaging.getOpenSans4());
//            g2.drawString(bubble.getMessage(), getWidth()-400, (getHeight()+17)+bubble.increase);
//            }
//            else{
//                g2.setColor(new Color(163, 203, 202));
////              g2.drawOval(getWidth()/2, getHeight()/2, 200, 200);
//              
//              g2.fillRoundRect(10, (getHeight()-10)+bubble.increase, 400, 40, 10, 10);
//              g2.setColor(Color.black);
//              g2.setFont(JMessaging.getOpenSans4());
//              g2.drawString(bubble.getMessage(), 20, (getHeight()+17)+bubble.increase);
//               
//            }
//        }
//        }
        goUp = false;
        
    }
    
    public ChatBox(ProfileBox profileBox, MessagingHome home)
    {
    	this.profileBox = profileBox;
        this.setBackground(new Color(133, 136, 140));
        this.setLayout(new BorderLayout());
        this.home = home;
        messages= new ArrayList();
        
        subPanel = new JPanel();
        backPanel = new JPanel();
        backPanel.setLayout(new BorderLayout());
        backPanel.add(subPanel, BorderLayout.SOUTH);
        subPanel.setBorder(null);
        subPanel.setBackground(new Color(40, 40, 40));
        backPanel.setBackground(new Color(133, 136, 140));
        backPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(40, 40, 40)));
//        subPanel.setMaximumSize(new Dimension(709, 0));
        subPanel.setLayout(new GridLayout(0,1));
//        subPanel.setMaximumSize(new Dimension(0, this.getHeight()));
        scrollPanel = new JScrollPane(backPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setAlignmentX(LEFT_ALIGNMENT);
        scrollPanel.setBackground(new Color(40, 40, 40));
        scrollPanel.getViewport().setViewPosition(new Point(0,getHeight()-1));
        scrollPanel.setBorder(null);
        
        this.add(scrollPanel, BorderLayout.CENTER);
        
//        scrollPanel.add(subPanel);
    }
    
    
    
    public void newMessage(String message, String sentOrRecieved){
//        message += " ";
//        if(!(message.substring(':').isEmpty() || message.substring(':').equals(" ")) ){
//        message.length() == message.indexOf(':')-1
        if(!(message.length()-1 == message.indexOf(':'))){
        subPanel.setEnabled(false);
        goUp = true;
        System.out.println("Sub Panel Size: " + subPanel.getSize());
        System.out.println("Chatbox Size:   " + this.getSize());
        System.out.println("ScrollPanel Size: "+ scrollPanel.getSize());
        this.increase = 15;
        String side;
        if (sentOrRecieved.equals("sent"))
            side = "r";
        else side = "l";
//        bubble.drawMessageBubble();
//        newMessage.panel().setBounds(0, 0, getWidth(), 100);
        MessageBubble newMessage = new MessageBubble(message, false, increase, side, g2, this, JMessaging.getOpenSans3());
        System.out.println("Name = " + home.getIp());
        JPanel msgPanel = newMessage.panel(this);
        msgPanel.setPreferredSize(new Dimension(0, 100));
        subPanel.add(msgPanel);
        
        scrollPanel.getViewport().setViewPosition(new Point(0,getHeight()+10000000));
//        for(MessageBubble bubble:messages)
//        {
//            bubble.increase -= 100;
//        }
//        this.setVisible(true);
//        this.revalidate();
//        home.setVisible(true);
        messages.add(newMessage);
        System.out.println("Added to a chatbox called: "+profileBox);
//        for(MessageBubble bubble: messages)
//        {
//            this.add(bubble.panel());
//        }
//        for(MessageBubble bubble: messages)
//        this.increase =+ 15;
        

        
        if (side.equals("l"))
        {
        	profileBox.NotificationMessage(message);
        }
        this.setEnabled(true);
        
        }
//        if (sentOrRecieved.equals("sent"))
//            includeName = true;
//        else includeName = false;
//        includeName = !sentOrRecieved.equals("sent");
    }
}
