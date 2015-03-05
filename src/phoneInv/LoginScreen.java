package phoneInv;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginScreen extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public LoginScreen() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Pin");
		lblNewLabel.setBackground(new Color(169, 169, 169));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(693, 100, 102, 30);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBackground(new Color(173, 216, 230));
		textField.setBounds(805, 100, 65, 30);
		add(textField);
		textField.setColumns(4);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pin = textField.getText();
				if (pin.length() == 4) {
					if(pin.equals("ADMN")){
						User myUser = new User("", "ADMIN", "", "AD", "");
						MFrame.nextPanel(myUser,"ADMIN");
					} else {
						User myUser = DDriver.checkUser(pin);
						
						if (myUser != null){
							MFrame.nextPanel(myUser,"mainScreen");
						} else {
							JOptionPane.showMessageDialog(null,
								    "Please enter valid pin",
								    "Pin Not valid",
								    JOptionPane.ERROR_MESSAGE);
					          }
					}
					
				
				} else {
					JOptionPane.showMessageDialog(null,
						    "Please enter valid pin",
						    "Pin Not valid",
						    JOptionPane.ERROR_MESSAGE);
			          }
			}
		});
		btnNewButton.setBackground(new Color(143, 188, 143));
		btnNewButton.setBounds(802, 155, 89, 36);
		add(btnNewButton);

	}
}
