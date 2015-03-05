package phoneInv;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HeadPanel extends JPanel {
	
	
	
	JButton btnNewButton;
	JLabel lblHello;
	String userName;
	
	
	/**
	 * Create the panel.
	 */
	public HeadPanel() {
		setBackground(new Color(199, 21, 133));
		setLayout(null);
		
		JLabel lblP = new JLabel("Premiere Mobile");
		lblP.setFont(new Font("Tahoma", Font.BOLD, 37));
		lblP.setForeground(Color.WHITE);
		lblP.setBackground(Color.MAGENTA);
		lblP.setBounds(26, 21, 379, 50);
		add(lblP);
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MFrame.mainUser = null;
				refresh();
				MFrame.nextPanel(null,"logOut");
			}
		});
		btnNewButton.setIcon(new ImageIcon(HeadPanel.class.getResource("/phoneInv/Logout.png")));
		btnNewButton.setBounds(988, 46, 125, 50);
		
		
		userName = "";
		lblHello = new JLabel("Hello , "+userName);
		lblHello.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHello.setForeground(new Color(255, 255, 255));
		lblHello.setBounds(184, 69, 195, 29);
		
		refresh();
		
		
		

	}
	
	public void refresh() {
		if (MFrame.mainUser == null) {
			userName = "";
			lblHello.setText("");
			if(btnNewButton.isVisible()){
				btnNewButton.setVisible(false);
			}
		} else {
			add(btnNewButton);
			userName = MFrame.mainUser.getUserFirst();
			lblHello.setText("Hello , "+userName);
			add(lblHello);
			if(!btnNewButton.isVisible()){
				btnNewButton.setVisible(true);
			}
			
		}
	}
	
	
}
