package Client_Tr;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
public class CMain {
	public static void main(String[] args) throws Exception {
		Socket withServer = null;
		withServer = new Socket("10.0.0.40",9999);
		new ClientChat(withServer);
	}

}
