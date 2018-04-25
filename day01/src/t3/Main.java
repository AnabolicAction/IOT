package t3;

import java.util.Scanner;

class Receiver implements Runnable{
	
	String cmd;
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	
	@Override
	public void run() {
		loop: while(true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(cmd !=null && cmd.equals("s")) {
				//Send Message
				for(int i=0;i<=50;i++) {
					if(cmd !=null && cmd.equals("e")) {
						break loop;
					}
					System.out.println(i);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//send Message end....
			}
			
		}
	}
	
}

public class Main {
	
	public static void main(String[] args) {
		Receiver r=new Receiver();
		Thread t=new Thread(r);
		t.start(); //스레드시작
		
		Scanner sc=new Scanner(System.in);
		System.out.println("input cmd s ");
		String cmd=sc.nextLine();
		r.setCmd(cmd.trim());
		
		System.out.println("input cmd e");
		String cmd2=sc.nextLine();
		r.setCmd(cmd2.trim());
		
		sc.close();
	}

}












