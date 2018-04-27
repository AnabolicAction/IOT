package tcp4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import tcp3.Server;

public class Client {
	String ip;
	int port;
	Socket socket;
	DataInputStream din;
	InputStream in;
	OutputStream out;
	DataOutputStream dout;
   
   public Client() {
      
   }
	public Client(String ip, int port) throws UnknownHostException, IOException {
		this.ip = ip;
		this.port = port;
		connect();
		startClient();
	}
	public void connect() { // 서버에 연결하는데 실패할경우 주기적으로 요청
		boolean flag2 = true;
		while (flag2) {
			try {
				socket = new Socket(ip, port);
				if (socket != null && socket.isConnected()) {
					break;
				}
			} catch (IOException e) {
				System.out.println("Re try");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
				}
			}
		}
	}
   
   
   Scanner scanner = new Scanner(System.in);
   boolean flag = true;
   
   public void startClient() throws UnknownHostException, IOException{
   
      while(flag){
           System.out.println("Input Client");
           String str =scanner.nextLine();
       	try {
			System.out.println("Connected");
			out=socket.getOutputStream();
			dout = new DataOutputStream(out);
			dout.writeUTF(str);
			dout.flush(); 
			in=socket.getInputStream();
			din=new DataInputStream(in);
		

		} catch (UnknownHostException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			din.close();
			dout.close();
			socket.close();

		}
           if(str.equals("q")) {
              scanner.close();
              break;
           }
         
      }
      
      System.out.println("Stop Clinet");
      
      
      
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
   
   
   
   public static void main(String[] args) {
	     Client client = null;
	      try {
	    	  client = new Client();
	    	  client.startClient();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }

      
      
   }

}