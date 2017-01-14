package hackaz_spring17;

import java.util.ArrayList;

public class List_client {
	
	ArrayList<String> li;
	
	public boolean add(){
		
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
