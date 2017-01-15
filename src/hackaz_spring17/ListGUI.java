package hackaz_spring17;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ListGUI extends JFrame {
	public static void main(String[] args) {
		String in = JOptionPane.showInputDialog("What would you like to name the list?");
		List_client li = new List_client(in);
	}
}
