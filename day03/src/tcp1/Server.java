package tcp1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	int port;
	ServerSocket serversocket;
	Socket socket;
	OutputStream out;
	OutputStreamWriter outw;
	boolean flag;

	public Server() throws IOException {
		port = 7777;
		serversocket = new ServerSocket(port); // Ư����Ʈ�� �̿��Ͽ� ������Ȱ�� �Ұ��̴�. �ٸ������ ����������ϰ������� ���Ұ�
	}

	public void startServer() throws IOException {

		System.out.println("Start Server....");
		System.out.println("Ready Server....");
		while (flag) {
			try {

				socket = serversocket.accept(); // server�ʿ��� ��������(client) ������������ ����ȵ�
				System.out.println("Accepted Server...." + socket.getInetAddress());
				out = socket.getOutputStream();
				outw = new OutputStreamWriter(out);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				outw.write("������ �θ��ȳ������� �������� �����������������...���� ���̵�� �װڳ�");

			} catch (IOException e) {
				throw e;
			} finally {
				if (outw != null) {
					outw.close();
				}
				if (socket != null) {
					socket.close();
				}
				outw.close();
				socket.close();
				System.out.println("End Server....");
			}
		}
	}

	public static void main(String[] args) {
		Server server = null;
		try {
			server = new Server();
			server.startServer();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
