/**
 * 
 */
package phoneInv;

/**
 * @author Mohamed
 *
 */
public class User {


	private int userPin;
	private String userFirst;
	private String userLast;
	
	public User(int aPin, String aFirst, String aLast) {
		this.userPin = aPin;
		this.userFirst = aFirst;
		this.userLast = aLast;
		
	}
	
	/**
	 * @return the userFirst
	 */
	public String getUserFirst() {
		return userFirst;
	}

	/**
	 * @param userFirst the userFirst to set
	 */
	public void setUserFirst(String userFirst) {
		this.userFirst = userFirst;
	}

	/**
	 * @return the userLast
	 */
	public String getUserLast() {
		return userLast;
	}

	/**
	 * @param userLast the userLast to set
	 */
	public void setUserLast(String userLast) {
		this.userLast = userLast;
	}

	/**
	 * @return the userPin
	 */
	public int getUserPin() {
		return userPin;
	}
}
