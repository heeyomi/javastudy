package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatClientReceiveThread extends Thread{

	private BufferedReader br;
	private Socket socket;
	private String nickName;

	public ChatClientReceiveThread(Socket socket) {
		this.socket = socket;
	}

	public ChatClientReceiveThread(Socket socket, String nickname) {
		this.socket = socket;
		this.nickName = nickname;
	}

	@Override
	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));

			while (true) {
				String message = br.readLine();
				if (message == null) {
					break;
				}
				
				if ("join:ok".equals(message)) {
					System.out.println("어서오세요. " +nickName +"님 환영합니다.");
					System.out.print(">>");
				} else {
					System.out.println(message);
					System.out.print(">>");
				}
			}
		} catch (UnsupportedEncodingException e) {
			ChatClient.ClientLog("Error:"+e);
		} catch (IOException e) {
			ChatClient.ClientLog("Error:"+e);
		} finally {
			try {
				if (socket!= null && socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				ChatClient.ClientLog("Error:"+e);
			}
		}
	}
}
