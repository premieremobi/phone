package phoneInv;

import java.awt.EventQueue;





public class Driver {
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DDriver dataDriver = new DDriver();
					MFrame frame = new MFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	

}
