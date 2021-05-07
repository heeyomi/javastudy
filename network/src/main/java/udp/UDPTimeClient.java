package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPTimeClient {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = UDPEchoServer.PORT;
	public static final int BUFFER_SIZE= UDPEchoServer.BUFFER_SIZE;

	public static void main(String[] args) {

		DatagramSocket socket = null;

		try {
			// 2. socket 생성
			socket =  new DatagramSocket();

			while (true) {

				// 4. 데이터 쓰기
				byte[] sendData = "".getBytes("UTF-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, new InetSocketAddress(SERVER_IP, SERVER_PORT));
				socket.send(sendPacket);

				// 5. 데이터 읽기
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(receivePacket); //blocking

				byte[] receiveData = receivePacket.getData();
				int length = receivePacket.getLength();
				String now = new String(receiveData, 0, length, "UTF-8");

				System.out.println(now);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null && socket.isClosed() == false) {
				socket.close();
			}
		}
	}

}
