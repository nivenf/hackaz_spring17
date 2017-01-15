package hackaz_spring17;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String> li;
	String name;
	JTextField field;
	JButton add;
	JButton remove;
	JButton help;
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
				@SuppressWarnings("rawtypes")
				JList listtemp = (JList)evt.getSource();
				if(evt.getClickCount() == 2) {
					int index = listtemp.locationToIndex(evt.getPoint());
					String r = ((DefaultListModel<String>) listModel).getElementAt(index);
					if(r.charAt(0) == 'O')
						((DefaultListModel<String>) listModel).set(index, "X" + ((DefaultListModel<String>) listModel).getElementAt(index).substring(1));
					else
						((DefaultListModel<String>) listModel).set(index, "O" + ((DefaultListModel<String>) listModel).getElementAt(index).substring(1));
					
				}
				else if(evt.getClickCount() == 3) {
					int index = listtemp.locationToIndex(evt.getPoint());
					removeIndex(index);
				}
			}
		});
		
		setTitle(name);
		setSize(800, 1000);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		add = new JButton("Add");
		ButtonListener addListener = new ButtonListener();
		add.addActionListener(addListener);
		
		remove = new JButton("Remove");
		ButtonListener removeListener = new ButtonListener();
		remove.addActionListener(removeListener);
		
		help = new JButton("Help");
		ButtonListener helpListener = new ButtonListener();
		help.addActionListener(helpListener);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(add);
		panel.add(remove);
		panel.add(help);
		
		JPanel listPane = new JPanel();
		listPane.setLayout(new BorderLayout());
		
		list = new JScrollPane(this.user_list);
		list.setFont(list.getFont().deriveFont(100f));
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
			else if(e.getActionCommand().equals("Help")) {
				JOptionPane.showMessageDialog(null, "Usage: \nDouble click to check/uncheck\nTriple click to remove");
			}
		}
	}

	// Drag and drop for the JList
	// Credit goes to Jan Taccis
	// Source: http://stackoverflow.com/questions/3804361/how-to-enable-drag-and-drop-inside-jlist
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
