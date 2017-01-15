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

	public static final int Server_Port = 7;
	
	private static ServerSocket socket;
	
	 while (true) {
	      Socket s = socket.accept();

	      ObjectInputStream inputStream = new ObjectInputStream(s.getInputStream());
	      ObjectOutputStream outputStream = new ObjectOutputStream(s.getOutputStream());
	
}
