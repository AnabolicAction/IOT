package s1;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;

public class SerialTest {

	CommPortIdentifier commPortIdentifier;

	public SerialTest() {

	}

	public SerialTest(String portName) throws NoSuchPortException {
		commPortIdentifier=CommPortIdentifier.getPortIdentifier(portName);
		System.out.println("Connect port");
	}

	public static void main(String[] args) {
		SerialTest serialTest=null;
		
		try {
			serialTest=new SerialTest("COM7");
		} catch (NoSuchPortException e) {
			System.out.println("Connect Fail");
			e.printStackTrace();
		}
	}

}
