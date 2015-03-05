package phoneInv;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Setting extends JPanel {
	private JTextField textField;
	private JTextField textField_2;
	private JTextField passwordField;

	/**
	 * Create the panel.
	 */
	public Setting() {
		setBackground(new Color(211, 211, 211));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Database Administrator Setting");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(70, 62, 535, 36);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(70, 145, 101, 28);
		add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(177, 151, 145, 20);
		add(textField);
		textField.setColumns(10);
		textField.setText(DDriver.userName);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(70, 218, 101, 28);
		add(lblPassword);
		
		JLabel lblNewLabel_2 = new JLabel("Server Name : Port");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(70, 310, 170, 28);
		add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setBounds(253, 316, 415, 20);
		add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(DDriver.serverName);
		
		JButton btnNewButton = new JButton("Apply");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DDriver.userName = textField.getText();
				System.out.println(DDriver.userName);
				DDriver.password = passwordField.getText();
				DDriver.serverName = textField_2.getText();
				DDriver.writeSetting();
				JOptionPane.showMessageDialog(null,
					    "Setting has been updated\nPlease restart you program to apply the changes",
					    "update Setting",
					    JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBackground(new Color(143, 188, 143));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(677, 402, 130, 23);
		add(btnNewButton);
		
		passwordField = new JTextField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(177, 224, 145, 20);
		add(passwordField);
		passwordField.setText(DDriver.password);
		passwordField.setColumns(10);

	}
}
