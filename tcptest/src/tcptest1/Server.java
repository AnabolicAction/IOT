package tcptest1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	boolean flag;
	ServerSocket serverSocket;
	int port = 7777;

	public Server() {
		flag = true;
		try {
			System.out.println("Start");
			System.out.println("Ready");
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (flag) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("connected");
				new Receiver(socket).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	class Receiver extends Thread { // �޴°Ŵ� ���Ƽ� ����Ǿ��ϴϱ� ������ ������� ��׶��� �۾�����
		InputStream in;
		DataInputStream din;
		Socket socket;

		public Receiver(Socket socket) {// Reveiver�� ��� ��� ������ ������ �������ΰ�
			this.socket = socket;
			try {
				in = socket.getInputStream();
				din = new DataInputStream(in);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		@Override
		public void run() {
			while (flag) {
				try {
					String msg = din.readUTF();
					System.out.println(msg);
				} catch (IOException e) {
					e.printStackTrace();
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
	public static void main(String[] args) {
		Server server=new Server();
		server.start();
		
	}
}
