package http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Http2 {

	public static void main(String[] args) throws Exception{
		//websever�� �����ϰ� http�������ݷ� ��û�Ѵ�
		String name="�����¿�";
		name=URLEncoder.encode(name,"UTF-8"); 
		//�ѱ��� �ְԵǸ� �о���� ���Ѵ� �׷��⶧���� URLEncoder.encode()����Ͽ����Ѵ�

		String surl="http://127.0.0.1/login?id=qq&pwd=11&name="+name;		
		URL url= new URL(surl); //URL�� ������ �д´�.
		URLConnection con=url.openConnection();
		con.setConnectTimeout(5000);//5�ʵ��� �����̾�����...
		
		InputStream in=con.getInputStream();
		InputStreamReader ir=new InputStreamReader(in,"UTF-8");
		BufferedReader br= new BufferedReader(ir);
		
		String str=br.readLine();//login������ �޾ƿ����
		System.out.println(str);
		br.close();
	}

}