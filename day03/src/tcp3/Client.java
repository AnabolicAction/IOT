package tcp3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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

	public void startClient() throws UnknownHostException, IOException {
		try {

			System.out.println("Connected");
			out=socket.getOutputStream();
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
			dout.writeUTF("안녕");
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
	}

	public static void main(String[] args) {
		Client client = null;
		try {
			client = new Client("70.12.114.136", 7777);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	class Sender implements Runnable {

		OutputStream out;
		OutputStreamWriter outw;
		Socket socket;

		public Sender() {

		}

		public Sender(Socket socket) throws IOException {
			this.socket = socket;
			out = socket.getOutputStream();
			outw = new OutputStreamWriter(out);
		}

		@Override
		public void run() {

			System.out.println("Accepted Server...." + socket.getInetAddress());

			try {
				Thread.sleep(3000);
				outw.write("what's going on bro?");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (outw != null) {
					try {
						outw.close();
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

}
