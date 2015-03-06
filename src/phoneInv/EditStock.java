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

public class EditStock extends JPanel {
	
	private JTextField textField_Price;
	private JTextField textField_Imei;
	JComboBox comboBrand;
	JComboBox comboModel;
	JComboBox comboCondition;
	String [] brandList = DDriver.getlist(DDriver.brandList);
	String [] conditionL = {"NEW","USED"};
	String [] aval = {"In stock", "Sold"};
	String [] modelList;
	private JTextField textField_Model;
	JLabel label;
	JComboBox comboStock;

	/**
	 * Create the panel.
	 */
	public EditStock(final Stock selected) {
		setLayout(null);
		{
			JLabel lblRepairTicket = new JLabel("S/N : ");
			lblRepairTicket.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblRepairTicket.setBounds(23, 22, 87, 22);
			add(lblRepairTicket);
		}
		{
			label = new JLabel(String.valueOf(selected.getsNum()));
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
			JLabel lblPrice = new JLabel("Price");
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPrice.setBounds(503, 55, 55, 22);
			add(lblPrice);
		}
		{
			textField_Imei = new JTextField();
			textField_Imei.setColumns(15);
			textField_Imei.setBounds(81, 129, 229, 23);
			textField_Imei.setText(selected.getPhoneImei());
			add(textField_Imei);
		}
		
		textField_Model = new JTextField();
		textField_Model.setColumns(10);
		textField_Model.setBounds(82, 97, 116, 23);
		textField_Model.setText(selected.getPhoneModel());
		add(textField_Model);
		
		comboBrand = new JComboBox(brandList);
		comboBrand.setBounds(82, 58, 119, 20);
		comboBrand.setSelectedItem(selected.getPhoneBrand());
		add(comboBrand);
		
		
		
		
		comboCondition = new JComboBox(conditionL);
		comboCondition.setBounds(578, 103, 119, 20);
		comboCondition.setSelectedItem(selected.getPhoneCondition());
		add(comboCondition);

		
		{
			JButton btnNewButton = new JButton("Update");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String brand = brandList[comboBrand.getSelectedIndex()];
					String model = textField_Model.getText();
					String condition = conditionL[comboCondition.getSelectedIndex()];
					
					String imei = textField_Imei.getText();
					if (imei.length() == 15) {
						double price = Double.parseDouble(textField_Price.getText());
						int i = 0;
						if (comboStock.getSelectedItem().equals("In stock")) {
							i = 1;
						}
						DDriver.updateStock(DDriver.stockList.indexOf(selected),imei,condition,price,i,brand,model);
						JOptionPane.showMessageDialog(null,
							    "Phone has been updated !",
							    "Update Stock",
							    JOptionPane.INFORMATION_MESSAGE);
						textField_Model.setText("");
						textField_Imei.setText("");
						textField_Price.setText("");
						label.setText("");
						
					} else {
						JOptionPane.showMessageDialog(null,
							    "IEMI has to be 15 digit",
							    "Not valid IEMI",
							    JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnNewButton.setBounds(639, 257, 131, 23);
			add(btnNewButton);
		}
		
		JLabel lblCondition = new JLabel("Condition");
		lblCondition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCondition.setBounds(503, 100, 78, 22);
		add(lblCondition);
		
		JLabel lblAvalibality = new JLabel("Stock");
		lblAvalibality.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAvalibality.setBounds(503, 157, 78, 22);
		add(lblAvalibality);
		
		comboStock = new JComboBox(aval);
		comboStock.setBounds(578, 160, 119, 20);
		add(comboStock);

	}
}
