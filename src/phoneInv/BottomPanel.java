package phoneInv;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BottomPanel extends JPanel {
	
	
	JButton userBt;
	JButton inventoryBt;
	JButton repairBt;
	JButton settingBt;
	JLabel connectLB;
	
	
	
	/**
	 * Create the panel.
	 */
	public BottomPanel() {
		setBackground(new Color(211, 211, 211));
		
        this.setBounds(0, 598, 1184, 60);
		
		this.setLayout(null);
		
		userBt = new JButton("Users");
		userBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MFrame.nextMainPanel("USERS");
			}
		});
		userBt.setBackground(new Color(169, 169, 169));
		userBt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userBt.setIcon(new ImageIcon(MFrame.class.getResource("/phoneInv/user.png")));
		userBt.setBounds(10, 0, 131, 60);
		
		
		inventoryBt = new JButton("Inventory");
		inventoryBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MFrame.nextMainPanel("INVENTORY");
			}
		});
		inventoryBt.setBackground(new Color(169, 169, 169));
		inventoryBt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		inventoryBt.setIcon(new ImageIcon(MFrame.class.getResource("/phoneInv/inventoery.png")));
		inventoryBt.setBounds(142, 0, 131, 60);
		
		
		repairBt = new JButton("Repair");
		repairBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MFrame.nextMainPanel("REPAIR");
			}
		});
		repairBt.setBackground(new Color(169, 169, 169));
		repairBt.setIcon(new ImageIcon(MFrame.class.getResource("/phoneInv/repair.png")));
		repairBt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		repairBt.setBounds(272, 0, 131, 60);
		
		
		settingBt = new JButton("Setting");
		settingBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MFrame.nextMainPanel("SETTING");
			}
		});
		settingBt.setIcon(new ImageIcon(MFrame.class.getResource("/phoneInv/Settings.png")));
		settingBt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		settingBt.setBackground(new Color(169, 169, 169));
		settingBt.setBounds(404, 0, 131, 60);
		
		
		connectLB = new JLabel("");
		
		
		
		connectLB.setFont(new Font("Tahoma", Font.BOLD, 15));
		connectLB.setBounds(1044, 30, 130, 19);
		add(connectLB);
		add(userBt);
		add(inventoryBt);
		add(repairBt);
		add(settingBt);
		refresh();
	}
	
	public void refresh() {
		
		if (DDriver.conn != null) {
			connectLB.setText("Connected");
			connectLB.setForeground(new Color(50, 205, 50));
		} else {
			connectLB.setText("Not Connected");
			connectLB.setForeground(new Color(220, 20, 60));
		}
		
		if (MFrame.mainUser != null) {
			
			inventoryBt.setEnabled(true);
			userBt.setEnabled(true);
			settingBt.setEnabled(true);
			repairBt.setEnabled(true);
			String role = MFrame.mainUser.getUserRole();
			switch (role) {
					case "TH": 
						inventoryBt.setEnabled(false);
					case "SL": 
						userBt.setEnabled(false);
						settingBt.setEnabled(false);
						break;
					case "MG":
						break;
			}
			if (role.equals("AD")){
				inventoryBt.setEnabled(false);
				repairBt.setEnabled(false);
				userBt.setEnabled(false);
				settingBt.setEnabled(true);
			}
			userBt.setVisible(true);
			inventoryBt.setVisible(true);
			repairBt.setVisible(true);
			settingBt.setVisible(true);
			revalidate();
			repaint();
			
			
		} else {
			userBt.setVisible(false);
			inventoryBt.setVisible(false);
			repairBt.setVisible(false);
			settingBt.setVisible(false);
			revalidate();
			repaint();
		}
	}
	

}
