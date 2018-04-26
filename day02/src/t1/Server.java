package t1;

import java.util.Scanner;

public class Server {
	boolean flag = true;

	public void startServer() {
		Scanner client = new Scanner(System.in);
		while (flag) {
			System.out.println("Server Ready");
			String msg = client.nextLine(); // while������ �����ʰ� ���� ��ٸ���
			// System.out.println(msg);
			// Reciver Thread����
			Receiver receiver=new Receiver(msg);
			receiver.start();
		}
	}

	public static void main(String[] args) {
		System.out.println("Sever Start");
		new Server().startServer();
		System.out.println("Sever Stop");
	}

	class Receiver extends Thread { // thread��ӹ��� Ŭ�����̰� Receiver �� ��Ȱ�� �Ѵ�
		String msg;

		public Receiver() {
		} // Receiver�� �Ű����� msg������ ����Ʈ�����ڸ� �ϳ��� ����������

		public Receiver(String msg) {
			this.msg = msg;
		}

		@Override // ��û�� �޾Ƽ� ó���ϰ� �ٽ� �����ϴ� ��Ȱ�� �Ѵ�.
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
			// Sender Thread�� ���� client���� ����
			Sender sender = new Sender(msg);
			sender.start();

		} // end Receiver

		class Sender extends Thread { // Receiver�� sender�� �����尡 �ƴϸ� �ٸ��۾� ����Ұ�
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
