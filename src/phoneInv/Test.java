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
		User me = new User("2222","KK","DOD","SL","Active");
		connect.addUser(me);
		System.out.println(connect.userList.get(2).getUserPin());

	}

}
