import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.Timer;

//Edward Fominykh
//Program Description
//Feb 17, 2017
public class NotificationWindow
{
    JWindow window;
    JPanel messagePanel;
    JPanel title;
    JLabel nameLabel;
    JLabel messageLabel;
    JPanel mainMessagePanel;
    private int x;
    private int timeOpen = 0;
    private Timer timer;
    
    public NotificationWindow(String name, String message)
    {
        x = 450;
        timer= new Timer(20, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                x = (int) (x*0.9);
                window.setLocation(new Point((Toolkit.getDefaultToolkit().getScreenSize().width-410)+x-10, Toolkit.getDefaultToolkit().getScreenSize().height-200));
                if(x == 0)
                {
                    timeOpen++;
//                    System.out.println(timeOpen);
                    if(timeOpen==200)
                    {
                    window.dispose();
                    window = null;
                    stopTimer();
                    }
                }   
                
                
            }
            
        });
        
        window =new JWindow();
        
        window.setLocation(new Point((Toolkit.getDefaultToolkit().getScreenSize().width)+x, Toolkit.getDefaultToolkit().getScreenSize().height-400));
        window.setAlwaysOnTop(true);
        messagePanel = new JPanel();
        title = new JPanel();
        System.out.println("Message: "+ message);
        System.out.println("Notification name: " + message.substring(0, message.indexOf(':')));
        nameLabel = new JLabel("  "+name+" - "+message.substring(0, message.indexOf(':'))+":");
        messageLabel = new JLabel("   "+message.substring(message.indexOf(':')+1));
        messagePanel.setLayout(new BorderLayout());
        mainMessagePanel = new JPanel();
        title.setLayout(new BorderLayout());
        
        nameLabel.setFont(JMessaging.getOpenSans4());
        nameLabel.setForeground(Color.white);
        
        title.setBackground(Color.black);
        title.setPreferredSize(new Dimension(0, 40));
        
        title.add(nameLabel);
        messagePanel.add(title, BorderLayout.NORTH);
        messagePanel.add(mainMessagePanel, BorderLayout.CENTER);
        
        messageLabel.setFont(JMessaging.getOpenSans3());
//        messageLabel.setForeground(Color);
        messagePanel.add(messageLabel);
        window.add(messagePanel);
        
        window.setSize(400, 100);
//        window.setLocationRelativeTo(null);
        window.setVisible(true);
        timer.start();
    }
    
    public static void main(String[] args)
    {
        new NotificationWindow("eSports Clan", "Edward:Hey");
        System.out.println("Showed notification");
    }
    
    public void stopTimer(){
        timer.stop();
    }
    
}
