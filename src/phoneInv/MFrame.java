package phoneInv;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;


public class MFrame extends JFrame {

	private static JPanel contentPane;
	private static HeadPanel topPanel;
	private static BottomPanel bottomPanel;
	private static Setting setPanel;
	private static RepairScreen repPanel;
	private static JPanel mainPanel;
	public static User mainUser;
	public String role;
    public static JPanel panel;
	/**
	 * Create the frame.
	 */
	public MFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		setLocation(0, 0);
		this.setTitle("Premiere Mobile");
		mainUser = null;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		topPanel = new HeadPanel();
		topPanel.setBounds(0, 0, 1184, 100);
		contentPane.add(topPanel);
		
		
		bottomPanel = new BottomPanel();
		contentPane.add(bottomPanel);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(211, 211, 211));
		mainPanel.setBounds(0, 100, 1184, 498);
		contentPane.add(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		
		
        if(mainUser == null) {
        	panel = new LoginScreen();
    		mainPanel.add(panel, BorderLayout.CENTER);
		}
		
		setVisible(true);
	}
	
	public static void nextMainPanel (String page) {
		if (page.equals("USER")) {
			mainPanel.removeAll();
			panel = new LoginScreen();
    		mainPanel.add(panel, BorderLayout.CENTER);
    		mainPanel.revalidate();
    		mainPanel.repaint();
			bottomPanel.refresh();
			
		} else if ( page.equals("INVENTORY")) {
			mainPanel.removeAll();
			
			mainPanel.revalidate();
    		mainPanel.repaint();
    		topPanel.refresh();
    		bottomPanel.refresh();
			
			
		}else if ( page.equals("REPAIR")) {
			
			mainPanel.removeAll();
			repPanel = new RepairScreen();
			mainPanel.add(repPanel, BorderLayout.CENTER);
			mainPanel.revalidate();
    		mainPanel.repaint();
    		//topPanel.refresh();
    		bottomPanel.refresh();
			
			
		}else if ( page.equals("SETTING")) {
			
			mainPanel.removeAll();
			setPanel = new Setting();
			mainPanel.add(setPanel, BorderLayout.CENTER);
			mainPanel.revalidate();
    		mainPanel.repaint();
    		//topPanel.refresh();
    		bottomPanel.refresh();
			
			
		}
	}
	
	
	public static void nextPanel (User myUser, String page) {
		mainUser = myUser;
		
		if (myUser == null && page.equals("logOut")) {
			mainPanel.removeAll();
			panel = new LoginScreen();
    		mainPanel.add(panel, BorderLayout.CENTER);
    		mainPanel.revalidate();
    		mainPanel.repaint();
			bottomPanel.refresh();
			
		} else if (myUser.getUserRole().equals("AD") && page.equals("ADMIN")) {
			
			mainPanel.removeAll();
			mainPanel.revalidate();
    		mainPanel.repaint();
    		topPanel.refresh();
    		bottomPanel.refresh();
			
			
		}else if (myUser.getUserRole().equals("MG")) {
			//ManagerScreen myManager = new ManagerScreen();
			mainPanel.removeAll();
			mainPanel.revalidate();
    		mainPanel.repaint();
    		topPanel.refresh();
    		bottomPanel.refresh();
			
			
		} else if (myUser.getUserRole().equals("SL")) {
			
			mainPanel.removeAll();
			mainPanel.revalidate();
    		mainPanel.repaint();
    		topPanel.refresh();
    		bottomPanel.refresh();
			
			
		} else if (myUser.getUserRole().equals("TH")) {
			
			mainPanel.removeAll();
			mainPanel.revalidate();
    		mainPanel.repaint();
    		topPanel.refresh();
    		bottomPanel.refresh();
			
			
		}
		
		
		
		
    }
}





