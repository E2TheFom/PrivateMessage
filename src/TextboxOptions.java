import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

//Edward Fominykh
//Program Description
//Feb 3, 2017
public class TextboxOptions extends JPanel
{
    @Override
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(Color.white);
    }
}
