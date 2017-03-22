import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

//Edward Fominykh
//Program Description
//Jan 19, 2017
public class MessagingHome extends JPanel implements Serializable
{
    private URL resource = getClass().getResource("gradient-wallpaper-2.jpg");
    public static Image background;
    
    public String ip;
    public String port;
    public JFrame f;
    private JPanel p;
    private JPanel top;
    private JPanel textingPanel;
    private JPanel NameDisplay;
    private JPanel JoinOptions;
    private JPanel SidePanel;
    private EFPaint TextBox;
    private TextboxOptions TextBoxOptions;
    private JButton Join;
    private JButton Create;
    private JButton Settings;
    private JButton Mute;
    private int PREF_W;
    private int PREF_H;
    private ArrayList<ProfileBox> profileBoxes;
    private boolean serverSetupOpen = false;
    @Override
	protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background, 0, 0, getWidth(), getHeight(), this);
       // TextBoxOptions.setBounds(0, 0, TextBox.getWidth()/6, TextBox.getHeight());
        
        
//        p.setBounds(0, 0, getWidth()/3, getHeight());
//        m.setBounds(0, 0, p.getWidth(), 100);
//        m2.setBounds(0, 0, m.getWidth(), m.getHeight() );
     //   m.setBorder(blackline);
        
//      g2.drawString("Hello", 500, 200);
    }

//    public void paintSidebar()
//    {
//    }
    
    
    @Override
	public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
     }
    //UHL STARTS MESSING AROUND
    
    public void gui(String ip, String port, Point location, Dimension size)
    {
        PREF_W = size.width;
        PREF_H = size.height;
        f = new JFrame("PrivateMessage Home");
        p = new JPanel();//side
        new JPanel();
        new JPanel();
        top = new JPanel();
        NameDisplay= new JPanel();
//        Chatbox = new ChatBox();
        JoinOptions = new JPanel();
        SidePanel = new JPanel();
        Join = new JButton("Join");
        Create = new JButton("Host");
        Settings = new JButton("Settings");
        Mute = new JButton("Mute");
        textingPanel = new JPanel();
        profileBoxes = new ArrayList();

        
        
        TextBox = new EFPaint(JMessaging.getOpenSans3());
        TextBoxOptions = new TextboxOptions();
        
        JMessaging.setTaskBarImage(f);
        
        
        Join.setBackground(Color.white);
        Create.setBackground(Color.white);
//        TrayIcon g = new TrayIcon(trayIcon);
//        SystemTray tray = SystemTray.getSystemTray();
//        
//        try {
//			tray.add(g);
//		} catch (AWTException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
        
        
        try
        {
            background = ImageIO.read(resource);
        } catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //p.paintComponents(paint);
        //Graphics2D paint2 = (Graphics2D) paint;
//        paint2.setColor(Color.white);
        //paint2.fillRect(0, 0, getWidth(), 20);
        //paint2.setColor(Color.GRAY);
        //paint2.setStroke(new BasicStroke(3));
        //paint2.drawLine(0, 20, getWidth(), 20);
        
        this.setLayout(new BorderLayout());
        
     // ScrollPanel.setViewportView( table );
        
     //   p.setPreferredSize(new Dimension(300, 0));
        
        Settings.addActionListener(new ActionListener()
        		{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						FileOutputStream fout = null;
						try {
							fout = new FileOutputStream("MessagingHome.privmsg");
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ObjectOutputStream oos = null;
						try {
							oos = new ObjectOutputStream(fout);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							oos.writeObject(getThis());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Saved current state");
					}
        	
        		});
        
        Join.addActionListener(new ActionListener()
        		{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(!serverSetupOpen){
						new ClientSetup(getThis());
						 serverSetupOpen = true;  
						}
					}
        	
        		});
        
        Create.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
               
                if(!serverSetupOpen){
                  new ServerSetup(getThis(), port);
                  serverSetupOpen = true;  
                }
                  
                  
            }  
        });
        
        BorderFactory.createMatteBorder(0,0, 1, 0, Color.gray);
        BorderFactory.createMatteBorder(0,0, 0, 1, Color.gray);
        BorderFactory.createMatteBorder(1,0, 0, 0, Color.gray);
        
        JoinOptions.setPreferredSize(new Dimension(0, 40));
        JoinOptions.setLayout(new GridLayout(0,2));
        JoinOptions.setBackground(new Color(95, 97, 102));
        JoinOptions.setBorder(BorderFactory.createMatteBorder(0, 0,1, 0, new Color(40, 40, 40)));
        
        SidePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(40, 40, 40)));
        SidePanel.setBackground(new Color(59, 91, 137));
        p.setBackground(new Color(95, 97, 102));
        Join.setOpaque(false);
        Create.setOpaque(false);
        
        JoinOptions.add(Join);
        JoinOptions.add(Create);
        
        
        
        SidePanel.setLayout(new BorderLayout());
        
//        m.setLayout(new GridLayout(0, 2));
//        m.setPreferredSize(new Dimension(0, 80));
//        m.setBorder(blackline);
//        
//        JPanel m1 = new JPanel();//individual
//        m1.setLayout(new GridLayout(0, 2));
//        m1.setBorder(blackline);
//        m1.setPreferredSize(new Dimension(0, 80));
//
//        JPanel m2 = new JPanel();//individual
//        m2.setLayout(new GridLayout(0, 2));
//        m2.setBorder(blackline);
//        m2.setPreferredSize(new Dimension(0, 80));

//        m.setBounds(0, 0, p.getWidth(), 100);
        f.setLocation(location);
      //  p.setLayout(new GridLayout(5, 1));
//        m2.setBounds(0, 0, m.getWidth()/3, m.getHeight() );
//        m.setLayout(null);
        
        JLabel IPLabel = new JLabel(ip);
        
        IPLabel.setFont(JMessaging.getOpenSans3());
        IPLabel.setForeground(Color.WHITE);
        NameDisplay.add(IPLabel);
        NameDisplay.setBackground(Color.BLACK);
        
        top.setLayout(new BorderLayout());
        top.setBackground(Color.black);
        top.add(NameDisplay, BorderLayout.WEST);
        //m.add(m2);
//        m2.setBackground(Color.blue);
//        p.setBackground(Color.white);
//        p.setBackground(new Color(181, 199, 203));
        
//        p.setBorder(blackline2);
//        p.add(m);       
//        p.add(m1);
//        p.add(m2);
//        JPanel[] panelList = new JPanel[7];
        p.setPreferredSize(new Dimension(0, profileBoxes.size()*100));
        if (profileBoxes.size()>7)
        p.setLayout(new GridLayout(profileBoxes.size(), 1));
        else p.setLayout(new GridLayout(7,1));
        
        JScrollPane scrollPane = new JScrollPane(p,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);
        scrollPane.setBorder(null);

//        scrollPane.add(p);
        
//        ScrollPanel.add(m);       
//        ScrollPanel.add(m1);
//        ScrollPanel.add(m2);
        
        
        TextBoxOptions.setBorder(BorderFactory.createMatteBorder(0,0, 0, 1, new Color(40, 40, 40)));
        TextBoxOptions.setBorder(BorderFactory.createMatteBorder(0,3, 0, 1, new Color(75, 72, 80)));

        TextBoxOptions.setBackground(new Color(75, 72, 80));
        TextBoxOptions.setLayout(new GridLayout(2,0));
        TextBoxOptions.setPreferredSize(new Dimension(100, 0));

//        try
//        {
//            Settings.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("rsz_settingsicon.bmp"))));
//        } catch (IOException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
        Settings.setBackground(Color.white);
        Settings.setOpaque(false);
        Mute.setBackground(Color.white);
        Mute.setOpaque(false);
        
       
        TextBoxOptions.add(Settings);
        TextBoxOptions.add(Mute);
        
        TextBox.setBackground(new Color(75, 72, 80));
        TextBox.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(40, 40, 40)));
        TextBox.setLayout(new BorderLayout());
        TextBox.add(TextBoxOptions, BorderLayout.WEST);
        TextBox.getTextField().addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e)
            {
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                String message = getIp()+":"+TextBox.getTextField().getText();
                
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(getSelectedProfilebox(profileBoxes).isServer()){
                    	System.out.println(getSelectedProfilebox(profileBoxes));
                    getSelectedProfilebox(profileBoxes).getServer().sendString(message);
                    
                    }
                    else
                    {
                    	System.out.println(getSelectedProfilebox(profileBoxes));
                    getSelectedProfilebox(profileBoxes).getClient().sendString(message);
                    }
                    getSelectedChatbox(profileBoxes).newMessage(message, "sent");
                    
                    TextBox.getTextField().setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
            }
            
        });
        
        Timer sizeCheck = new Timer(10, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				  p.setPreferredSize(new Dimension(0, profileBoxes.size()*100));
			        if (profileBoxes.size()>7)
			        p.setLayout(new GridLayout(profileBoxes.size(), 1));
			        else p.setLayout(new GridLayout(7,1));
			}
        	
        });
        
        sizeCheck.start();
        
//        TextBox.setPreferredSize(new Dimension(0, 60));
//        TextBox.add(new EFPaint(), BorderLayout.);
        textingPanel.setLayout(new BorderLayout());
        textingPanel.setBackground(new Color(133, 136, 140));
//        textingPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.red));
//        Chatbox.setBackground(Color.white);
//        Chatbox.setLayout(new BorderLayout());
        textingPanel.add(TextBox, BorderLayout.SOUTH);
        
        SidePanel.setPreferredSize(new Dimension(280, 0));
        SidePanel.add(scrollPane, BorderLayout.CENTER);
        SidePanel.add(JoinOptions, BorderLayout.NORTH);
        
        this.add(SidePanel, BorderLayout.WEST);
        this.add(top, BorderLayout.NORTH);
        this.add(textingPanel, BorderLayout.CENTER);
    //    textingPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.red));
//        textingPanel.add(Chatbox, BorderLayout.CENTER);
        
        f.add(this);
        f.setMinimumSize(new Dimension(800, 600));
//        f.setSize(size);
        f.pack();
//        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
      
    }
    
    public JPanel getTextingPanel() {
		return textingPanel;
	}

	public EFPaint getTextBox() {
		return TextBox;
	}

	public void setTextBox(EFPaint textBox) {
		TextBox = textBox;
	}

	public void setTextingPanel(JPanel textingPanel) {
		this.textingPanel = textingPanel;
	}

	public ProfileBox getSelectedProfilebox(ArrayList<ProfileBox> array)
    {
        for(int i = 0; i < array.size(); i++)
        {
            if(array.get(i).isSelected())
            {
                return array.get(i);
            }
        }
        return null;
    }

    public ChatBox getSelectedChatbox(ArrayList<ProfileBox> array)
    {
        for(int i = 0; i < array.size(); i++)
        {
            if(array.get(i).isSelected())
            {
                return array.get(i).getChatBox();
            }
        }
        return null;
    }
    
    public void setServerSetupOpen(boolean x)
    {
    	serverSetupOpen = x;
    }
    
    public MessagingHome(String ip, String port, Point location, Dimension size)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.port = port;
        this.ip = ip;
        gui(ip, port, location, size);
//        getFakePanel().getHeight();
    }
   
    
    
//    public JPanel getFakePanel()
//    {
//        ProfileBox m1 = new ProfileBox("Edward.jpg", "Edward", "An awesomeguy");//individual
//       // m1.setLayout(new GridLayout(0, 2));
////        m1.setBorder(blackline);
////        m1.setPreferredSize(new Dimension(0,m1.getProfilePhoto().getIconHeight()));
////        m1.setPreferredSize(new Dimension(280, 80));
////        JLabel label = new JLabel("HELLO");
////        m1.add(label);
//        return m1;
//    }
    

//    public static void main(String[] args)
//    {
//        new MessagingHome("123", "123");
//    }
    
    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public void addNewChat(ClientSetup cs)
    {
    	try {
//            Double port1 = Double.parseDouble(port);
            
//            chat = new ChatClient(getThis(), cs.getPort(), cs.getName());
//            t = chat;
//            t.start();
    		
    		ProfileBox newBox = new ProfileBox(cs.getImageDirectory(), cs.getName(), "An awesome guy", false, textingPanel, getThis(), new ChatClient(getThis(), cs.getPort(), cs.getName(), cs.Password()));
            profileBoxes.add(newBox);
            newBox.selectThis();
            
            for(ProfileBox box:profileBoxes)
            {
                p.add(box);
                System.out.println("added");                 
            }
            p.setPreferredSize(new Dimension(0, profileBoxes.size()*100));
            if (profileBoxes.size()>7)
            p.setLayout(new GridLayout(profileBoxes.size(), 1));
            else p.setLayout(new GridLayout(7,1));
            try
          {
              Thread.sleep(15);
          } catch (InterruptedException e1)
          {
              // TODO Auto-generated catch block
              e1.printStackTrace();
          }
            
         }catch(IOException e1) {
            e1.printStackTrace();
         }
          f.pack();
          f.setVisible(true);
          changeSelectedBox();
//          System.out.println(cs.getImageDirectory());
//          timer.start();
    }
    
    public void addNewServer(ServerSetup ss)
    {
        
            
            try {
            	ProfileBox newBox = new ProfileBox(ss.getImageDirectory(), ss.getName(), "An awesome guy", true, textingPanel, getThis(), new ChatServer(getThis(), ss.getPort(), ss.getName(), ss.Password()));
              profileBoxes.add(newBox);
//              Double port1 = Double.parseDouble(port);
              System.out.println("Opening new server on " + ss.getPort());
//              server = new ChatServer(getThis(), ss.getPort());
              newBox.selectThis();
//              changeSelectedBox();
//              unSelectAllProfileBoxes(profileBoxes);
              
              for(ProfileBox box:profileBoxes)
              {
                  p.add(box);
                  System.out.println("added");                 
              }
              p.setPreferredSize(new Dimension(0, profileBoxes.size()*100));
              if (profileBoxes.size()>7)
              p.setLayout(new GridLayout(profileBoxes.size(), 1));
              else p.setLayout(new GridLayout(7,1));
              try
            {
                Thread.sleep(15);
            } catch (InterruptedException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
              
           }catch(IOException e1) {
              e1.printStackTrace();
           }
//            f.pack();
            f.setVisible(true);
            System.out.println(ss.getImageDirectory());
            
        
    }
    
    public ArrayList<ProfileBox> getArrayList()
    {
        return profileBoxes;
    }
    public static void main(String[] args)
    {
        
        new MessagingHome("Edward", "12345", new Point(0,0), new Dimension(1000, 800));
        
    }
    
    public MessagingHome getThis()
    {
        return this;
    }
    
//    public ChatBox getChatbox(){
//        return Chatbox;
//    }
    
    public void unSelectAllProfileBoxes(ArrayList<ProfileBox> array)
    {
    	for(int i = 0; i < array.size(); i++)
    	{
    		array.get(i).setSelected(false);
    		array.get(i).chatBox.setVisible(false);
    	}
    }

//    @Override
//    public void mouseClicked(MouseEvent e)
//    {
//        System.out.println(getSelectedProfilebox(profileBoxes));
//        for(ProfileBox p: profileBoxes)
//        {
//            p.setBackground(null);
//        }
//        getSelectedProfilebox(profileBoxes).setBackground(new Color(124, 165, 183));
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e)
//    {
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e)
//    {
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e)
//    {
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e)
//    {
//    }
    
    public void changeSelectedBox()
    {
        System.out.println("The selected Chatbox is: " + getSelectedProfilebox(profileBoxes));
     //   System.out.println(getSelectedProfilebox(profileBoxes));
        Color color2 = new Color(81, 84, 89);
        for(int i = 0; i < profileBoxes.size(); i++)
        {
            profileBoxes.get(i).setBackground(Color.white);
            if(profileBoxes.get(i).isSelected())
            System.out.println(profileBoxes.get(i) + " Is selected");
            if(!profileBoxes.get(i).isSelected())
            {
            	profileBoxes.get(i).chatBox.setVisible(false );
            	profileBoxes.get(i).setBackground(Color.white);
            	System.out.println(profileBoxes.get(i)+" Is not fucking selected");
            	profileBoxes.get(i).getNameArea().setBackground(color2);
            	profileBoxes.get(i).getPhotoPanel().setBackground(color2);
            	profileBoxes.get(i).getNameDescription().setBackground(color2);
            	profileBoxes.get(i).getsNameDescription().setBackground(color2);
            	profileBoxes.get(i).getConnectDot().setBackground(color2);

            	
            }
        }
        ProfileBox p2 = getSelectedProfilebox(profileBoxes);
        Color color1 = new Color(38, 42, 48);
        p2.chatBox.setVisible(true);
        p2.getNameArea().setBackground(color1);
        p2.getPhotoPanel().setBackground(color1);
        p2.getNameDescription().setBackground(color1);
        p2.getConnectDot().setBackground(color1);
        p2.getsNameDescription().setBackground(color1);
        p2.getNameLabel().setForeground(Color.white);
  //      p.set
        System.out.println(p);
        System.out.println("Changed Chatbox");
        
    }
    
    public static String length16(String c) {
        int length = c.length();
        int spacesToAdd = 16 - length;
        String newString = c;
        for(int i = 0; i < spacesToAdd; i++)
            newString += "+";
        return newString;
    }
    
}
