package udp;

import java.net.DatagramPacket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

public class UDPTimeServer {

	public static void main(String[] args) {
		// 2. 데이터 수신
		DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
		socket.receive(receivePacket); //blocking
		
		// 3. 데이터 송신
		String now = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		byte[] sendData = "".getBytes();
		
	}

}
