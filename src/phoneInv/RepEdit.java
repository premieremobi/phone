package phoneInv;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RepEdit extends JPanel {
	
	private JTextField textField_Price;
	private JTextField textField_Imei;
	private JTextField textField_model;
	private JTextArea textArea;
	JLabel label;
	JLabel label_Loc;
	JComboBox comboBrand;
	JComboBox comboStats;
	JComboBox comboService;
	String [] service = {"Screen Service","LCD Replace","LCD+Screen","Housing","Dignostic","Unlocking"};
	String [] location = DDriver.getlist(DDriver.locList);
	String [] brandList = DDriver.getlist(DDriver.brandList);
	String [] stats = {"Ready", "Waitting Parts", "Inprogress"};

	/**
	 * Create the panel.
	 */
	public RepEdit(final Repair selected) {
		
		setLayout(null);
		{
			JLabel lblRepairTicket = new JLabel("Repair ticket");
			lblRepairTicket.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblRepairTicket.setBounds(23, 22, 87, 22);
			add(lblRepairTicket);
		}
		{
			label = new JLabel("");
			label.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label.setBounds(114, 22, 87, 20);
			label.setText(String.valueOf(selected.getRepairId()));
			add(label);
		}
		{
			JLabel lblBrand = new JLabel("Brand");
			lblBrand.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblBrand.setBounds(23, 55, 55, 22);
			add(lblBrand);
		}
		{
			textField_Price = new JTextField();
			textField_Price.setBounds(541, 57, 116, 23);
			textField_Price.setText(String.valueOf(selected.getPhonePrice()));
			add(textField_Price);
			textField_Price.setColumns(10);
		}
		{
			JLabel lblModel = new JLabel("Model");
			lblModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblModel.setBounds(23, 94, 55, 22);
			add(lblModel);
		}
		{
			JLabel lblImei = new JLabel("IMEI");
			lblImei.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblImei.setBounds(23, 127, 55, 22);
			add(lblImei);
		}
		{
			JLabel lblService = new JLabel("Service");
			lblService.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblService.setBounds(259, 55, 55, 22);
			add(lblService);
		}
		{
			JLabel lblPrice = new JLabel("Price");
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPrice.setBounds(503, 55, 55, 22);
			add(lblPrice);
		}
		{
			JLabel lblStats = new JLabel("Status");
			lblStats.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblStats.setBounds(259, 94, 55, 22);
			add(lblStats);
		}
		{
			JLabel lblLocation = new JLabel("Location");
			lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblLocation.setBounds(503, 94, 55, 22);
			add(lblLocation);
		}
		{
			textField_Imei = new JTextField();
			textField_Imei.setColumns(10);
			textField_Imei.setBounds(85, 130, 229, 23);
			textField_Imei.setText(selected.getPhoneImei());
			add(textField_Imei);
		}
		{
			JLabel lblComment = new JLabel("Comment");
			lblComment.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblComment.setBounds(384, 127, 87, 22);
			add(lblComment);
		}
		{
			textArea = new JTextArea();
			textArea.setBounds(394, 160, 332, 57);
			textArea.setText(selected.getRepairComment());
			add(textArea);
		}
		
		comboBrand = new JComboBox(brandList);
		comboBrand.setBounds(82, 58, 119, 20);
		comboBrand.setSelectedItem(selected.getPhoneBrand());
		add(comboBrand);
		
		
		comboStats = new JComboBox(stats);
		comboStats.setBounds(315, 97, 119, 20);
		comboStats.setSelectedItem(selected.getRepairStats());
		add(comboStats);

		
		
		comboService = new JComboBox(service);
		comboService.setBounds(324, 58, 147, 20);
		comboService.setSelectedItem(selected.getRepairService());
		add(comboService);
		{
			textField_model = new JTextField();
			textField_model.setColumns(10);
			textField_model.setBounds(85, 97, 116, 23);
			textField_model.setText(selected.getPhoneModel());
			add(textField_model);
		}
		{
			JButton btnNewButton = new JButton("Update Repair");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String brand = brandList[comboBrand.getSelectedIndex()];
					String model = textField_model.getText();
					String imei = textField_Imei.getText();
					if (imei.length() == 15) {
						String serv = service [comboService.getSelectedIndex()];
						double price = Double.parseDouble(textField_Price.getText());
						String stauts = stats[comboStats.getSelectedIndex()];
						String comment = textArea.getText();
						String userPin = MFrame.mainUser.getUserPin();
						DDriver.updateRepair(DDriver.repairList.indexOf(selected),brand,model,imei,serv,price,stauts,comment,userPin,selected.getRepairLocation());
						
						
						
						JOptionPane.showMessageDialog(null,
							    "Repair has been updated ! ",
							    "Update Repair ticket",
							    JOptionPane.INFORMATION_MESSAGE);
						textField_model.setText("");
						textField_Imei.setText("");
						textField_Price.setText("");
						textArea.setText("");
						label.setText("");
						label_Loc.setText("");
					} else {
						JOptionPane.showMessageDialog(null,
							    "IEMI has to be 15 digit",
							    "Not valid IEMI",
							    JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnNewButton.setBounds(639, 257, 129, 23);
			add(btnNewButton);
		}
		{
			label_Loc = new JLabel("");
			label_Loc.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_Loc.setBounds(570, 91, 87, 20);
			label_Loc.setText(selected.getRepairLocation());
			add(label_Loc);
		}

	}


}
