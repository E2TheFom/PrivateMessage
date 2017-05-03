
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Edward Fominykh
//Program Description
//Feb 8, 2017
public class ProfileBox extends JPanel
{
    private ImageIcon profilePhoto;
    private String name;
    public String description;
    public ChatBox chats;
    public boolean b; 
    private boolean isSelected= true;
    private boolean connected = false;
    private boolean connectionDropped = false;
    public ChatBox chatBox;
    public MessagingHome home;
    private JPanel photoPanel;
    private JPanel nameDescription;
    private JPanel nameArea;
    private ChatServer server;
    private ChatClient client;
    private JPanel textingPanel;
    private JLabel nameLabel;
    private ConnectStat connectDot;
    private JPanel sNameDescription;
    public int index;
    public Thread t;

    @Override
	public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(connected)
        	g2.setColor(Color.GREEN);
        else if(!connected && connectionDropped)
        	g2.setColor(Color.RED);
        else
        	g2.setColor(Color.ORANGE);
        g2.drawOval(getHeight()/2-5, getWidth()-15, 10, 10);
        System.out.println("Oval has been drawn");
        
        
    }

    public ProfileBox(String photoDir, String name, String description, boolean b, JPanel textingPanel, MessagingHome home, ChatClient client, int index)
    {
        this.home = home;
    	this.b = b;
        this.name = name;
        this.description = description;
        this.nameLabel = new JLabel();
        this.index = index;
        connectDot = new ConnectStat(this);
        client.setProfileBox(this);
        
       
//        this.setSize(new Dimension(280, 80));
        
        
        this.textingPanel = textingPanel;
        
        URL resource = getClass().getResource("default.png");
        chatBox = new ChatBox(this, home);
//        chatBox.setMaximumSize(new Dimension(0, home.getTextingPanel().getHeight()-home.getTextBox().getHeight()));
        chatBox.setVisible(true);
        textingPanel.add(chatBox, BorderLayout.CENTER);
        System.out.println("Added Chatbox");
        
        this.setBackground(new Color(38, 42, 48));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
        this.setPreferredSize(new Dimension(100,0));

//          nameLabel = new JLabel(client.getDisplayName());
//          JOptionPane.showMessageDialog(null, client.getDisplayName());
          System.out.println("The name is " + client.getDisplayName());
//        nameLabel.setName("1234");;
        
//        BufferedImage img = null;
//        try
//        {
//            Image img = ImageIO.read(resource);
//        } catch (IOException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
//        BufferedImage dimg = img.getScaledInstance(profilePhotoLabel.WIDTH, profilePhotoLabel.HEIGHT, Image.SCALE_SMOOTH);
//        BufferedImage dimg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        
        this.photoPanel = new JPanel();
        if(photoDir.isEmpty() == false)
        this.profilePhoto =  new ImageIcon(new ImageIcon(photoDir).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        else 
        this.profilePhoto =  new ImageIcon(new ImageIcon(resource).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        this.nameDescription = new JPanel();
        nameDescription.setLayout(new BorderLayout());
//        connectDot.setBounds(getWidth()-10, getHeight()-10, 10, 10);
//        JPanel nd = new JPanel();
//        nd.setPreferredSize(new Dimension(20, 0));
//        nd.setBackground(Color.red);
//        nameDescription.add(nd, BorderLayout.EAST);
//        nd.setLayout(new BorderLayout());
//        connectDot.setPreferredSize(new Dimension(20,20));
//        nd.setBackground(Color.red);
//        nd.add(connectDot, BorderLayout.NORTH);
        this.nameArea = new JPanel();
//        nameDescription.setOpaque(false);
        repaint();
//        this.photoPanel = photoPanel;
        
        
        
        this.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e)
            {
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
            	selectThis();
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

//            @Override
//            public void focusGained(FocusEvent e)
//            {
//                textingPanel.add(chatBox, BorderLayout.CENTER);
//                chatBox.setVisible(true);
//                isSelected = true;
//                System.out.println("ProfileBox selected");
//            }

//            @Override
//            public void focusLost(FocusEvent e)
//            {
//                isSelected = false;
//                chatBox.setVisible(false);
//            }
            
//        });
        
        nameDescription.setLayout(new BorderLayout());
        nameArea.setBorder(BorderFactory.createMatteBorder(0,0, 1, 0, Color.gray));
        while(client.getDisplayName().isEmpty())
        {
        	
        }
//        nameArea.add(nameLabel);
        System.out.println("Name Label: " + nameLabel);
        t = client;
        t.start();
        this.client = client;
        nameDescription.add(nameArea, BorderLayout.NORTH);
        
        connectDot = new ConnectStat(this);
        
//      nameDescription.setBackground(Color.red);
      System.out.println("NameDescription Size: "+ nameDescription.getSize());
      JPanel nd = new JPanel();
      nd.setPreferredSize(new Dimension(30, 0));
      
      nameDescription.add(nd, BorderLayout.EAST);
//      nd.setBackground(Color.blue);
      nd.setLayout(new BorderLayout());
      connectDot.setPreferredSize(new Dimension(30,30));
      nd.add(connectDot, BorderLayout.NORTH);
      this.sNameDescription = nd;
        
        nameLabel.setFont(JMessaging.getOpenSans3());
//        nameArea.setBackground(Color.red);
        
        
        JLabel profilePhotoLabel = new JLabel(profilePhoto);

        //        profilePhotoLabel.setPreferredSize(photoPanel.getSize());
        
        photoPanel.setLayout(new BorderLayout());       
        photoPanel.add(profilePhotoLabel, BorderLayout.NORTH);
        
        this.setLayout(new BorderLayout());
//        photoPanel.setBackground(Color.BLUE);
   //     nameDescription.setBackground(Color.red);
        
        photoPanel.setPreferredSize(new Dimension(100,0));
               
       
        
        this.add(photoPanel, BorderLayout.WEST);       
        this.add(nameDescription, BorderLayout.CENTER);
        
        
    }
    
    public JLabel getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(JLabel newNameLabel) {
		
	    this.nameArea.remove(getNameLabel());
	    this.nameLabel = newNameLabel;
	    nameLabel.setFont(JMessaging.getOpenSans3());
	    nameLabel.setForeground(Color.white);
		this.nameArea.add(nameLabel);
		System.out.println("Set name label to "+ nameLabel);
		this.revalidate();
		//System.out.println("New name label is: " + nameLabel);
		
	}

	public ProfileBox(String photoDir, String name, String description, boolean b, JPanel textingPanel, MessagingHome home, ChatServer server,int index)
    {
        this.home = home;
    	this.b = b;
        this.name = name;
        this.description = description;
        this.index = index;
        
//        this.setSize(new Dimension(280, 80));
        
        URL resource = getClass().getResource("default.png");
        
        t = server;
        t.start();
        this.server = server;
        
        server.setProfileBox(this);
        
        chatBox = new ChatBox(this, home);
        chatBox.setVisible(true);
        textingPanel.add(chatBox, BorderLayout.CENTER);
        this.chatBox = chatBox;
        System.out.println("Added Chatbox");
        
        this.setBackground(new Color(38, 42, 48));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
            
        JLabel nameLabel = new JLabel(name);
        
//        BufferedImage img = null;
//        try
//        {
//            Image img = ImageIO.read(resource);
//        } catch (IOException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
//        BufferedImage dimg = img.getScaledInstance(profilePhotoLabel.WIDTH, profilePhotoLabel.HEIGHT, Image.SCALE_SMOOTH);
//        BufferedImage dimg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        
        this.photoPanel = new JPanel();
        if(photoDir.isEmpty() == false)
        this.profilePhoto =  new ImageIcon(new ImageIcon(photoDir).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        else 
        this.profilePhoto =  new ImageIcon(new ImageIcon(resource).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        this.nameDescription = new JPanel();
        
        
        
        this.nameArea = new JPanel();
        this.photoPanel = photoPanel;
        
        
        
        this.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e)
            {
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                selectThis();
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

//            @Override
//            public void focusGained(FocusEvent e)
//            {
//                textingPanel.add(chatBox, BorderLayout.CENTER);
//                chatBox.setVisible(true);
//                isSelected = true;
//                System.out.println("ProfileBox selected");
//            }

//            @Override
//            public void focusLost(FocusEvent e)
//            {
//                isSelected = false;
//                chatBox.setVisible(false);
//            }
            
//        });
        
        nameDescription.setLayout(new BorderLayout());
        nameArea.setBorder(BorderFactory.createMatteBorder(0,0, 1, 0, Color.gray));
        nameArea.add(nameLabel);
        
        nameDescription.add(nameArea, BorderLayout.NORTH);
        connectDot = new ConnectStat(this);
        
//        nameDescription.setBackground(Color.red);
        System.out.println("NameDescription Size: "+ nameDescription.getSize());
        JPanel nd = new JPanel();
        nd.setPreferredSize(new Dimension(30, 0));
        
        nameDescription.add(nd, BorderLayout.EAST);
//        nd.setBackground(Color.blue);
        nd.setLayout(new BorderLayout());
        connectDot.setPreferredSize(new Dimension(30,30));
        nd.add(connectDot, BorderLayout.NORTH);
        this.sNameDescription = nd;
        nameLabel.setFont(JMessaging.getOpenSans3());
        
        
//        nameDescription.add(nameLabel);
        
        JLabel profilePhotoLabel = new JLabel(profilePhoto);

        //        profilePhotoLabel.setPreferredSize(photoPanel.getSize());
        
        photoPanel.setLayout(new BorderLayout());       
        photoPanel.add(profilePhotoLabel, BorderLayout.NORTH);
        
        this.setLayout(new BorderLayout());
//        photoPanel.setBackground(Color.BLUE);
   //     nameDescription.setBackground(Color.red);
        
        photoPanel.setPreferredSize(new Dimension(100,0));
        
       
       
        
        this.add(photoPanel, BorderLayout.WEST);       
        this.add(nameDescription, BorderLayout.CENTER);
        this.nameLabel = nameLabel;
        this.textingPanel = textingPanel;
    }
    
        
    
    public void selectThis()
    {
    	home.unSelectAllProfileBoxes(home.getArrayList());
        textingPanel.add(chatBox, BorderLayout.CENTER);
        chatBox.setVisible(true);
        isSelected = true;
        System.out.println("ProfileBox selected");
        System.out.println("isSelected: " + isSelected);
        home.changeSelectedBox();
        
        System.out.println("isSelected: " + isSelected);
    }
    
    public ConnectStat getConnectDot()
    {
        return connectDot;
    }

    public void setConnectDot(ConnectStat connectDot)
    {
        this.connectDot = connectDot;
    }

    public JPanel getsNameDescription()
    {
        return sNameDescription;
    }

    public void setsNameDescription(JPanel sNameDescription)
    {
        this.sNameDescription = sNameDescription;
    }

    public JPanel getPhotoPanel()
    {
        return photoPanel;
    }

    public ChatServer getServer() {
		return server;
	}

	public void setServer(ChatServer server) {
		this.server = server;
	}

	public ChatClient getClient() {
		return client;
	}

	public void setClient(ChatClient client) {
		this.client = client;
	}

	public void setPhotoPanel(JPanel photoPanel)
    {
        this.photoPanel = photoPanel;
    }



    public JPanel getNameDescription()
    {
        return nameDescription;
    }



    public void setNameDescription(JPanel nameDescription)
    {
        this.nameDescription = nameDescription;
    }



    public JPanel getNameArea()
    {
        return nameArea;
    }



    public void setNameArea(JPanel nameArea)
    {
        this.nameArea = nameArea;
    }



    @Override
	public String toString()
    {
        return name;
    }
    
    public ChatBox getChatBox()
    {
        return chatBox;
    }
    
    public Boolean isServer()
    {
    	return b;
    }

    public ImageIcon getProfilePhoto()
    {
        return profilePhoto;
    }

    public void setProfilePhoto(ImageIcon profilePhoto)
    {
        this.profilePhoto = profilePhoto;
    }

    public boolean isSelected()
    {
        return isSelected;
    }
    
    public void setSelected(boolean b)
    {
    	isSelected = b;
    }
    
    @Override
	public String getName()
    {
        return name;
    }

    @Override
	public void setName(String name)
    {
        this.name = name;
    }
    
    public void setAsSelected()
    {
    	 home.unSelectAllProfileBoxes(home.getArrayList());
         textingPanel.add(chatBox, BorderLayout.CENTER);
         chatBox.setVisible(true);
         isSelected = true;
         System.out.println("ProfileBox selected");
         System.out.println("isSelected: " + isSelected);
         home.changeSelectedBox();
 //        isSelected = false;
         System.out.println("isSelected: " + isSelected);
    }
    
    public void NotificationMessage(String message)
    {
    	if (!home.f.isActive())
    	{
    	    
    		new NotificationWindow(getName(), message);
    		System.out.println(getName() + " ---##--- "+message);
    	}
    }
    
    public void setDotColor(byte i)
    {
        connectDot.status(i);
    }
    
    public void terminate()
    {
        server = null;
        client = null;
        chatBox = null;
    }
    
    
}
