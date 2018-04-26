package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Http1 {

	public static void main(String[] args) {
		/*
		 * InetAddress ia = null; ia = InetAddress.getByName("localhost");
		 * System.out.println(ia.getHostAddress());
		 * System.out.println(ia.getHostName());
		 */

		URL url = null;
		String address = "http://127.0.0.1/index.html";

		try {
			url = new URL(address);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		InputStreamReader in = null; // ��������°�??
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer(); // ��Ʈ���� �ѹ������� ������ �����Ҽ� ���⋚���� StringBuffer���
		String str = null;

		try {
			in = new InputStreamReader(url.openStream());
			br = new BufferedReader(in);
			while (true) {
				str = br.readLine(); // �� ���ξ����� ���� sb�� �����Ű������
				if (str == null) {
					break;
				}
				sb.append(str+"\n"); // sb�� ���δ�
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close(); //close�� �ݵ�� ������ϹǷ� finally�� ������Ѵ�.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(sb.toString());

	}

}









