package hackaz_spring17;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ClientHandler extends Thread {
	private ObjectInputStream input;
	private List<ObjectOutputStream> clients;
}
