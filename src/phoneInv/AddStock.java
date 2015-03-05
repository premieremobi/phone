package phoneInv;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ComboBoxModel;

public class AddStock extends JPanel {
	
	private JTextField textField_Price;
	private JTextField textField_Imei;
	JComboBox comboBrand;
	JComboBox comboModel;
	JComboBox comboCondition;
	String [] brandList = DDriver.getlist(DDriver.brandList);
	String [] conditionL = {"NEW","USED"};
	String [] modelList;
	private JTextField textField_Model;
	/**
	 * Create the panel.
	 */
	public AddStock() {
		setLayout(null);
		{
			JLabel lblRepairTicket = new JLabel("S/N");
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
			JLabel lblPrice = new JLabel("Price");
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPrice.setBounds(503, 55, 55, 22);
			add(lblPrice);
		}
		{
			textField_Imei = new JTextField();
			textField_Imei.setColumns(15);
			textField_Imei.setBounds(81, 129, 229, 23);
			add(textField_Imei);
		}
		
		textField_Model = new JTextField();
		textField_Model.setColumns(10);
		textField_Model.setBounds(82, 97, 116, 23);
		add(textField_Model);
		
		comboBrand = new JComboBox(brandList);
		comboBrand.setBounds(82, 58, 119, 20);
		add(comboBrand);
		
		
		
		
		comboCondition = new JComboBox(conditionL);
		comboCondition.setBounds(578, 103, 119, 20);
		add(comboCondition);

		
		{
			JButton btnNewButton = new JButton("Add To Stock");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String brand = brandList[comboBrand.getSelectedIndex()];
					String model = textField_Model.getText();
					String condition = conditionL[comboCondition.getSelectedIndex()];
					String imei = textField_Imei.getText();
					if (imei.length() == 15) {
						double price = Double.parseDouble(textField_Price.getText());
						DDriver.addStock(new Stock(imei,condition,price,1,brand,model));
						JOptionPane.showMessageDialog(null,
							    "Phone has been added !",
							    "Add Stock",
							    JOptionPane.INFORMATION_MESSAGE);
						textField_Model.setText("");
						textField_Imei.setText("");
						textField_Price.setText("");
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

	}
}
