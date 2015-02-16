/**
 * 
 */
package phoneInv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
	public static List<User> userList;
	public static List<Stock> stockList;
	private static List<Repair> repairList;
	public static List<String> locList;
	public final static  String DATE_FORMAT_NOW = "yyyy-MM-dd";
	
	

	public static void createConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
				+ serverName + "/", connectionProps);

		System.out.println("Connected to database");
	}
	
	public DDriver(){
		try {
			getUsers();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	/**
	 * To get today date in our format
	 * @return date as string 
	 */
	public static String today() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime());

	  }
	
	
	////////////////////////////USERS////////////////////////////////////////////////////////////////
	// to get the user list and create the user list 
	public static void getUsers() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select userPin, userFirst, userLast, userRole, userState "
				+ "from mydb.user ";

		userList = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String pin = rs.getString("userPin");
				//String apin = Integer.parseInt(pin);
				String first = rs.getString("userFirst");
				String last = rs.getString("userLast");
				String role = rs.getString("userRole");
				String stats = rs.getString("userState");
				User theUser = new User(pin,first,last,role, stats);
				userList.add(theUser);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	
	/**
	 * @author Mohamed
	 * To check for user in the user list
	 * @param theUserPin
	 * @return it will return the user if exist otherwise it will return null.
	 */
	public static User checkUser(String theUserPin) {
		User result = null;
		if (userList != null) {
			for (User user: userList) {
				if (user.getUserPin().equals(theUserPin)){
					result = user;
				}
			}
		}
		return result;
	}
	
	/**
	 * @author Mohamed
	 * it will return the users with that last name or part of it 
	 * @param last Name
	 * @return list of user with that last name
	 */
	public static List<User> getFilterUsers(String lastName) {
		List<User> filterList = new ArrayList<User>();
		for (User user : userList) {
			if (user.getUserLast().toLowerCase().contains(lastName)) {
				filterList.add(user);
			}
		}
		return filterList;
	}
	
	/**
	 * To update user info
	 * @author Mohamed
	 * 
	 */
	public static void updateUser(int row, String apin, String aFrist,String aLast,String aRole,String aState){
		User updated = userList.get(row);
		String pin = updated.getUserPin();
		String sql = "update mydb.user set userPin = ? , userFirst= ? "
				+ ", userLast = ? , userRole= ? , userState = ?  where userPin= ?;";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, apin);
			preparedStatement.setString(2, aFrist);
			preparedStatement.setString(3, aLast);
			preparedStatement.setString(4, aRole);
			preparedStatement.setString(5, aState);
			preparedStatement.setString(6, pin);
			preparedStatement.executeUpdate();
			getUsers();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * to add new user 
	 * @author Mohamed
	 * @param aNewUser
	 */
	public static void addUser(User aNewUser) {
		String sql = "insert into mydb.user values " + "(?, ?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, aNewUser.getUserPin());
			preparedStatement.setString(2, aNewUser.getUserFirst());
			preparedStatement.setString(3, aNewUser.getUserLast());
			preparedStatement.setString(4, aNewUser.getUserRole());
			preparedStatement.setString(5, aNewUser.getUserState());
			preparedStatement.executeUpdate();
			getUsers();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
	}
	///////////////////////////////End User/////////////////////////////////////////////
	
	//////////////////////////////Repair ///////////////////////////////////////////////
	
	/**
	 * @author Mohamed
	 * It will get all empty locations
	 * @throws SQLException
	 */
	public static void getLocations() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select locationId "
				+ "from mydb.location where locationFull = 0 ";

		locList = new ArrayList<String>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String location = rs.getString("locationId");
				locList.add(location);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public static void getRepair() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select repairId, repairBrand, repairModel, repairIMEI, repairService, repairPrice, repairStats"
				+ ", repairComment, user_userPin, location_locationId "
				+ "from mydb.repair ;";

		repairList = new ArrayList<Repair>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int repairId = rs.getInt("repairId");
				String repairBrand = rs.getString("repairBrand");
				String repairModel = rs.getString("repairModel");
				String repairIMEI = rs.getString("repairIMEI");
				String repairService = rs.getString("repairService");
				double repairPrice = rs.getDouble("repairPrice");
				String repairStats = rs.getString("repairStats");
				String repairComment = rs.getString("repairComment");
				String user_userPin = rs.getString("user_userPin");
				String location_locationId = rs.getString("location_locationId");
				Repair myRepair = new Repair(repairId,repairBrand,repairModel,repairIMEI,repairPrice
						,repairService,repairStats,repairComment,user_userPin,location_locationId);
				repairList.add(myRepair);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
}
