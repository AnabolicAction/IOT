package http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Http2 {

	public static void main(String[] args) throws Exception{
		//websever가 존재하고 http프로토콜로 요청한다
		String name="존잘태우";
		name=URLEncoder.encode(name,"UTF-8"); 
		//한글을 넣게되면 읽어오질 못한다 그렇기때문에 URLEncoder.encode()사용하여야한다

		String surl="http://127.0.0.1/login?id=qq&pwd=11&name="+name;		
		URL url= new URL(surl); //URL의 정보를 읽는다.
		URLConnection con=url.openConnection();
		con.setConnectTimeout(5000);//5초동안 응답이없으면...
		
		InputStream in=con.getInputStream();
		InputStreamReader ir=new InputStreamReader(in,"UTF-8");
		BufferedReader br= new BufferedReader(ir);
		
		String str=br.readLine();//login정보만 받아오면됨
		System.out.println(str);
		br.close();
	}

}
