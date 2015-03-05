package phoneInv;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RepairDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			RepairDialog dialog = new RepairDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public RepairDialog() {
		
		setBounds(100, 100, 811, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblRepairTicket = new JLabel("Repair ticket");
			lblRepairTicket.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblRepairTicket.setBounds(23, 22, 87, 22);
			contentPanel.add(lblRepairTicket);
		}
		{
			JLabel label = new JLabel("");
			label.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label.setBounds(114, 22, 87, 20);
			contentPanel.add(label);
		}
		{
			JLabel lblBrand = new JLabel("Brand");
			lblBrand.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblBrand.setBounds(23, 55, 55, 22);
			contentPanel.add(lblBrand);
		}
		{
			textField_Price = new JTextField();
			textField_Price.setBounds(541, 57, 116, 23);
			contentPanel.add(textField_Price);
			textField_Price.setColumns(10);
		}
		{
			JLabel lblModel = new JLabel("Model");
			lblModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblModel.setBounds(23, 94, 55, 22);
			contentPanel.add(lblModel);
		}
		{
			JLabel lblImei = new JLabel("IMEI");
			lblImei.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblImei.setBounds(23, 127, 55, 22);
			contentPanel.add(lblImei);
		}
		{
			JLabel lblService = new JLabel("Service");
			lblService.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblService.setBounds(259, 55, 55, 22);
			contentPanel.add(lblService);
		}
		{
			JLabel lblPrice = new JLabel("Price");
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPrice.setBounds(503, 55, 55, 22);
			contentPanel.add(lblPrice);
		}
		{
			JLabel lblStats = new JLabel("Status");
			lblStats.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblStats.setBounds(259, 94, 55, 22);
			contentPanel.add(lblStats);
		}
		{
			JLabel lblLocation = new JLabel("Location");
			lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblLocation.setBounds(503, 94, 55, 22);
			contentPanel.add(lblLocation);
		}
		{
			textField_Imei = new JTextField();
			textField_Imei.setColumns(10);
			textField_Imei.setBounds(85, 130, 229, 23);
			contentPanel.add(textField_Imei);
		}
		{
			JLabel lblComment = new JLabel("Comment");
			lblComment.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblComment.setBounds(384, 127, 87, 22);
			contentPanel.add(lblComment);
		}
		{
			textArea = new JTextArea();
			textArea.setBounds(394, 160, 332, 57);
			contentPanel.add(textArea);
		}
		
		comboBrand = new JComboBox(brandList);
		comboBrand.setBounds(82, 58, 119, 20);
		contentPanel.add(comboBrand);
		
		
		comboStats = new JComboBox(stats);
		comboStats.setBounds(315, 97, 119, 20);
		contentPanel.add(comboStats);
		
		comboLoc = new JComboBox(location);
		comboLoc.setBounds(568, 97, 64, 20);
		contentPanel.add(comboLoc);
		
		//service = {"Screen Serives","LCD Replace","LCD+Screen","Housing","Dignostic"};
		comboService = new JComboBox(service);
		comboService.setBounds(324, 58, 147, 20);
		contentPanel.add(comboService);
		{
			textField_model = new JTextField();
			textField_model.setColumns(10);
			textField_model.setBounds(85, 97, 116, 23);
			contentPanel.add(textField_model);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
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
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
