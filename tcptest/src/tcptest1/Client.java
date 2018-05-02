package tcptest1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread {

	int port;
	String address;
	boolean flag;
	Socket socket;

	public Client() {
		port = 7777;
		address = "127.0.0.1";
		flag = true;

	}

	@Override
	public void run() {
		
		try {
			socket = new Socket(address, port);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);
		String msg;
		
		while (flag) {
			msg = sc.nextLine();
			System.out.println(msg);
		Sender sender=new Sender(msg);
		sender.start();
		}
		sc.close();
		
	}

	class Sender extends Thread {
		OutputStream out;
		DataOutputStream dout;
		String msg;

		public Sender(String msg) {
			this.msg = msg;
			try {
				out = socket.getOutputStream();
				dout = new DataOutputStream(out);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		@Override
		public void run() {
			try {
				dout.writeUTF(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
	public static void main(String[] args) {
		Client clinet=new Client();
		clinet.start();
		
	}

}
