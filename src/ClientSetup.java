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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

public class ClientSetup
{
    private JFrame frame;
    private JPanel backPanel;
    private JPanel mainPanel;
    private JPanel subPanel0;
    private SetupOptionPanel subPanel1;
    private JPanel subPanel2;
    private SetupOptionPanel subPanel3;
//    private SetupOptionPanel ;
    private SetupOptionPanel subPanel5;
    private JPanel subPanel6;
    private JPanel subPanel7;
    private JPanel joinChat;
    private JLabel makeServer;
    private JButton joinChatButton;
    private JLabel password;
    private JPasswordField passwordText;
    private JCheckBox checkBox;
    private String directory = "";
    private MessagingHome home;
    private String port;
    
    
    
    
    public ClientSetup(MessagingHome home)
    {
        Color efGray = new Color(181, 199, 203);
        Color efDarkGray = new Color(141, 159, 163);
        checkBox = new JCheckBox("", false);
        this.home = home;
        password = new JLabel("     Please enter the password for this chat");
        password.setFont(JMessaging.getOpenSans3());
        passwordText = new JPasswordField();
        passwordText.setFont(JMessaging.getOpenSans3());
        passwordText.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, efDarkGray));
        passwordText.setBackground(efGray);
        passwordText.setEchoChar('*');
        
        joinChatButton = new JButton("Join Chat");
        joinChatButton.setBackground(Color.white);
        joinChatButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e)
            {
                home.addNewChat(getThis());
                home.setServerSetupOpen(false);
                frame.dispose();
            }
            
        });
        frame = new JFrame("PrivateMessage Join Chat");
        frame.setResizable(false);
        JMessaging.setTaskBarImage(frame);
        
        backPanel = new JPanel();
        
        mainPanel = new JPanel();
        mainPanel.setBackground(efGray);
        
        mainPanel.setLayout(new GridLayout(4, 0));
        mainPanel.setBackground(efDarkGray);
        
        
        joinChat= new JPanel();
        joinChat.setBackground(efDarkGray);
        joinChatButton.setBackground(Color.white);
        joinChat.setLayout(new GridLayout(0,3));
        joinChat.add(new JLabel(""));
        joinChat.add(new JLabel(""));
        joinChat.add(joinChatButton);
        
        subPanel0 = new JPanel();
        subPanel0.add(new JLabel(""));
        subPanel0.setBackground(Color.black);
        subPanel1= new SetupOptionPanel(efDarkGray, efGray, "Please enter the IP Address of the server");
        subPanel2 = new JPanel();
        subPanel2.setLayout(null);
        subPanel2.add(password);
        subPanel2.add(passwordText);
        
        
        subPanel2.setBackground(efGray);
        
        
        subPanel3 = new SetupOptionPanel(efGray, efDarkGray, "Please enter the port the server is using");
        subPanel3.getTextField().setText(port);
        this.port = subPanel3.getTextField().getText();
//         = new SetupOptionPanel(efGray, efDarkGray, "Please select an Icon for the server (Optional)");
//        .removeTextField();
//        .addButton(new JButton("Select Image"));
        
        subPanel5 = new SetupOptionPanel(efGray, efDarkGray, "Whitelist");
        subPanel5.addCheckbox(checkBox, "Download Server Icon");
        subPanel5.getCheckbox().setFont(JMessaging.OpenSans3);
        subPanel5.removeTextField();
        
        
        subPanel6 = new JPanel();
        subPanel6.setBackground(efGray);
        
        subPanel7 = new JPanel();
        subPanel7.setBackground(efGray);
        
        
        
        subPanel0.setLayout(new GridLayout(2,0));
        
        JLabel makeServer = new JLabel("        Join Chat");
        makeServer.setFont(JMessaging.getOpenSans4());
        makeServer.setForeground(Color.white);
        subPanel0.add(makeServer);
        
        
        frame.pack();
        frame.setSize(350, 480);
        frame.setLocationRelativeTo(null);
        frame.add(backPanel);
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
        
        backPanel.setLayout(new BorderLayout());
        
        backPanel.add(subPanel0, BorderLayout.NORTH);
        backPanel.add(mainPanel, BorderLayout.CENTER);
        backPanel.add(joinChat, BorderLayout.SOUTH);
        
        mainPanel.add(subPanel1);
        mainPanel.add(subPanel3);
        mainPanel.add(subPanel2);
//        mainPanel.add();
        mainPanel.add(subPanel5);
        
//        mainPanel.add(subPanel6);
        
//        .getButton().addActionListener(new ActionListener(){

//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                JFileChooser fileChooser = new JFileChooser();
//                fileChooser.showOpenDialog(null);
////                fileChooser.getSelectedFile().toString() = this.directory;
//                setImageDirectory(fileChooser.getSelectedFile().toString());
//            }
//            
//        });
        
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setVisible(true);
        passwordText.setBounds(20, subPanel2.getHeight()-50, 200, 30);
//        .getButton().setBackground(Color.white);
        subPanel3.setTextFieldBounds();
        subPanel5.setCheckboxBounds();
        subPanel1.setTextFieldBounds();
//        .setButtonBounds();
        subPanel1.setLabelBounds();
        password.setBounds(0, subPanel2.getHeight()-87, subPanel2.getWidth(), 30);
        subPanel3.setLabelBounds();
//        .setLabelBounds();
        subPanel5.setLabelBounds();
        
        // Parameters: 
        // Password
        // Server Icon
        // Port
        // Enable Whitelist - if yes, then make list
        
        
    }
    
    public ClientSetup getThis()
    {
        return this;
    }
    
    public String getName()
    {
        return subPanel1.getInput();
    }
    
    public String Password()
    {
        return passwordText.getText();
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
    
    public String getPort()
    {
        return subPanel3.getTextField().getText();
    }
}
