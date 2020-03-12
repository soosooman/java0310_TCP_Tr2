package Server_Tr;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class SMain {
	public static void main(String[] args) throws Exception{
		// 서버와 클라이언트와 양방향 통신 program ver 1.0
		// TCP 통신을 하기 위한 자원
		ServerSocket serverS = null;
		Socket withClient = null;
		serverS = new ServerSocket();
		serverS.bind(new InetSocketAddress("10.0.0.40",9999));
		
		ArrayList<Socket> cList = new ArrayList<>();
		ServerCenter sc = new ServerCenter();
		while(true) {
			System.out.println("서 버대기중");
			withClient = serverS.accept();
			cList.add(withClient);
			System.out.println(cList);
			System.out.println(withClient.getInetAddress()+"클라이언트 접속 함");
			ServerChat s =new ServerChat(withClient,sc);
		//	sc.sList.add(s);
			sc.addServerChat(s);
			s.start();
		
		}
	}

}
