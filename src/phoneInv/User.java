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
	private String userState;
	
	public User(int aPin, String aFirst, String aLast, String aState) {
		this.userPin = aPin;
		this.userFirst = aFirst;
		this.userLast = aLast;
		this.userState = aState;
		
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

	/**
	 * @return the userState
	 */
	public String getUserState() {
		return userState;
	}

	/**
	 * @param userState the userState to set
	 */
	public void setUserState(String userState) {
		this.userState = userState;
	}

	/**
	 * @param userPin the userPin to set
	 */
	public void setUserPin(int userPin) {
		this.userPin = userPin;
	}
}
