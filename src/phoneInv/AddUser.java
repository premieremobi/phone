package phoneInv;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddUser extends JPanel {
	
	private JTextField textField_Pin;
	private JTextField textField_Last;
	JComboBox comboStatus;
	JComboBox comboRole;
	String [] statusList = {"Active","Not Active"};
	String [] rolelist = {"MG","SL","TH"};
	private JTextField textField_First;

	/**
	 * Create the panel.
	 */
	public AddUser() {
		setLayout(null);
		{
			JLabel lblRepairTicket = new JLabel("New User Info");
			lblRepairTicket.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblRepairTicket.setBounds(23, 22, 110, 22);
			add(lblRepairTicket);
		}
		{
			JLabel lblBrand = new JLabel("User Status");
			lblBrand.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblBrand.setBounds(500, 178, 84, 22);
			add(lblBrand);
		}
		{
			textField_Pin = new JTextField();
			textField_Pin.setBounds(103, 177, 116, 23);
			add(textField_Pin);
			textField_Pin.setColumns(10);
		}
		{
			JLabel lblModel = new JLabel("Frist Name");
			lblModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblModel.setBounds(23, 94, 84, 22);
			add(lblModel);
		}
		{
			JLabel lblImei = new JLabel("Last Name");
			lblImei.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblImei.setBounds(23, 127, 80, 22);
			add(lblImei);
		}
		{
			JLabel lblPrice = new JLabel("User Pin");
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPrice.setBounds(23, 175, 55, 22);
			add(lblPrice);
		}
		{
			textField_Last = new JTextField();
			textField_Last.setColumns(15);
			textField_Last.setBounds(103, 127, 140, 23);
			add(textField_Last);
		}
		
		textField_First = new JTextField();
		textField_First.setColumns(10);
		textField_First.setBounds(103, 96, 140, 23);
		add(textField_First);
		
		comboStatus = new JComboBox(statusList);
		comboStatus.setBounds(577, 181, 110, 20);
		add(comboStatus);
		
		
		
		
		comboRole = new JComboBox(rolelist);
		comboRole.setBounds(358, 178, 66, 20);
		add(comboRole);

		
		{
			JButton btnNewButton = new JButton("Add User");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String stat = statusList[comboStatus.getSelectedIndex()];
					String first = textField_First.getText();
					String role = rolelist[comboRole.getSelectedIndex()];
					String last = textField_Last.getText();
					String pin = textField_Pin.getText();
					if (first.length() >0 && last.length() > 0 && pin.length() > 0) {
						DDriver.addUser(new User(pin,first,last,role,stat));
						JOptionPane.showMessageDialog(null,
							    "User has been added !",
							    "Add User",
							    JOptionPane.INFORMATION_MESSAGE);
						textField_First.setText("");
						textField_Last.setText("");
						textField_Pin.setText("");
					} else {
						JOptionPane.showMessageDialog(null,
							    "Please fill all required fields!",
							    "Requrired fields",
							    JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnNewButton.setBounds(639, 257, 131, 23);
			add(btnNewButton);
		}
		
		JLabel lblCondition = new JLabel("User Role");
		lblCondition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCondition.setBounds(283, 178, 78, 22);
		add(lblCondition);

	}

}
