package tcp5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import tcp5.Server.SendHttp;

public class Server2 {

	ServerSocket serverSocket; // 컨스트럭트에서 생성
	boolean flag = true;
	// 여러개의 클라이언트객체 수용하기위해
	ArrayList<DataOutputStream> list = new ArrayList<>();

	// 서버가 생성될때 서버소켓9999가 생성
	public Server2() throws IOException {
		serverSocket = new ServerSocket(9999);
		System.out.println("Server Ready");
	}

	// client 접근처리 : while loop 돌면서 클라이언트들어오면 그떄마다 소켓생성
	public void start() throws IOException {
		while (flag) {
			Socket socket = serverSocket.accept(); // 연결요청되는부분,대기상태
			System.out.println(socket.getInetAddress() + "connected");

		}

	}

	// 각각의 소켓마다 인풋스트림 생성하고 데이터 받도록
	// 각각의 소켓마다 아웃풋스트림도 여기서 받아서 arraylist에 넣어주기만 한다
	class Receiver extends Thread {
		InputStream in;
		DataInputStream dis;
		OutputStream out;
		DataOutputStream dos;

		public Receiver(Socket socket) throws IOException {
			in = socket.getInputStream();
			dis = new DataInputStream(in);

			out = socket.getOutputStream();
			dos = new DataOutputStream(out);
			// dos으로 읽어온 값을 list에 저장시킨다. 몇명이 접속해있는지 알수도 있다
			list.add(dos);
		}

		// 쓰래드구현
		@Override
		public void run() {
			// dis가 있을때 까지 계속 돌아라
			while (dis != null) {
				String str = "";
				try {
					str = dis.readUTF();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (str.equals("q") && str != null) {
					break;
				}
				System.out.println(str);
				// send Spring Server
				SendHttp http = new SendHttp(str);
				http.start();
			}
			// client가 한명나갔으 그러면 remove
			list.remove(dos);
			if (dos != null) {
				try {
					dos.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	class SendHttp extends Thread {
		String speed;
		String tmp;
		String urls = "http://127.0.0.1/ws/main.do";

		public SendHttp(String msg) {
			String[] divide = msg.split(",");
			this.speed = divide[0].trim();
			this.tmp = divide[1].trim();
		}

		@Override
		public void run() {
			urls += "?speed=" + speed + "&tmp=" + tmp;
			URL url;
			try {
				url = new URL(urls);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setConnectTimeout(5000);
				con.getInputStream();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		Server server=null;
		try {
			server=new Server();
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
