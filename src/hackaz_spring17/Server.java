package hackaz_spring17;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {

	public static final int Server_Port = 1025;
	
	private static ServerSocket socket;
	
	private static List<ObjectOutputStream> clients = Collections.synchronizedList(new ArrayList<ObjectOutputStream>());
			
	
	public static void main(String[] args) throws IOException {
		socket = new ServerSocket(Server_Port);
		System.out.println("Server up and running successfully. \n Port number "
		+ Server_Port + " being used.");
		
		while(true){
			Socket sock = socket.accept();
			
			ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());
			ObjectOutputStream outputStream = new ObjectOutputStream(sock.getOutputStream());
			
			clients.add(outputStream);
			
			ClientHandler manager = new ClientHandler(inputStream, clients);
			manager.start();
			
			System.out.println("New connection accepted onto server from " 
			+ socket.getInetAddress());
		}
	}	
}
