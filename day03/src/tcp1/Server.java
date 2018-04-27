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
		serversocket = new ServerSocket(port); // 특정포트를 이용하여 서버역활을 할것이다. 다른사람이 서버를사용하고있을때 사용불가
	}

	public void startServer() throws IOException {

		System.out.println("Start Server....");
		System.out.println("Ready Server....");
		while (flag) {
			try {

				socket = serversocket.accept(); // server쪽에서 누군가가(client) 들어오기전까지 실행안됨
				System.out.println("Accepted Server...." + socket.getInetAddress());
				out = socket.getOutputStream();
				outw = new OutputStreamWriter(out);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				outw.write("영무형 두리안냄새나요 옆에서는 수영장락스냄새나고...양쪽 사이드로 죽겠네");

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
