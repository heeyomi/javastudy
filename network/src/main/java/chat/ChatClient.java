package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import echo.EchoServer;

public class ChatClient {
	private static final String SERVER_IP = "0.0.0.0";
	private static final int SERVER_PORT = ChatServer.PORT;

	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;

		try {
			//1. 키보드 연결
			scanner = new Scanner(System.in);

			//2. socket 생성
			socket = new Socket();

			//3. 연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			log("connected");

			//4. reader/writer 생성
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			//5. join 프로토콜
			System.out.print("닉네임>>" );
			String nickname = scanner.nextLine();
			printWriter.println( "join:" + nickname );
//			String line = br.readLine(); // ack를 여기서 받아줘야 함 아니면 thread에서 ack를 받아버림
			printWriter.flush();

			//6. ChatClientReceiveThread 시작
			new ChatClientReceiveThread().start();

			//7. 키보드 입력 처리
			while( true ) {
				System.out.print( ">>" );
				String input = scanner.nextLine();

				if( "quit".equals( input ) == true ) {
					// 8. quit 프로토콜 처리
					break;
				} else {
					// 9. 메시지 처리      
				}
			}

		} 
		catch( IOException ex ) {
			ex.printStackTrace();
			log( "error:" + ex );
		} finally {
			//10. 자원정리
			try {
				if (scanner != null) {
					scanner.close();
				}
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void log(String log) {
		System.out.println("[Client :" + log + "]");
	}

}
