import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	int port = 1234;
	BufferedReader in = null;
	PrintWriter out = null;
	ServerSocket server;
	Socket socket;
	
	public void setup() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					server = new ServerSocket(port);
					socket = server.accept();
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out = new PrintWriter(socket.getOutputStream(), true);
					String msg = null;
					while ((msg = in.readLine()) != null) {
						System.out.println(msg);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}
	public static void main(String[] args) {

	}

}
