package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class ChatServerThread extends Thread{
	private Socket socket;
	private String nickname;

	private List<Writer> listWriters;
	private BufferedReader br;
	private PrintWriter pw;


	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		// 1. Remote Most Information
		InetSocketAddress inetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
		String remoteHostAdress = inetSocketAddress.getAddress().getHostAddress();
		int remoteHostPort = inetSocketAddress.getPort();
		ChatServer.ServerLog("connected by client[" + remoteHostAdress +":"+remoteHostPort);

		try {
			// 2. 스트림 얻기
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),true);

			// 3. 요청 얻기
			while (true) {
				String request = br.readLine();
				if (request == null) {
					ChatServer.ServerLog("클라이언트로 부터 연결 끊김");
					doQuit(pw);
					break;
				}

				// 4. 프로토콜 분석
				String[] tokens = request.split(":");
				if ("join".equals(tokens[0])) {
					doJoin(tokens[1], pw);
				} else if ("message".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if ("quit".equals(tokens[0])) {
					doQuit(pw);
				} else {
					ChatServer.ServerLog("Error:알 수 없는 요청(" + tokens[0] +")");
				}

			}
		} catch (UnsupportedEncodingException e) {
			ChatServer.ServerLog("Error : " +e);
		} catch (IOException e) {
			doQuit(pw);
			ChatServer.ServerLog("Error : " +e);
		} finally {
			try {
				if (socket!=null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				ChatServer.ServerLog("Error:" + e);
			}
		}
	}

	private void doQuit(Writer writer) {
		removeWriter(writer);

		String data = nickname + "님이 퇴장했습니다.";
		broadcast(data);
	}

	private void removeWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.remove(writer);
		}

	}

	private void doMessage(String message) {
		broadcast(nickname+":"+message);
	}

	private void doJoin(String nickName, Writer writer) {
		this.nickname = nickName;

		String data = nickName + "님이 참여했습니다.";
		broadcast(data);

		/* water pool에 저장 */
		addWriter(writer);

		// ack
		PrintWriter pw = new PrintWriter(writer);
		pw.println("join:ok");
		pw.flush();
	}

	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}

	private void broadcast(String data) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter pw = (PrintWriter) writer;
				pw.println(data);
				pw.flush();
			}
		}
	}


}
