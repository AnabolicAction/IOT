package t1;

import java.util.Scanner;

public class Server {
	boolean flag = true;

	public void startServer() {
		Scanner client = new Scanner(System.in);
		while (flag) {
			System.out.println("Server Ready");
			String msg = client.nextLine(); // while루프를 돌지않고 값을 기다린다
			// System.out.println(msg);
			// Reciver Thread동작
			Receiver receiver=new Receiver(msg);
			receiver.start();
		}
	}

	public static void main(String[] args) {
		System.out.println("Sever Start");
		new Server().startServer();
		System.out.println("Sever Stop");
	}

	class Receiver extends Thread { // thread상속받은 클래스이고 Receiver 의 역활을 한다
		String msg;

		public Receiver() {
		} // Receiver의 매개변수 msg때문에 디폴트생성자를 하나더 만들어줘야함

		public Receiver(String msg) {
			this.msg = msg;
		}

		@Override // 요청을 받아서 처리하고 다시 전송하는 역활을 한다.
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.print(i);
			}
			System.out.println(msg + "Completed");
			// Sender Thread를 통해 client에게 전송
			Sender sender = new Sender(msg);
			sender.start();

		} // end Receiver

		class Sender extends Thread { // Receiver와 sender가 쓰레드가 아니면 다른작업 수행불가
			String msg;

			public Sender() {
			}

			public Sender(String msg) {
				this.msg = msg;
			}

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.print(i);
				}
				System.out.println(msg + ":Send Completed");
			}

		}

	}

} // Sever class
