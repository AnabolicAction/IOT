package day01;

class Thread1 extends Thread {

	String msg;
	boolean flag = true;

	public Thread1(String msg) {
		this.msg = msg;
	}
	public void setFlag(boolean flag) {
		this.flag=flag;
	}

	@Override
	public void run() {
		int i=0;
		while (flag) {
			System.out.println(msg+" "+i++);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Thread2 implements Runnable {
	String msg;
	boolean flag = true;

	public Thread2(String msg) {
		this.msg = msg;
		
	}
	public void setFlag(boolean flag) {
		this.flag=flag;
	}

	@Override
	public void run() {
		int i=0;
		while (flag) {
			System.out.println(msg+" "+i++);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

public class T1 {
	public static void main(String[] args) throws InterruptedException {
		Thread1 t1 = new Thread1("T1");
		Thread2 r=new Thread2("t2");
		Thread t2 = new Thread(r);
		t1.start();
		t2.start();
		Thread.sleep(5000);
		t1.setFlag(false);
		r.setFlag(false);

	}

}
