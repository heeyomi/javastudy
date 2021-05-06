package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	public static final int PORT = 8080;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			// 1. Server Socket 생성
			serverSocket = new ServerSocket();
			List<Writer> listWriters = new ArrayList<Writer>();
			
			// 2. binding
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			log("연결 기다림 " + hostAddress + ":" + PORT);
			
			// 3. 요청 대기
			while (true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, listWriters).start();
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

	public static void log(String log) {
		System.out.println("[server] : " + log);
		
	}
}
