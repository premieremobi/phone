package phoneInv;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RepAdd extends JPanel {

	
	private JTextField textField_Price;
	private JTextField textField_Imei;
	private JTextField textField_model;
	private JTextArea textArea;
	JComboBox comboBrand;
	JComboBox comboStats;
	JComboBox comboLoc;
	JComboBox comboService;
	String [] service = {"Screen Service","LCD Replace","LCD+Screen","Housing","Dignostic"};
	String [] location = DDriver.getlist(DDriver.locList);
	String [] brandList = DDriver.getlist(DDriver.brandList);
	String [] stats = {"Ready", "Waitting Parts", "Inprogress"};
	/**
	 * Create the panel.
	 */
	public RepAdd() {
		setLayout(null);
		{
			JLabel lblRepairTicket = new JLabel("Repair ticket");
			lblRepairTicket.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblRepairTicket.setBounds(23, 22, 87, 22);
			add(lblRepairTicket);
		}
		{
			JLabel label = new JLabel("");
			label.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label.setBounds(114, 22, 87, 20);
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
			add(textArea);
		}
		
		comboBrand = new JComboBox(brandList);
		comboBrand.setBounds(82, 58, 119, 20);
		add(comboBrand);
		
		
		comboStats = new JComboBox(stats);
		comboStats.setBounds(315, 97, 119, 20);
		add(comboStats);
		
		comboLoc = new JComboBox(location);
		comboLoc.setBounds(568, 97, 64, 20);
		add(comboLoc);
		
		//service = {"Screen Serives","LCD Replace","LCD+Screen","Housing","Dignostic"};
		comboService = new JComboBox(service);
		comboService.setBounds(324, 58, 147, 20);
		add(comboService);
		{
			textField_model = new JTextField();
			textField_model.setColumns(10);
			textField_model.setBounds(85, 97, 116, 23);
			add(textField_model);
		}
		{
			JButton btnNewButton = new JButton("Add Repair");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String brand = brandList[comboBrand.getSelectedIndex()];
					String model = textField_model.getText();
					String imei = textField_Imei.getText();
					String serv = service [comboService.getSelectedIndex()];
					double price = Double.parseDouble(textField_Price.getText());
					String stauts = stats[comboStats.getSelectedIndex()];
					String comment = textArea.getText();
					String userPin = MFrame.mainUser.getUserPin();
					String loc = location[comboLoc.getSelectedIndex()];
					DDriver.addRepair(new Repair(brand,model,imei,serv,price,stauts,comment,userPin,loc));
					DDriver.updateLocation(loc);
					int id = DDriver.getRepairID(imei);
					JOptionPane.showMessageDialog(null,
						    "Repair ticket number is "+id,
						    "Add Repair ticket number",
						    JOptionPane.INFORMATION_MESSAGE);
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnNewButton.setBounds(639, 257, 116, 23);
			add(btnNewButton);
		}

	}

}
