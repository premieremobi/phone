/**
 * 
 */
package phoneInv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;





/**
 * @author Mohamed
 *
 */
public class DDriver {
	private static String userName = "mohamed";
	private static String password = "ms020202";
	private static String serverName = "192.168.2.18:3306";
	private static Connection conn;
	private List<User> list;

	public static void createConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
				+ serverName + "/", connectionProps);

		System.out.println("Connected to database");
	}
	
	
	
	public List<User> getUsers() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select userPin, userFirst, userLast, userRole, userState "
				+ "from mydb.user ";

		list = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String pin = rs.getString("userPin");
				int apin = Integer.parseInt(pin);
				String first = rs.getString("userFirst");
				String last = rs.getString("userLast");
				String role = rs.getString("userRole");
				String stats = rs.getString("userState");
				User theUser = new User(apin,first,last,role, stats);
				list.add(theUser);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return list;
	}
	
}
