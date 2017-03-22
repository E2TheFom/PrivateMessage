import java.net.*;

import javax.swing.JOptionPane;

import java.io.*;

public class ChatServer extends Thread {
   private ServerSocket serverSocket;
   private DataInputStream in;
   private DataOutputStream out;
   private Socket server;
   private MessagingHome message;
   private int port;
   private ProfileBox profileBox;
   private String name;
   private SecurePHI ed;


//   private String IPAddress;
   
   
   public ChatServer(MessagingHome message, String port, String name, String password) throws IOException {
      this.message = message;
      this.port = Integer.parseInt(port);
      this.name = name;
      ed = new SecurePHI(MessagingHome.length16(password));
      
   }
   
   public void setProfileBox(ProfileBox profileBox)
   {
	   this.profileBox = profileBox;
   }

   @Override
public void run() {
      while(true) {
         try {
//        	 JOptionPane.showMessageDialog(null, "Restarted Server");
            System.out.println(port);
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(100000);
            server = serverSocket.accept();
            profileBox.setDotColor((byte) 1);
            in = new DataInputStream(server.getInputStream());
            out = new DataOutputStream(server.getOutputStream());
            out.writeByte(0);
            out.writeUTF(ed.encrypt(name));
            System.out.println("out constructed");
            checkForNewMessage();
//            server.close();
            
         }catch(SocketTimeoutException s) {
             profileBox.setDotColor((byte) 2);
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e) {
            e.printStackTrace();
            profileBox.setDotColor((byte) 2);
            break;
         }
      }
   }
   
   public void checkForNewMessage()
   {
       String x = "";
       while(true)
       {
            try
            {
                x = ed.decrypt(in.readUTF());
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                profileBox.setDotColor((byte) 2);
                JOptionPane.showMessageDialog(null, "Connection reset", name, JOptionPane.ERROR_MESSAGE);
                
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
        
   }
   
  
   
   public void sendString(String message2)
   {
       try
    {
        out.writeUTF(ed.encrypt(message2));

        System.out.println("Send message: " + message);
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