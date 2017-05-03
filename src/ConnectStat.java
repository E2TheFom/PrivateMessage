import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

//Edward Fominykh
//Program Description
//Mar 16, 2017
public class ConnectStat extends JPanel implements MouseListener
{
    private byte connectStatus = 0;
    private ProfileBox p;
    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
    private Timer fade;
    
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
        g2.setColor(Color.black);
        g2.drawLine(getWidth()/2-4, getHeight()/2-4, getWidth()/2+4, getHeight()/2+4);
        g2.drawLine(getWidth()/2+4, getHeight()/2-4, getWidth()/2-4, getHeight()/2+4);
//        g2.drawLine(getWidth()-getWidth()/2+4, getHeight()-getHeight()/2-4, getWidth()-getWidth()/2-4, getHeight()-getHeight()/2-4);
        
    }
    
    public ConnectStat(ProfileBox p)
    {
        this.p = p;
        this.addMouseListener(this);
        fade = new Timer(50, new ActionListener()
                {

                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        
                    }
                    
                    
                    
                });
    }
    
    public void status(byte i)
    {
        connectStatus = i;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        p.chatBox.setVisible(false);
        p.home.getArrayList().get(p.index).terminate();
        p.home.p.remove(p.home.getArrayList().get(p.index));
        p.home.getArrayList().get(p.index).revalidate();
        p.home.p.repaint();
        p.home.getArrayList().remove(p.index);
        if(p.home.getArrayList().size()>1)
        p.home.getArrayList().get(p.index-1).setAsSelected();
        System.out.println("Profile box at index " + p.index+" Removed");
        
        for(int i = 0; i < p.home.getArrayList().size(); i++)
        {
            p.home.getArrayList().get(i).index = i;
        }
        p.home.p.revalidate();
        
        p.home.scrollPane.revalidate();
        
            p.getServer().stopServer();;
        
        

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }
    
    
    
}
