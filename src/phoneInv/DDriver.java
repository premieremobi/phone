/**
 * 
 */
package phoneInv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	public static String userName;
	public static String password;
	public static String serverName;
	public static Connection conn;
	public static List<User> userList;
	public static List<Stock> stockList;
	public static List<Repair> repairList;
	public static List<String> brandList;
	public static List<Model> modelList;
	public static List<String> locList;
	public final static  String DATE_FORMAT_NOW = "yyyy-MM-dd";
	static ArrayList<String> setting = new ArrayList<String>();
	private static boolean flag = false;
	

	public static void createConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
				+ serverName + "/", connectionProps);
        if (conn != null) {
        	flag = true;
        }
		System.out.println("Connected to database");
	}
	
	public DDriver(){
		try {
			readSetting();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			 if (flag) {
				    getUsers();
					getRepairs();
					getLocations();
					getBrands();
					getModels();
					getStocks();
			 }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void updateData() {
		try {
					
					getUsers();
					getRepairs();
					getLocations();
					getBrands();
					getModels();
					getStocks();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	public void readSetting() throws IOException {
		 // The name of the file to open.
        String fileName = "setting.txt";
        

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                setting.add(line);
            }    

            // Always close files.
            bufferedReader.close();            
        } catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        userName = setting.get(0);
        password = setting.get(1);
        serverName = setting.get(2);
        
	}
	
	public static void writeSetting() {
		 // The name of the file to open.
        String fileName = "setting.txt";
        setting.set(0, userName);
        setting.set(1, password);
        setting.set(2, serverName);

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(setting.get(0));
            bufferedWriter.newLine();
            bufferedWriter.write(setting.get(1));
            bufferedWriter.newLine();
            bufferedWriter.write(setting.get(2));

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
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
			if (user.getUserLast().toLowerCase().contains(lastName.toLowerCase())) {
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
		updated.setUserFirst(aFrist);
		updated.setUserLast(aLast);
		updated.setUserPin(apin);
		updated.setUserRole(aRole);
		updated.setUserState(aState);
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
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		try {
			getUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @author Mohamed
	 * @param deletedUser
	 */
	public static void deleteUser(User deletedUser){
		String pin = deletedUser.getUserPin();
		String sql = "DELETE FROM mydb.user where userPin= "+pin+";";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
	///////////////////////////////End User/////////////////////////////////////////////
	
	////////////////////////////////Location////////////////////////////////////////////
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
	
	public static void updateLocation (String loc, int full) {
		
		String sql = "update mydb.location set locationFull = ? where locationId= ?;";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, full);
			preparedStatement.setString(2, loc);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		try {
			getLocations();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * to add new location
	 * @author Mohamed
	 * @param aNewLocation
	 */
	public static void addLocation(String aLocation) {
		String sql = "insert into mydb.location values " + "(?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, aLocation);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		locList.add(aLocation);
	}
	///////////////////////////////End Locations////////////////////////////////////////
	
	////////////////////////////////Brand//////////////////////////////////////////////
	/**
	 * @author Mohamed
	 * It will get all brands
	 * @throws SQLException
	 */
	public static void getBrands() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select brand "
				+ "from mydb.phonebrand ;";

		brandList = new ArrayList<String>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String brand = rs.getString("brand");
				brandList.add(brand);
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
	 * to add new location
	 * @author Mohamed
	 * @param aNewLocation
	 */
	public static void addBrand(String aBrand) {
		String sql = "insert into mydb.phonebrand values " + "(?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, aBrand);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		brandList.add(aBrand);
	}
	
	///////////////////////////////End Brand//////////////////////////////////////////
	
	///////////////////////////////Model/////////////////////////////////////////////
	/**
	 * @author Mohamed
	 * It will get all models
	 * @throws SQLException
	 */
	public static void getModels() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select model, brand "
				+ "from mydb.phonemodel ;";
		

		modelList = new ArrayList<Model>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String model = rs.getString("model");
				String brand = rs.getString("brand");
				Model amodel = new Model(model, brand);
				modelList.add(amodel);
;			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public static String [] getmodelforbrand(String aBrand) {
		List<String> result = new ArrayList<String>();
		
		for (Model myModel: modelList) {
			if (myModel.getBrand().equals(aBrand)){
				result.add(myModel.getModel());
			}
		}
		String [] res = new String [result.size()];
		int i =0;
		for (String myModel: result) {
			res [i]=myModel;
			i++;
		}
		
		return res;
	}
	/**
	 * to add new model
	 * @author Mohamed
	 * @param aNewLocation
	 */
	public static void addModel(Model aModel) {
		String sql = "insert into mydb.phonemodel values " + "(?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, aModel.getModel());
			preparedStatement.setString(2, aModel.getBrand());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		modelList.add(aModel);
	}
	///////////////////////////////End Model/////////////////////////////////////////
	
	//////////////////////////////Repair ///////////////////////////////////////////////
	
	/**
	 * @author Mohamed
	 * To check for repair in the repair list
	 * @param aRepairId
	 * @return it will return the repair if exist otherwise it will return null.
	 */
	public static Repair getRepair(int aRepairId) {
		Repair result = null;
		if (repairList != null) {
			for (Repair repair: repairList) {
				if (repair.getRepairId() == aRepairId){
					result = repair;
				}
			}
		}
		return result;
	}
	
	public static int getRepairID(String iemi) {
		int result = 0;
		if (repairList != null) {
			for (Repair repair: repairList) {
				if (repair.getPhoneImei().equals(iemi)){
					result = repair.getRepairId();
				}
			}
		}
		return result;
	}
	public static List<Repair> getFilterRepair(int repairId) {
		List<Repair> filterList = new ArrayList<Repair>();
		for (Repair repair : repairList) {
			if (repair.getRepairId() == repairId) {
				filterList.add(repair);
			}
		}
		return filterList;
	}
	
	
	
	public static void getRepairs() throws SQLException {
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
				Repair myRepair = new Repair(repairId,repairBrand,repairModel,repairIMEI
						,repairService,repairPrice,repairStats,repairComment,user_userPin,location_locationId);
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
	
	/**
	 * to add new repair
	 * @author Mohamed
	 * @param aNewRepair
	 */
	public static void addRepair(Repair aNewRepair) {
		String sql = "insert into mydb.repair values " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, null);
			preparedStatement.setString(2, aNewRepair.getPhoneBrand());
			preparedStatement.setString(3, aNewRepair.getPhoneModel());
			preparedStatement.setString(4, aNewRepair.getPhoneImei());
			preparedStatement.setString(5, aNewRepair.getRepairService());
			preparedStatement.setDouble(6, aNewRepair.getPhonePrice());
			preparedStatement.setString(7, aNewRepair.getRepairStats());
			preparedStatement.setString(8, aNewRepair.getRepairComment());
			preparedStatement.setString(9, aNewRepair.getRepairUserPin());
			preparedStatement.setString(10, aNewRepair.getRepairLocation());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		try {
			getRepairs();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	// To update repair transiction
	public static  void updateRepair(int row, String repairBrand, String repairModel,String repairIMEI,String repairService,double repairPrice,
			String repairStats, String repairComment, String userPin, String locationId){
		Repair updated = repairList.get(row);
		int id = updated.getRepairId();
		updated.setPhoneBrand(repairBrand);
		updated.setPhoneModel(repairModel);
		updated.setPhoneImei(repairIMEI);
		updated.setRepairService(repairService);
		updated.setPhonePrice(repairPrice);
		updated.setRepairStats(repairStats);
		updated.setRepairComment(repairComment);
		updated.setRepairUserPin(userPin);
		updated.setRepairLocation(locationId);
		
		String sql = "update mydb.repair set repairBrand= ? "
				+ ", repairModel = ? , repairIMEI = ? , repairService = ? , repairPrice = ? , repairStats = ? , repairComment = ? "
				+ ", user_userPin = ? , location_locationId = ? where repairId= ?;";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, repairBrand);
			preparedStatement.setString(2, repairModel);
			preparedStatement.setString(3, repairIMEI);
			preparedStatement.setString(4, repairService);
			preparedStatement.setDouble(5, repairPrice);
			preparedStatement.setString(6, repairStats);
			preparedStatement.setString(7, repairComment);
			preparedStatement.setString(8, userPin);
			preparedStatement.setString(9, locationId);
			preparedStatement.setInt(10, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	/////////////////////////////////////////End Repair////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////Stock///////////////////////////////////////////////////////////////////
	
	// get stock list
	public static void getStocks() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select sn, stockIMEI, stockCondition, stockPrice, stockAval, phoneBrand_brand, phoneModel_model"
				+ " from mydb.stock ;";

		stockList = new ArrayList<Stock>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int stockId = rs.getInt("sn");
				String imei = rs.getString("stockIMEI");
				String condition = rs.getString("stockCondition");
				double price = rs.getDouble("stockPrice");
				int stockAval = rs.getInt("stockAval");
				String brand = rs.getString("phoneBrand_brand");
				String model = rs.getString("phoneModel_model");
				Stock myStock = new Stock(stockId,imei, condition, price,stockAval,brand, model);
				stockList.add(myStock);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public static List<Stock> getFilterStock(String aBrand) {
		List<Stock> filterList = new ArrayList<Stock>();
		for (Stock myStock : stockList) {
			if (myStock.getPhoneBrand().toLowerCase().contains(aBrand.toLowerCase())) {
				filterList.add(myStock);
			}
		}
		return filterList;
	}
	
	public static Stock getStock(int sn) {
		Stock result = null;
		for (Stock myStock : stockList) {
			if (myStock.getsNum() == sn) {
				result = myStock;
			}
		}
		return result;
	}
	/**
	 * to add new stock
	 * @author Mohamed
	 * @param aNewStock
	 */
	public static void addStock(Stock aNewStock) {
		String sql = "insert into mydb.stock values " + "(?, ?, ?, ?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, null);
			preparedStatement.setString(2, aNewStock.getPhoneImei());
			preparedStatement.setString(3, aNewStock.getPhoneCondition());
			preparedStatement.setDouble(4, aNewStock.getPhonePrice());
			preparedStatement.setInt(5, aNewStock.getPhoneAval());
			preparedStatement.setString(6, aNewStock.getPhoneBrand());
			preparedStatement.setString(7, aNewStock.getPhoneModel());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		try {
			getStocks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// To update stock
		public static  void updateStock(int row,String aImei, String aCondition, double aPrice,
				 int aAval,String aBrand, String aModel){
			Stock updated = stockList.get(row);
			int id = updated.getsNum();
			updated.setPhoneImei(aImei);;
			updated.setPhoneCondition(aCondition);;
			updated.setPhonePrice(aPrice);
			updated.setPhoneAval(aAval);
			updated.setPhoneBrand(aBrand);
			updated.setPhoneModel(aModel);
			
			String sql = "update mydb.stock set stockIMEI= ? "
					+ ", stockCondition = ? , stockPrice = ? , stockAval = ? , phoneBrand_brand = ? , phoneModel_model = ? where sn= ?;";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, aImei);
				preparedStatement.setString(2, aCondition);
				preparedStatement.setDouble(3, aPrice);
				preparedStatement.setInt(4, aAval);
				preparedStatement.setString(5, aBrand);
				preparedStatement.setString(6, aModel);
				preparedStatement.setInt(7, id);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
	
	//////////////////////////////////////////End Stock////////////////////////////////////////////////////////////////
	
		public static String [] getlist(List<String> mylist) {
			String result [] = new String [mylist.size()];
			int i = 0;
			for(String line : mylist) {
				result [i] = line;
				i++;
			}
			return result;
		}
		
		public static String [] getModelList() {
			String result [] = new String [modelList.size()];
			int i = 0;
			for(Model line : modelList) {
				result [i] = line.model;
				i++;
			}
			return result;
		}
	
	
}
