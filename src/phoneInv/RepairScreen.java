package phoneInv;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;



public class RepairScreen extends JPanel {
	private JTextField textField;
	private JTable table;
	public Object data [][];
	public JScrollPane scrollPane;
    public String [] columnNames = {"Ticket","Brand","Model","IMEI","Service","Price","Status","Comment","Emp","Location"};
    public Repair selected;
    RepAdd addScreen;
    RepEdit editScreen;

	/**
	 * Create the panel.
	 */
	public RepairScreen() {
		setBackground(new Color(211, 211, 211));
		setLayout(null);
		selected = null;
		JLabel lblRepair = new JLabel("Repair Ticket Number");
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
						    "Please enter valid repair ticket number!",
						    "Empty Field",
						    JOptionPane.ERROR_MESSAGE);
				} else {
					List<Repair> myFiltered = DDriver.getFilterRepair(Integer.parseInt(textField.getText()));
					if (!myFiltered.isEmpty()) {
						updateData(myFiltered);
						selected = myFiltered.get(0);
						table = new JTable(data,columnNames);
						table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						scrollPane.setViewportView(table);
						scrollPane.revalidate();
						scrollPane.repaint();
					} else {
						JOptionPane.showMessageDialog(null,
							    "Please enter valid repair ticket number!",
							    "Not vaild ticket number",
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
		
		updateData(DDriver.repairList);
		table = new JTable(data,columnNames);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Add Repair");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addScreen = new RepAdd();
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
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedrow = -1;
				int selectedCol = 0;
				selectedrow = table.getSelectedRow();
				if (selectedrow > -1 && selectedCol > -1 ) {
					selected = DDriver.getRepair(((Integer)table.getValueAt(selectedrow, selectedCol)));
					if (selected != null) {
						editScreen = new RepEdit(selected);
						scrollPane.setViewportView(editScreen);
						scrollPane.revalidate();
						scrollPane.repaint();
					}
				} else {
					JOptionPane.showMessageDialog(null,
						     "No Repair Ticket Selected !",
						    "Please Select Repair Ticket",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		btnNewButton_2.setForeground(new Color(0, 0, 205));
		btnNewButton_2.setBackground(new Color(105, 105, 105));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(186, 451, 89, 23);
		add(btnNewButton_2);
		
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
		
		JButton btnClearRepairTicket = new JButton("Clear Repair Ticket");
		btnClearRepairTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedrow = -1;
				int selectedCol = 0;
				selectedrow = table.getSelectedRow();
				if (selectedrow > -1 && selectedCol > -1 ) {
					selected = DDriver.getRepair(((Integer)table.getValueAt(selectedrow, selectedCol)));
					if (selected != null) {
						if (selected.getRepairStats().equals("Ready")){
							if (selected.getRepairLocation().equals("")) {
								JOptionPane.showMessageDialog(null,
									     "Repair Ticket  already cleared !",
									    "Repair Ticket",
									    JOptionPane.ERROR_MESSAGE);
							} else {
								DDriver.updateLocation(selected.getRepairLocation(), 0);
								DDriver.updateRepair(DDriver.repairList.indexOf(selected),selected.getPhoneBrand(),selected.getPhoneModel(),selected.getPhoneImei()
										,selected.getRepairService(),selected.getPhonePrice(),selected.getRepairStats(),selected.getRepairComment(),selected.getRepairUserPin(),"");
								JOptionPane.showMessageDialog(null,
									     "Repair Ticket has been cleard !",
									    "Repair Ticket",
									    JOptionPane.INFORMATION_MESSAGE);
								updateScreen();
							}
							
						} else {
							JOptionPane.showMessageDialog(null,
								     "Repair Ticket not ready to be cleared !",
								    "Repair Ticket",
								    JOptionPane.ERROR_MESSAGE);
						}
						
					}
				} else {
					JOptionPane.showMessageDialog(null,
						     "No Repair Ticket Selected !",
						    "Please Select Repair Ticket",
						    JOptionPane.ERROR_MESSAGE);
				} 
			}
		});
		btnClearRepairTicket.setForeground(new Color(0, 255, 127));
		btnClearRepairTicket.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClearRepairTicket.setBackground(SystemColor.controlDkShadow);
		btnClearRepairTicket.setBounds(837, 451, 182, 23);
		add(btnClearRepairTicket);

	}
	
	
	public void updateData(List<Repair> mylist){
		data = new Object[mylist.size()][columnNames.length];
		for (int i=0; i<mylist.size(); i++) {
			data[i][0] = mylist.get(i).getRepairId();
			data[i][1] = mylist.get(i).getPhoneBrand();
			data[i][2] = mylist.get(i).getPhoneModel();
			data[i][3] = mylist.get(i).getPhoneImei();
			data[i][4] = mylist.get(i).getRepairService();
			data[i][5] = mylist.get(i).getPhonePrice();
			data[i][6] = mylist.get(i).getRepairStats();
		    data[i][7] = mylist.get(i).getRepairComment();
		    data[i][8] = mylist.get(i).getRepairUserPin();
		    data[i][9] = mylist.get(i).getRepairLocation();
		}
	}
	
	public void updateScreen() {
		DDriver.updateData();
		updateData(DDriver.repairList);
		table = new JTable(data,columnNames);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setViewportView(table);
		scrollPane.revalidate();
		scrollPane.repaint();
	}
}
