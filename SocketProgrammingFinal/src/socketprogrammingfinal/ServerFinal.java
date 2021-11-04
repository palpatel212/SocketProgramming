package socketprogrammingfinal;
import java.net.*;
import java.io.*;

public class ServerFinal {
	
	private ServerSocket serversocket;
    static  int port = 1111;
	public ServerFinal(ServerSocket serversocket) {
		this.serversocket= serversocket;
	}
	public void initializeServer() {
		try {
			while(!serversocket.isClosed()) {
				Socket s = serversocket.accept();
				System.out.println("A new connection has been established");
				PortThread clients = new PortThread(s);		
				Thread thread = new Thread(clients);
				thread.start();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void closeServerSocket() {
		try {
			if(serversocket != null) {
				serversocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		ServerSocket serversocket = new ServerSocket(port);
		ServerFinal server = new ServerFinal(serversocket);
		System.out.println("Ready for clients to connect\n");
		server.initializeServer();
	}
}