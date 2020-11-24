import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import javax.swing.JFrame;

public class Server {
	static String line = "";
	static JFrame frame;
	static HashMap<String, Item> map = new HashMap<String, Item>();// Creating HashMap

	private static Socket s;
	private static ServerSocket ss;

	public static void main(String[] args) throws Exception {
		ServerModel server_system = new ServerModel();
		ServerModel.readCSV("stocks.csv");
		ss = new ServerSocket(2000);

		// Server GUI
		new ServerView(server_system.getMap());
		System.out.println("s:server started");
		System.out.println("s:server is waiting for client request");

		while (true) {
			s = ss.accept();
			Client client = new Client(s);
			Thread clientThread = new Thread(client);
			// clientList.add( client );
			clientThread.start();

		}
	}

}