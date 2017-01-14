// ☑☐

package hackaz_spring17;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class List_client {

	ArrayList<String> li;
	
	
	public List_client() {
		li = new ArrayList<String>();
	}

	public boolean add(String n) {
		if (n.length() > 0) {
			li.add(n);
			System.out.println("\"" + n + "\"" + " was added to the list.");
			return true;
		} else if (n.length() <= 0) {
			System.out.println("Nothing added to list!");
			return false;
		}
		return false;
	}

	public boolean remove(int item) {
		if (doesExist(item) == false) {
			return false;
		} else
			li.remove(item);
		System.out.println("Item successfully removed");
		return true;
	}

	public boolean swap(int x, int y) {
		System.out.println("What items would you like to swap?");
		if (doesExist(x) && doesExist(y)) {
			String temp = li.get(x);
			li.set(x, li.get(y));
			li.set(y, temp);
<<<<<<< HEAD
=======
			

>>>>>>> 096b63647cf0c0ac99bbc65705f6d50b6174c23e
			return true;
		} else {
			System.out.println("At least one of the items was not found.");
			return false;
		}

	}

	public boolean moveToTop(int item) {
		if (doesExist(item) == false) {
			System.out.println("That item was not in the list.");
			return false;
		} else {
			li.add(0, li.get(item));
			remove(item + 1);
			System.out.println("Sucessfully moved to the top.");
			return true;
		}

	}

	public boolean doesExist(int item) {
		if (li.isEmpty() || item > li.size()) {
			System.out.println("That item was not in the list");
			return false;
		}
		return true;
	}

	public String listString() {
		String result = "";
		if (li.size() != 0)
			for (int x = 0; x < li.size(); x++) {
				result += "☐   " + li.get(x) + "\n";
			}
		return result;
	}

}
