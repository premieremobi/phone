/**
 * 
 */
package phoneInv;

/**
 * @author Mohamed
 *
 */
public class User {


	private String userPin;
	private String userFirst;
	private String userLast;
	private String userRole;
	private String userState;
	
	public User(String aPin, String aFirst, String aLast, String aRole, String aState) {
		this.userPin = aPin;
		this.userFirst = aFirst;
		this.userLast = aLast;
		this.userRole = aRole;
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
	public String getUserPin() {
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
	public void setUserPin(String userPin) {
		this.userPin = userPin;
	}

	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
}
