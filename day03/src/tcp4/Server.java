package tcp4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	String ip;
	int port;
	Socket socket;
	DataInputStream din;
	InputStream in;
	OutputStream out;
	DataOutputStream dout;
	
   public Server() {
      
   }
   
   
   Scanner scanner = new Scanner(System.in);
   boolean flag = true;
   
   public void startServer(){
   
      while(flag){
           System.out.println("Input Server");
           String str =scanner.nextLine();
           if(str.equals("q")) {
              scanner.close();
              break;
           }
         
      }
      
      System.out.println("Stop Server");
      
      
      
   }
   
   
   class Sender implements Runnable{

		Socket socket;

		public Sender() {

		}

		public Sender(Socket socket) throws IOException {
			this.socket = socket;
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
		}

		@Override
		public void run() {

			System.out.println("Accepted Server...." + socket.getInetAddress());

			try {
				Thread.sleep(3000);
				dout.writeUTF("what's going on bro?");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (dout != null) {
					try {
						dout.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			System.out.println("bye-bye");

		}
      
      
   }
   
   
   class Receiver extends Thread{

	   @Override
	      public void run() {
	    	    try {
	                String msg = din.readUTF();
	                System.out.println(socket.getInetAddress() + " :: " + msg);         
	                dout.writeUTF("서버로부터온 메세지");
	             } catch (IOException e) {
	                e.printStackTrace();
	             } finally {
	                if (dout!= null) {
	                   try {
	                	   dout.close();
	                   } catch (IOException e1) {
	                      e1.printStackTrace();
	                   }
	                }
	                if (din != null) {
	                   try {
	                      din.close();
	                   } catch (IOException e) {
	                      e.printStackTrace();
	                   }
	                }
	                if (socket != null) {
	                   try {
	                      socket.close();
	                   } catch (IOException e) {
	                      e.printStackTrace();
	                   }
	                }
	                
	             }
	         
	      }
      
   }
   
   
   public static void main(String[] args) throws IOException {
	   Server server = null;
	      server = new Server();
		  server.startServer();
   }

}