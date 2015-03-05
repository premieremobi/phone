package phoneInv;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class UsersScreen extends JPanel {

	private JTextField textField;
	private JTable table;
	public Object data [][];
	public JScrollPane scrollPane;
    public String [] columnNames = {"User Pin","Frist","Last","Role","status"};
    public User selected;
    AddUser addScreen;
	/**
	 * Create the panel.
	 */
	public UsersScreen() {
		setBackground(new Color(211, 211, 211));
		setLayout(null);
		selected = null;
		JLabel lblRepair = new JLabel("Search by Last Name");
		lblRepair.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRepair.setBounds(46, 35, 161, 24);
		add(lblRepair);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setBounds(205, 39, 144, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Find");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
						    "Please enter a phone Brand!",
						    "Empty Field",
						    JOptionPane.ERROR_MESSAGE);
				} else {
					List<User> myFiltered = DDriver.getFilterUsers(textField.getText());
					if (!myFiltered.isEmpty()) {
						updateData(myFiltered);
						table = new JTable(data,columnNames);
						scrollPane.setViewportView(table);
						scrollPane.revalidate();
						scrollPane.repaint();
					} else {
						JOptionPane.showMessageDialog(null,
							     "No user found !",
							    "Not Found",
							    JOptionPane.ERROR_MESSAGE);
					}
					
				}
				textField.setText("");
				
			}
		});
		btnNewButton.setForeground(new Color(255, 20, 147));
		btnNewButton.setBackground(new Color(105, 105, 105));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(422, 38, 89, 23);
		add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 95, 1084, 345);
		add(scrollPane);
		
		updateData(DDriver.userList);
		table = new JTable(data,columnNames);
		scrollPane.setViewportView(table);
		
		if (MFrame.mainUser.getUserRole().equals("MG")){
			JButton btnNewButton_1 = new JButton("Add User");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addScreen = new AddUser();
					scrollPane.setViewportView(addScreen);
					scrollPane.revalidate();
					scrollPane.repaint();
				}
			});
			btnNewButton_1.setForeground(new Color(0, 255, 127));
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_1.setBackground(new Color(105, 105, 105));
			btnNewButton_1.setBounds(46, 451, 130, 23);
			add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("Edit");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// add edit function
				}
			});
			btnNewButton_2.setForeground(new Color(0, 0, 205));
			btnNewButton_2.setBackground(new Color(105, 105, 105));
			btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_2.setBounds(186, 451, 89, 23);
			add(btnNewButton_2);
		}
		
		
		
		
		JButton btnNewButton_3 = new JButton("Refresh");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateScreen();
			}
		});
		btnNewButton_3.setBackground(new Color(105, 105, 105));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setForeground(new Color(255, 0, 0));
		btnNewButton_3.setBounds(295, 451, 118, 23);
		add(btnNewButton_3);
  
	}
	
	public void updateData(List<User> mylist){
		data = new Object[mylist.size()][columnNames.length];
		for (int i=0; i<mylist.size(); i++) {
			data[i][0] = mylist.get(i).getUserPin();
			data[i][1] = mylist.get(i).getUserFirst();
			data[i][2] = mylist.get(i).getUserLast();
			data[i][3] = mylist.get(i).getUserRole();
			data[i][4] = mylist.get(i).getUserState();
		}
	}
	
	public void updateScreen() {
		DDriver.updateData();
		updateData(DDriver.userList);
		table = new JTable(data,columnNames);
		scrollPane.setViewportView(table);
		scrollPane.revalidate();
		scrollPane.repaint();
	}

}
