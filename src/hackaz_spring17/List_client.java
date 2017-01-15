// â˜‘â˜�

package hackaz_spring17;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class List_client extends JFrame {

	ArrayList<String> li;
	JTextField field;
	JButton add;
	JButton remove;
	JButton swap;
	JButton move;
	JTextField list;
	
	
	public List_client() {
		li = new ArrayList<String>();
		setupGUI();
	}
	
	private void setupGUI() {
		setTitle("TEST");
		setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add = new JButton("Add");
		remove = new JButton("Remove");
		swap = new JButton("Swap");
		move = new JButton("Move To Top");
		
		JPanel panel = new JPanel();
		panel.add(add);
		panel.add(remove);
		panel.add(swap);
		panel.add(move);
		
		list = new JTextField();
		list.setFont(new Font("Arial", Font.TRUETYPE_FONT, 12));
		list.setSize(new Dimension(100, 100));
		JPanel listPane = new JPanel();
		list.setText(this.listString());
		listPane.add(list);
		
		this.getContentPane().add(listPane);
		this.getContentPane().add(panel);
		
		setVisible(true);
	}

	public boolean add(String n) {
		if (n.length() > 0) {
			li.add(n);
			System.out.println("\"" + n + "\"" + " was added to the list.");
			this.field.setText(this.listString());
			return true;
		} else if (n.length() <= 0) {
			System.out.println("Nothing added to list!");
			return false;
		}
		return false;
	}

	public boolean removeIndex(int item) {
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
				result += "â˜�   " + li.get(x) + "\n";
			}
		return result;
	}

}
