/**
 * 
 */
package phoneInv;

import java.sql.SQLException;

/**
 * @author Mohamed
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DDriver connect = new DDriver();
		try {
			System.out.println(connect.getUsers().get(0).getUserLast());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
