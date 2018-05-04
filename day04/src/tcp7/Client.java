package tcp7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

	String address = "70.12.114.136";
	Socket socket;
	Scanner scanner;
	boolean cflag = true;
	boolean flag = true;

	public Client() { // throws UnknownHostException, IOException


	}

	@Override
	public void run() { //안드로이드에서는 통신을 하는 모듈의 로직은 쓰레드 안에서만 동작한다.
		while (cflag) {
			try {
				socket = new Socket(address, 8888);
				System.out.println("Connected Server ..");
				cflag = false;
				break;
			} catch (IOException e) {
				System.out.println("Retry ..");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		//After Connected..
		try {
			new Receiver(socket).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMsg(String msg) {
		try {
			Sender sender=new Sender(socket);
			sender.setSendMsg(msg);
			new Thread(sender).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*public void startClient() throws Exception {  //화면에서 스케너 읽어드릴려고,근데 안드로이드에서는 필요없음

		
		Sender sender = new Sender(socket);
		scanner = new Scanner(System.in);
		while (flag != false) {

			System.out.println("클라이언트 입력 하세요:");

			String str = scanner.nextLine();
			if (str.trim().equals("q")) {
				Thread t = new Thread(sender);
				sender.setSendMsg("q");
				t.start();
				flag = false;
				scanner.close();
				break;
			}
			Thread t = new Thread(sender);
			sender.setSendMsg(str);
			t.start();
		}
		Thread.sleep(1000);
		socket.close();

		// System.exit(0);

	}*/

	class Sender implements Runnable {
		Socket socket;
		OutputStream out;
		DataOutputStream outw;
		String sendMsg;

		public Sender(Socket socket) throws IOException {
			this.socket = socket;
			out = socket.getOutputStream();
			outw = new DataOutputStream(out);
		}

		public void setSendMsg(String sendMsg) {
			this.sendMsg = sendMsg;
		}

		@Override
		public void run() {
			try {
				if (outw != null) {
					outw.writeUTF(sendMsg);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class Receiver extends Thread {
		Socket socket;
		InputStream in;
		DataInputStream inr;

		public Receiver(Socket socket) throws IOException {
			this.socket = socket;
			in = socket.getInputStream();
			inr = new DataInputStream(in);
		}

		@Override
		public void run() {
			try {
			while (flag && inr != null) {
		
					String str = inr.readUTF();
					System.out.println(str);
					if (str.trim().equals("q")) {
						inr.close();
						break;
					}
				} 
			}catch (Exception e) {
				e.printStackTrace();
				
			}finally {
				if(inr !=null) {
					try {
						inr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	public void stopClient() {
		try {
			Thread.sleep(1000);
			flag=false; 
			if(socket !=null) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}