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
		InputStreamReader in = null; // 가지고오는거??
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer(); // 스트링은 한번설정한 내용을 변경할수 없기떄문에 StringBuffer사용
		String str = null;

		try {
			in = new InputStreamReader(url.openStream());
			br = new BufferedReader(in);
			while (true) {
				str = br.readLine(); // 한 라인씩읽은 값을 sb에 저장시키기위함
				if (str == null) {
					break;
				}
				sb.append(str+"\n"); // sb에 쌓인다
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close(); //close는 반드시 해줘야하므로 finally에 해줘야한다.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(sb.toString());

	}

}









