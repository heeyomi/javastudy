package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Localhost {

	public static void main(String[] args) {
		try {
			InetAddress inetAdress = InetAddress.getLocalHost(); //localhost에 대한 정보
			//remote는 나랑 연결되어서 정보를 주고 받는 호스트
			//나는 localhost
			String hostName = inetAdress.getHostName();
			String hostAdress = inetAdress.getHostAddress();
			
			System.out.println(hostName);
			System.out.println(hostAdress);
			
			byte[] addresses = inetAdress.getAddress();
			for(byte adress : addresses) {
				System.out.print(adress & 0x000000ff);
				System.out.print(".");
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

}
