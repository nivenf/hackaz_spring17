package hackaz_spring17;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


public class ClientHandler extends Thread {
	private ObjectInputStream input;
	private List<ObjectOutputStream> clients;
	
	public ClientHandler(ObjectInputStream input, List<ObjectOutputStream> clients){
		this.input = input;
		this.clients = clients;
	}
	
	@Override
	public void run(){
		while(true){
			String message = null;
			
			try{
				message = (String) input.readObject();
			}catch (Exception e) {
				e.printStackTrace(); 
			}
			this.writeStringToClients(message);
		}
	}

	private void writeStringToClients(String message) {
		synchronized (clients){
			for(ObjectOutputStream client : clients){
				try {
					client.writeObject(message);
					client.reset();
				} catch(IOException e) {
					e.printStackTrace();
					clients.remove(client);
				}
			}
		}
	}
	
}
