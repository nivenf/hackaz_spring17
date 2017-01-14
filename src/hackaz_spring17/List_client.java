package hackaz_spring17;

import java.util.ArrayList;

public class List_client {
	
	ArrayList<String> li;
	
<<<<<<< HEAD
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
=======
	public boolean add(){
		
>>>>>>> ea0b4f93685a0c7ee5a47a6f2fd6da457629d628
	}
	
	public boolean remove(int item){
		if(li.isEmpty() || item > li.size()){
			return false;
		}
		else
			li.remove(item);
			return true;
	}
	
	public boolean swap() {
		
	}
	
	public boolean moveToTop() {
		
	}
	
	public String listString() {
		
	}
	
	
}
