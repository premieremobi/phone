/**
 * 
 */
package phoneInv;

/**
 * @author Mohamed
 *
 */
public class Stock extends Phone{
	
	private String phoneCondition;
	private boolean phoneAval;

	/**
	 * 
	 */
	public Stock(String aBrand, String aModel, String aImei, double aPrice,
			String aCondition, boolean aAval) {
		super(aBrand, aModel, aImei, aPrice);
		this.phoneCondition = aCondition;
		this.phoneAval = aAval;
		
	}

	/**
	 * @return the phoneAval
	 */
	public boolean isPhoneAval() {
		return phoneAval;
	}

	/**
	 * @param phoneAval the phoneAval to set
	 */
	public void setPhoneAval(boolean phoneAval) {
		this.phoneAval = phoneAval;
	}

	/**
	 * @return the phoneCondition
	 */
	public String getPhoneCondition() {
		return phoneCondition;
	}

	/**
	 * @param phoneCondition the phoneCondition to set
	 */
	public void setPhoneCondition(String phoneCondition) {
		this.phoneCondition = phoneCondition;
	}

}
