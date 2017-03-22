import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//Edward Fominykh
//Program Description
//Jan 18, 2017
public class JMessaging extends JPanel implements KeyListener
{
    private URL resource = getClass().getResource("gradient-wallpaper-2.jpg");
    public static Image background;
    private Color color1;
    private Color color2;
    private Color color3;
    private Color color4;
    private Color lineColor;
    private int transparency = 0;
    private int transparency2 = 0;
    private int time;
    private Font OpenSans;
    private Font OpenSans2;
    public static Font OpenSans3;
    private double a = 75;
    private RenderingHints hint = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
    private Timer animate;
    private JTextField tf;
    private JTextField tf2;
    private boolean animationOver = false;
    private boolean tfSelected = false;
    private boolean tf2Selected = false;
    private JButton jb;
    private Timer Continue;
    private JFrame f;

    public void gui()
    {
        try
        {
            background = ImageIO.read(resource);
        } catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        f = new JFrame("PrivateMessage");
        setTaskBarImage(f);
        tf = new JTextField();
        tf2 = new JTextField();
        jb = new JButton();
        this.add(tf);
        this.add(tf2);
        this.add(jb);
        jb.setVisible(false);
        tf.setVisible(false);
        tf2.setVisible(false);
        jb.setBorder(null);
        jb.setOpaque(false);
        jb.setBackground(new Color(0,0,0,0));
        jb.setContentAreaFilled(false);
        // JPanel p = new JPanel();
        f.add(this);
        f.pack();
        f.setSize(1280, 800);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setMinimumSize(new Dimension(496, 651));
        f.setVisible(true);
        this.setLayout(null);
        tf.setOpaque(false);
        tf2.setOpaque(false);
        this.add(tf);
        this.add(tf2);
        tf.setFont(OpenSans3);
        tf2.setFont(OpenSans3);
        tf2.addKeyListener(this);
        tf.addKeyListener(this);
        tf.setBorder(null);
        tf2.setBorder(null);
        tf.setBackground(color1);
        tf2.setBackground(color1);
        
        jb.getModel().addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				ButtonModel model = (ButtonModel) arg0.getSource();
				if(model.isPressed())
				{
					jb.setOpaque(false);
					jb.setBackground(new Color(0,0,0,0));
				}
			}
        	
        });
        
        tf.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                repaint();
            }
        });
        tf2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                repaint();
            }
        });
        tf.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                tfSelected = true;
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
        });
        tf2.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                tf2Selected = true;
                Continue = new Timer(5, new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if (tfSelected){
                        if (transparency2 != 255)
                        transparency2++;
                        jb.setVisible(true);
                        repaint();
                        }
                    }
                });
                Continue.start();
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
        });
        
        jb.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e)
            {
                new MessagingHome(tf.getText(), tf2.getText(), f.getLocation(), f.getSize());
                f.setEnabled(false);
                f.setVisible(false);
                f.dispose();
                
                
                
              //  JOptionPane.showMessageDialog(null, "This is only the intro!");
            }
            
        });
        // this.add(p, BorderLayout.WEST);
        animate = new Timer(5, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                time++;
                if (time > 200 && transparency != 255)
                    transparency = transparency + 1;
                if (time > 200 && a != 0.05 && time % 2 == 0)
                    a = a * 0.95;
                else if (a < 0.05)
                {
                    animate.stop();
                    animationOver = true;
                }
                repaint();
          //      System.out.println(time + ", " + transparency + ", " + a);
            }
        });
        animate.start();
        // Box optionBox = new Box(Box.createVerticalBox());
        //
        // optionBox.add(new JButton("Press"));
        // p.add(optionBox);
    }

    @Override
	protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(hint);
        g2.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        color1 = new Color(240, 240, 240, transparency);
        color2 = new Color(150, 150, 150, transparency);
        color3 = new Color(240, 240, 240, transparency2);
        color4 = new Color(194, 181, 198, transparency2);
        lineColor = new Color(190, 190, 190, transparency);
        g2.setColor(color1);
        g2.fillRoundRect(getWidth() / 2 - 150, (getHeight() / 2 - 150) + (int) a, 300, 300, 50, 50);
        g2.setColor(color3);
        g2.fillOval(getWidth() / 2 - 32, getHeight() / 2 + 180, 64, 64);
        g2.setColor(color4);
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(getWidth() / 2 - 24, getHeight()/2+212, getWidth() / 2 + 24, getHeight()/2+212);
        g2.drawLine(getWidth() / 2, getHeight()/2+195, getWidth() / 2 + 24, getHeight()/2+212);
        g2.drawLine(getWidth() / 2, getHeight()/2+229, getWidth() / 2 + 24, getHeight()/2+212);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(color2);
        g2.drawRoundRect(getWidth() / 2 - 150, (getHeight() / 2 - 150) + (int) a, 300, 300, 50, 50);
        g2.setColor(color1);
        g2.setFont(OpenSans);
        g2.drawString("Setup", getWidth() / 2 - 90, getHeight() / 2 - 180);
        g2.setFont(OpenSans2);
        g2.setColor(color2);
        g2.drawString("Name", getWidth() / 2 - 135, (getHeight() / 2 - 55) + (int) a);
        g2.drawString("Local Port", getWidth() / 2 - 135, (getHeight() / 2 + 45) + (int) a);
        g2.setColor(lineColor);
        g2.drawLine(getWidth() / 2 - 80, (getHeight() / 2 - 50) + (int) a, getWidth() / 2 + 110,
                (getHeight() / 2 - 150 + 100) + (int) a);
        g2.drawLine(getWidth() / 2 - 45, (getHeight() / 2 + 50) + (int) a, getWidth() / 2 + 110,
                (getHeight() / 2 - 150 + 200) + (int) a);
        if (animationOver)
        {
            tf.setVisible(true);
            tf2.setVisible(true);
        }
        tf.setBounds(getWidth() / 2 - 70, (getHeight() / 2 - 75) + (int) a, 150, 27);
        tf2.setBounds(getWidth() / 2 - 35, (getHeight() / 2 + 25) + (int) a, 115, 27);
        jb.setBounds(getWidth() / 2 - 32, getHeight() / 2 + 180, 64, 64);
    }

    public static Font getOpenSansBold(){
        Font x =
                null;
                try
                {
                    x = Font
                            .createFont(Font.TRUETYPE_FONT,
                                    ClassLoader.getSystemClassLoader().getResourceAsStream("OpenSans-Semibold.ttf"))
                            .deriveFont(15f);
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    // register the font
                    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                            ClassLoader.getSystemClassLoader().getResourceAsStream("OpenSans-Semibold.ttf")));
                } catch (FontFormatException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return x;
            }
    
    public static Font getOpenSans3(){
        Font x =
        null;
        try
        {
            x = Font
                    .createFont(Font.TRUETYPE_FONT,
                            ClassLoader.getSystemClassLoader().getResourceAsStream("OpenSans-Light.ttf"))
                    .deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    ClassLoader.getSystemClassLoader().getResourceAsStream("OpenSans-Light.ttf")));
        } catch (FontFormatException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return x;
    }
    
    public static Font getOpenSans5(){
        Font x =
        null;
        try
        {
            x = Font
                    .createFont(Font.TRUETYPE_FONT,
                            ClassLoader.getSystemClassLoader().getResourceAsStream("OpenSans-Light.ttf"))
                    .deriveFont(13f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    ClassLoader.getSystemClassLoader().getResourceAsStream("OpenSans-Light.ttf")));
        } catch (FontFormatException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return x;
    }
    
    public static Font getOpenSans4(){
        Font x =
        null;
        try
        {
            x = Font
                    .createFont(Font.TRUETYPE_FONT,
                            ClassLoader.getSystemClassLoader().getResourceAsStream("OpenSans-Light.ttf"))
                    .deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    ClassLoader.getSystemClassLoader().getResourceAsStream("OpenSans-Light.ttf")));
        } catch (FontFormatException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return x;
    }
    
    public JMessaging()
    {
        try
        {
            OpenSans = Font
                    .createFont(Font.TRUETYPE_FONT,
                            ClassLoader.getSystemClassLoader().getResourceAsStream("OpenSans-Light.ttf"))
                    .deriveFont(70f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    ClassLoader.getSystemClassLoader().getResourceAsStream("OpenSans-Light.ttf")));
        } catch (FontFormatException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try
        {
            OpenSans2 = Font
                    .createFont(Font.TRUETYPE_FONT,
                            ClassLoader.getSystemClassLoader().getResourceAsStream("OpenSans-Light.ttf"))
                    .deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    ClassLoader.getSystemClassLoader().getResourceAsStream("OpenSans-Light.ttf")));
        } catch (FontFormatException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try
        {
            OpenSans3 = Font
                    .createFont(Font.TRUETYPE_FONT,
                            ClassLoader.getSystemClassLoader().getResourceAsStream("OpenSans-Light.ttf"))
                    .deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    ClassLoader.getSystemClassLoader().getResourceAsStream("OpenSans-Light.ttf")));
        } catch (FontFormatException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        color1 = new Color(240, 240, 240, transparency);
        color2 = new Color(150, 150, 150, transparency);
        gui();
    }

    public static void main(String[] args)
    {
        new JMessaging();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
//        repaint();
//        System.out.println("Pressed");
        // System.out.println(getWidth() + ", " + getHeight());
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
//        repaint();
//        System.out.println("Released");
    }
    
    public static void setTaskBarImage(JFrame j)
    {
    	  Image trayIcon = null;
  		try {
  			trayIcon = ImageIO.read(MessagingHome.class.getResource("trayIcon.png"));
  		} catch (IOException e2) {
  			// TODO Auto-generated catch block
  			e2.printStackTrace();
  		}
  		j.setIconImage(trayIcon);
    }
    
}
