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

	ServerSocket serverSocket; // ����Ʈ��Ʈ���� ����
	boolean flag = true;
	// �������� Ŭ���̾�Ʈ��ü �����ϱ�����
	ArrayList<DataOutputStream> list = new ArrayList<>();

	// ������ �����ɶ� ��������9999�� ����
	public Server2() throws IOException {
		serverSocket = new ServerSocket(9999);
		System.out.println("Server Ready");
	}

	// client ����ó�� : while loop ���鼭 Ŭ���̾�Ʈ������ �׋����� ���ϻ���
	public void start() throws IOException {
		while (flag) {
			Socket socket = serverSocket.accept(); // �����û�Ǵºκ�,������
			System.out.println(socket.getInetAddress() + "connected");

		}

	}

	// ������ ���ϸ��� ��ǲ��Ʈ�� �����ϰ� ������ �޵���
	// ������ ���ϸ��� �ƿ�ǲ��Ʈ���� ���⼭ �޾Ƽ� arraylist�� �־��ֱ⸸ �Ѵ�
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
			// dos���� �о�� ���� list�� �����Ų��. ����� �������ִ��� �˼��� �ִ�
			list.add(dos);
		}

		// �����屸��
		@Override
		public void run() {
			// dis�� ������ ���� ��� ���ƶ�
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
			// client�� �Ѹ����� �׷��� remove
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
