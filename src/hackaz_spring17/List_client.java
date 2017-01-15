// â˜‘â˜�

package hackaz_spring17;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

public class List_client extends JFrame {

	ArrayList<String> li;
	String name;
	JTextField field;
	JButton add;
	JButton remove;
	JButton swap;
	JButton move;
	JScrollPane list;
	JList<String> user_list;
	ListModel<String> listModel = new DefaultListModel<String>();
	
	
	public List_client() {
		name = "My List";	
		li = new ArrayList<String>();
		setupGUI();
	}
	
	public List_client(String in) {
		name = in;
		li = new ArrayList<String>();
		setupGUI();
	}
	
	private void setupGUI() {
		this.user_list = new JList<String>(listModel);
		MyMouseAdaptor myMouseAdaptor = new MyMouseAdaptor();
		this.user_list.addMouseListener(myMouseAdaptor);
		this.user_list.addMouseMotionListener(myMouseAdaptor);
		
		user_list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList)evt.getSource();
				if(evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					String r = ((DefaultListModel<String>) listModel).getElementAt(index);
					if(r.charAt(0) == 'O')
						((DefaultListModel<String>) listModel).set(index, "X" + ((DefaultListModel<String>) listModel).getElementAt(index).substring(1));
					else
						((DefaultListModel<String>) listModel).set(index, "O" + ((DefaultListModel<String>) listModel).getElementAt(index).substring(1));
					
				}
			}
		});
		
		setTitle(name);
		setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		add = new JButton("Add");
		ButtonListener addListener = new ButtonListener();
		add.addActionListener(addListener);
		
		remove = new JButton("Remove");
		ButtonListener removeListener = new ButtonListener();
		remove.addActionListener(removeListener);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(add);
		panel.add(remove);
		
		JPanel listPane = new JPanel();
		listPane.setLayout(new BorderLayout());
		
		list = new JScrollPane(this.user_list);
		list.setFont(list.getFont().deriveFont(12f));
		list.setFocusable(false);
		listPane.add(list, BorderLayout.CENTER);
		
		this.getContentPane().add(panel, BorderLayout.NORTH);
		this.getContentPane().add(listPane, BorderLayout.CENTER);
		
		setVisible(true);
	}

	public boolean add(String n) {
		if(n == null)
			return false;
		if (n.length() > 0) {
			li.add(n);
			System.out.println("\"" + n + "\"" + " was added to the list.");
			((DefaultListModel<String>) listModel).addElement("O   "+ n);
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
		} else {
			if(item >= li.size())
				return false;
			li.remove(item);
			((DefaultListModel<String>) listModel).remove(item);
		}
		System.out.println("Item successfully removed");
		return true;
	}

	public boolean swap(int x, int y) {
		System.out.println("What items would you like to swap?");
		if (doesExist(x) && doesExist(y)) {
			String temp = li.get(x);
			li.set(x, li.get(y));
			li.set(y, temp);
			((DefaultListModel<String>) listModel).set(x, listModel.getElementAt(y));
			((DefaultListModel<String>) listModel).set(y, temp);
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
			if(item >= li.size())
				return false;
			li.add(0, li.get(item));
			//remove(item + 1);
			
			String temp = ((DefaultListModel<String>) listModel).getElementAt(0);
			((DefaultListModel<String>) listModel).set(0, ((DefaultListModel<String>) listModel).getElementAt(item));
			((DefaultListModel<String>) listModel).set(item, temp);
			
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
				result += "  " + li.get(x) + "\n";
			}
		return result;
	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Add")) {
				String in = JOptionPane.showInputDialog("What would you like to add?");
				add(in);
			}
			else if(e.getActionCommand().equals("Remove")) {
				int in = Integer.parseInt(JOptionPane.showInputDialog("Which index would you like to remove?"));
				removeIndex(in);
			}
			else if(e.getActionCommand().equals("Swap")) {
				int in1 = Integer.parseInt(JOptionPane.showInputDialog("Enter first index to swap"));
				int in2 = Integer.parseInt(JOptionPane.showInputDialog("Enter second index to swap"));
				swap(in1, in2);
			}
			else if(e.getActionCommand().equals("Move To Top")) {
				int in = Integer.parseInt(JOptionPane.showInputDialog("Which index would you like to move to the top?"));
				moveToTop(in);
			}
		}
	}
	
	private class MyMouseAdaptor extends MouseInputAdapter {
        private boolean mouseDragging = false;
        private int dragSourceIndex;

        @Override
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                dragSourceIndex = user_list.getSelectedIndex();
                mouseDragging = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mouseDragging = false;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (mouseDragging) {
                int currentIndex = user_list.locationToIndex(e.getPoint());
                if (currentIndex != dragSourceIndex) {
                    int dragTargetIndex = user_list.getSelectedIndex();
                    String dragElement = ((DefaultListModel<String>) listModel).get(dragSourceIndex);
                    ((DefaultListModel<String>) listModel).remove(dragSourceIndex);
                    ((DefaultListModel<String>) listModel).add(dragTargetIndex, dragElement);
                    dragSourceIndex = currentIndex;
                }
            }
        }
    }


}
