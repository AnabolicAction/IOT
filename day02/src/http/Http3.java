package http;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Http3 {

	public static void main(String[] args) throws Exception {
		URL url=new URL("http://127.0.0.1/hzmr_demo.zip");
		InputStream in=url.openStream();
		FileOutputStream out=new FileOutputStream("down.zip"); //읽은다음에 down 저장
		int i=0;
		while(true) {
			i=in.read();
			System.out.println(i);
			if(i !=-1) {
				break;
			}
		}
		in.close();
		out.close();
	}

}
