package tcp2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	int port;
	ServerSocket serversocket;
	// Socket socket;
	boolean flag = true;

	public Server() throws IOException {
		port = 7777;
		serversocket = new ServerSocket(port); // 특정포트를 이용하여 서버역활을 할것이다. 다른사람이 서버를사용하고있을때 사용불가
	}

	// Accept Client Socket And
	// Sender THread Create(Send Socket) and Start
	private void startServer() {

		while (true) {

			try {
				Sender sender = new Sender(serversocket.accept());
				Thread t = new Thread(sender);
				t.start();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} // while문

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