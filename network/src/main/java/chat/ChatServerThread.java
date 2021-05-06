package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ChatServerThread extends Thread {

	private String nickname;
	private Socket socket;
	private List<Writer> listWriters;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;


	public ChatServerThread(Socket socket) {
		this.socket = socket;
	}

	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		try {
			// 1. Remote Most Information
			InetSocketAddress inetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetSocketAddress.getAddress().getHostAddress();
			int remoteHostPort = inetSocketAddress.getPort();
			ChatServer.log("connected by client[" + remoteHostAddress + ":" + remoteHostPort +"]");

			// 2. Stream 얻기
			
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

			// 3. 요청 처리
			while (true) {
				String request = bufferedReader.readLine();
				if (request == null) {
					ChatServer.log("클라이언트로 부터 연결 끊김");
					doQuit(printWriter);
					break;
				}
				
				//4. 프로토콜 분석
				String[] tokens = request.split(":");
				
				for (int i = 0; i < tokens.length; i++) {
					System.out.println(tokens[i]);
				}
				if ("join".equals(tokens[0])) {
					doJoin(tokens[1], printWriter);
				} else if ("message".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if ("quit".equals(tokens[0])) {
					doQuit(printWriter);
					System.out.println(listWriters.size());
				} else {
					ChatServer.log("에러:알 수 없는 요청(" + tokens[0] +")");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	private void doQuit(Writer writer) {
		removeWriter(writer);

		String data = nickname + "님이 퇴장했습니다.";
		broadCast(data);
	}

	private void removeWriter(Writer writer) {
		for (int i = 0; i < listWriters.size(); i++) {
			if (listWriters.get(i).equals(writer)) {
				listWriters.remove(i);
			}
		}
	}

	private void doMessage(String message) {

	}

	private void doJoin(String nickName, Writer writer) {
		this.nickname = nickName;

		String data = nickName + "님이 참여했습니다.";
		broadCast(data);

		/* writer pool에 저장*/
		addWriter(writer);
		System.out.println(listWriters.size());
		

		// ack
		printWriter.println("join:ok");
		printWriter.flush();

	}

	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);	
		}
	}

	private void broadCast(String data) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter)writer;
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}

}
