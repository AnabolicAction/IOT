package t2;

class T1 extends Thread { // Runnable 안에서는 함수 선언을 할수없다
	int result = 1;
	boolean flag = true;

	public int getResult() {
		return this.result;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		while (flag) {
			result++;
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

public class Main {
	public static void main(String[] args) {
		T1 t1 = new T1();
		t1.start();
		int result = 0;
		while (result <= 20) {
			result = t1.getResult();
			System.out.println(result);
			if (result == 20) {
				t1.setFlag(false); //스레드이때 소멸...
				break;
			}
		}
		
	}
}
