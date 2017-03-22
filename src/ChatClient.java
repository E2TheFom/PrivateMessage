import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class ChatClient extends Thread {
   private String IP;
   private DataInputStream in;
   private DataOutputStream out;
   private Socket client;
   private MessagingHome message;
   private Timer inputCheck;
   private String port;
   private ProfileBox profileBox;
   private String displayName = "Test Name";
   private SecurePHI ed;
   
//   private String IPAddress;
   
   
   public ChatClient(MessagingHome message, String port, String IP, String password) throws IOException {
	   
      this.message = message;
      this.port = port;
//      this.port = Integer.parseInt(port);
      this.IP = IP;
      this.ed = new SecurePHI(MessagingHome.length16(password));
   
      
      
//      checkForNewMessage();
   }

   public void setProfileBox(ProfileBox profileBox)
   {
	   this.profileBox = profileBox;
   }
   
   @Override
public void run()
   {
	   try {
		client = new Socket(IP, Integer.parseInt(port));
		profileBox.setDotColor((byte) 1);
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	      try {
			in = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      try {
			out = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      try {
			in.readByte();
			displayName = ed.decrypt(in.readUTF());
			profileBox.setName(displayName);
			System.out.println(profileBox +": " + displayName);
			JLabel nameLabel = new JLabel(displayName);
//			JOptionPane.showMessageDialog(null, displayName);
			nameLabel.setFont(JMessaging.getOpenSansBold());
			profileBox.setNameLabel(nameLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      
	      
	      checkForNewMessage();
   }
   
//   public void run() {
//      while(true) {
//         try {
//            
////            serverSocket = new ServerSocket(port);
////            serverSocket.setSoTimeout(100000);
////            server = serverSocket.accept();
//        	 client = new Socket(IP, port);
//            in = new DataInputStream(client.getInputStream());
//            out = new DataOutputStream(client.getOutputStream());
//            checkForNewMessage();
////            server.close();
//            
//         }catch(SocketTimeoutException s) {
//            System.out.println("Socket timed out!");
//            break;
//         }catch(IOException e) {
//            e.printStackTrace();
//            break;
//         }
//      }
//   }
//   
   public void checkForNewMessage()
   {
	   
	   String x = "";
       while(true)
       {
            try
            {
                String g = in.readUTF();
                System.out.println(g);
                x = ed.decrypt(g);
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                profileBox.setDotColor((byte) 2);
                JOptionPane.showMessageDialog(null, "Connection reset", displayName, JOptionPane.ERROR_MESSAGE);
                
                e.printStackTrace();
                
//                JOptionPane.showMessageDialog(null, "Server connection reset", name, JOptionPane.ERROR_MESSAGE);
//                
                break;
            }
            if(!(x.isEmpty()))
                    {
            	profileBox.getChatBox().newMessage(x, "recieved");
                    }
            try
            {
                Thread.sleep(7);
            } catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
       }
	   
	   
//	   while(true){
//       String x = "";
//      
//            try
//            {
//                x = in.readUTF();
//            } catch (IOException e)
//            {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            if(!(x.isEmpty()))
//                    {
//                profileBox.getChatBox().newMessage(x, "recieved");
//                    }
//            
//       System.out.println("Checked");
//       try {
//		Thread.sleep(7);
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	   }
   }
   
  public String getDisplayName()
  {
	  return displayName;
  }
   
   public void sendString(String message2)
   {
       try
    {
        out.writeUTF(ed.encrypt(message2));
    } catch (IOException e)
    {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
   }
   
//   public static void main(String [] args) {
//      int port = Integer.parseInt(args[0]);
//      try {
//         Thread t = new ChatServer(port);
//         t.start();
//      }catch(IOException e) {
//         e.printStackTrace();
//      }
//   }
}
