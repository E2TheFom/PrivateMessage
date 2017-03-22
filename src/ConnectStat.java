import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

//Edward Fominykh
//Program Description
//Mar 16, 2017
public class ConnectStat extends JPanel
{
    private byte connectStatus = 0;
    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
    
    @Override
	protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(hints);
        if(connectStatus == 0)
            g2.setColor(Color.orange);
        else if(connectStatus == 1)
            g2.setColor(Color.green);
        else if(connectStatus == 2)
            g2.setColor(Color.red);
        g2.fillOval(6, 6, getWidth()-12, getHeight()-12);
        
    }
    
    public void status(byte i)
    {
        connectStatus = i;
        repaint();
    }
    
    
    
}
