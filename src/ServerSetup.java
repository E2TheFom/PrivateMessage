import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

//Edward Fominykh
//Program Description
//Feb 10, 2017
public class ServerSetup
{
    private JFrame frame;
    private JPanel backPanel;
    private JPanel mainPanel;
    private JPanel subPanel0;
    private SetupOptionPanel subPanel1;
    private JPanel subPanel2;
    private SetupOptionPanel subPanel3;
    private SetupOptionPanel subPanel4;
    private SetupOptionPanel subPanel5;
    private JPanel subPanel6;
    private JPanel subPanel7;
    private JPanel startServer;
    private JLabel makeServer;
    private JButton startServerButton;
    private JLabel password;
    private JPasswordField passwordText;
    private JCheckBox checkBox;
    private String directory = "";
    private MessagingHome home;
    private String port;
    
    
    
    public ServerSetup(MessagingHome home, String port)
    {
        Color efGray = new Color(181, 199, 203);
        Color efDarkGray = new Color(141, 159, 163);
        checkBox = new JCheckBox("", false);
        this.home = home;
        password = new JLabel("     Please set a password for this chat");
        password.setFont(JMessaging.getOpenSans3());
        passwordText = new JPasswordField();
        passwordText.setFont(JMessaging.getOpenSans3());
        passwordText.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, efGray));
        passwordText.setBackground(efDarkGray);
        passwordText.setEchoChar('*');
        
        startServerButton = new JButton("Start Server");
        startServerButton.setBackground(Color.white);
        startServerButton.addActionListener(new ActionListener(){

        	
        	
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	setPort(subPanel3.getTextField().getText());
                home.addNewServer(getThis());
                home.setServerSetupOpen(false);
                frame.dispose();
            }
            
        });
        frame = new JFrame("PrivateMessage New Server");
        frame.setResizable(false);
        

        frame.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				home.setServerSetupOpen(false);
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        
        backPanel = new JPanel();
        
        mainPanel = new JPanel();
        mainPanel.setBackground(efGray);
        
        mainPanel.setLayout(new GridLayout(5, 0));
        
        
        
        startServer= new JPanel();
        startServer.setBackground(efGray);
        startServerButton.setBackground(Color.white);
        startServer.setLayout(new GridLayout(0,3));
        startServer.add(new JLabel(""));
        startServer.add(new JLabel(""));
        startServer.add(startServerButton);
        
        subPanel0 = new JPanel();
        subPanel0.add(new JLabel(""));
        subPanel0.setBackground(Color.black);
        subPanel1= new SetupOptionPanel(efDarkGray, efGray, "Please enter a name for the server");
        subPanel2 = new JPanel();
        subPanel2.setLayout(null);
        subPanel2.add(password);
        subPanel2.add(passwordText);
        
        
        subPanel2.setBackground(efDarkGray);
        
        
        subPanel3 = new SetupOptionPanel(efDarkGray, efGray, "Please enter a port");
        
        subPanel3.getTextField().setText(port);
        subPanel4 = new SetupOptionPanel(efGray, efDarkGray, "Please select an Icon for the server (Optional)");
        subPanel4.removeTextField();
        subPanel4.addButton(new JButton("Select Image"));
        
        subPanel5 = new SetupOptionPanel(efDarkGray, efGray, "Whitelist");
        subPanel5.addCheckbox(checkBox, "Enable Whitelist (You will get to set it up later)");
        subPanel5.getCheckbox().setFont(JMessaging.OpenSans3);
        subPanel5.removeTextField();
        
        
        subPanel6 = new JPanel();
        subPanel6.setBackground(efGray);
        
        subPanel7 = new JPanel();
        subPanel7.setBackground(efGray);
        
        
        
        subPanel0.setLayout(new GridLayout(2,0));
        
        JLabel makeServer = new JLabel("        Host Server");
        makeServer.setFont(JMessaging.getOpenSans4());
        makeServer.setForeground(Color.white);
        subPanel0.add(makeServer);
        
        
        frame.pack();
        frame.setSize(350, 600);
        frame.setLocationRelativeTo(null);
        frame.add(backPanel);
        JMessaging.setTaskBarImage(frame);
        
        backPanel.setLayout(new BorderLayout());
        
        backPanel.add(subPanel0, BorderLayout.NORTH);
        backPanel.add(mainPanel, BorderLayout.CENTER);
        backPanel.add(startServer, BorderLayout.SOUTH);
        
        mainPanel.add(subPanel1);
        mainPanel.add(subPanel2);
        mainPanel.add(subPanel3);
        mainPanel.add(subPanel4);
        mainPanel.add(subPanel5);
//        mainPanel.add(subPanel6);
        
        subPanel4.getButton().addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
//                fileChooser.getSelectedFile().toString() = this.directory;
                setImageDirectory(fileChooser.getSelectedFile().toString());
            }
            
        });
        
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setVisible(true);
        passwordText.setBounds(20, subPanel2.getHeight()-50, 200, 30);
        subPanel4.getButton().setBackground(Color.white);
        subPanel3.setTextFieldBounds();
        subPanel5.setCheckboxBounds();
        subPanel1.setTextFieldBounds();
        subPanel4.setButtonBounds();
        subPanel1.setLabelBounds();
        password.setBounds(0, subPanel2.getHeight()-87, subPanel2.getWidth(), 30);
        subPanel3.setLabelBounds();
        subPanel4.setLabelBounds();
        subPanel5.setLabelBounds();
        
        // Parameters: 
        // Password
        // Server Icon
        // Port
        // Enable Whitelist - if yes, then make list
        
        
    }
    
    public ServerSetup getThis()
    {
        return this;
    }
    
    public String getName()
    {
        return subPanel1.getInput();
    }
    
    public String Password()
    {
        return new String(passwordText.getPassword());
        
    }
    
    public String getImageDirectory()
    {
        return directory;
    }
    
    public void setImageDirectory(String s)
    {
        this.directory = s;
    }
    
    public static void main(String[] args)
    {
        new MessagingHome("123", "12345", new Point(0,0), new Dimension(1000, 800));
    }
    
    public void setPort(String newPort)
    {
    	this.port = newPort;
    }
    
    public String getPort()
    {
        return port;
    }
}
