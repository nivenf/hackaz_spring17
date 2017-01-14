package hackaz_spring17;

import java.util.ArrayList;

public class List_client {
	
	ArrayList<String> li;
	
	public boolean add(String n) {
		if(n.length() > 0){
			li.add(n);
			System.out.println("\""+ n +"\"" + " was added to the list.");
			return true;
		}
		else if(n.length() <= 0){
			System.out.println("Nothing added to list!");
			return false;
		}
		return false;
	}
	
	public boolean remove() {
		
	}
	
	public boolean swap() {
		
	}
	
	public boolean moveToTop() {
		
	}
	
	public String listString() {
		
	}
	
	
}
