package Server_Tr;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerChat extends Thread {

	private Socket withClient = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String id = null;
	private ServerCenter sc = null;

	private ArrayList<Thread> tList = new ArrayList<>();

	ServerChat(Socket c, ServerCenter s) {
		this.withClient = c;
		this.sc = s;
		// streamSet();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		streamSet();
		receive();
		//send();
	}

	private void receive() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("receive start~~");
					while (true) {
						reMsg = withClient.getInputStream();
						byte[] reBuffer = new byte[100];
						reMsg.read(reBuffer);
						String msg = new String(reBuffer);
						msg = msg.trim();
						sc.reMsg(msg,id);
						// System.out.println("[" + id + "] " + msg);
					}
				} catch (Exception e) {
					System.out.println("receive End");
					return;
				}
			}
		}).start();

	}

	public void send(String reMsg) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			sendMsg = withClient.getOutputStream();
			sendMsg.write(reMsg.getBytes());

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("send End");
			return;
		}
	}

	private void streamSet() {
		try {
			reMsg = withClient.getInputStream();
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			id = new String(reBuffer);
			id = id.trim();

			InetAddress c_ip = withClient.getInetAddress();
			String ip = c_ip.getHostAddress();

			System.out.println(id + "님 로그인 (" + ip + ")");

			String reMsg = "정상접속 되었습니다.";
			sendMsg = withClient.getOutputStream();
			sendMsg.write(reMsg.getBytes());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
