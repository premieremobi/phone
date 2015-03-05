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
	private int phoneAval;
	private int sNum;

	/**
	 * 
	 */
	public Stock(int aSn,String aImei, String aCondition, double aPrice,
			 int aAval,String aBrand, String aModel) {
		super(aBrand, aModel, aImei, aPrice);
		this.phoneCondition = aCondition;
		this.phoneAval = aAval;
		this.sNum = aSn;
		
	}

	
	public Stock(String aImei, String aCondition, double aPrice,
			 int aAval,String aBrand, String aModel) {
		super(aBrand, aModel, aImei, aPrice);
		this.phoneCondition = aCondition;
		this.phoneAval = aAval;
		
		
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

	/**
	 * @return the phoneAval
	 */
	public int getPhoneAval() {
		return phoneAval;
	}

	/**
	 * @param phoneAval the phoneAval to set
	 */
	public void setPhoneAval(int phoneAval) {
		this.phoneAval = phoneAval;
	}

	/**
	 * @return the sNum
	 */
	public int getsNum() {
		return sNum;
	}

	/**
	 * @param sNum the sNum to set
	 */
	public void setsNum(int sNum) {
		this.sNum = sNum;
	}

	
}
