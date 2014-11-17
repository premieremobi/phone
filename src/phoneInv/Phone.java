/**
 * 
 */
package phoneInv;

/**
 * @author Mohamed
 *
 */
public class Phone {
	

	private String phoneBrand;
	private String phoneModel;
	private String phoneImei;
	private double phonePrice;
	
	public Phone(String aBrand, String aModel, String aImei, double aPrice){
		this.phoneBrand = aBrand;
		this.phoneImei = aImei;
		this.phoneModel = aModel;
		this.phonePrice = aPrice;
	}

	/**
	 * @return the phoneBrand
	 */
	public String getPhoneBrand() {
		return phoneBrand;
	}

	/**
	 * @param phoneBrand the phoneBrand to set
	 */
	public void setPhoneBrand(String phoneBrand) {
		this.phoneBrand = phoneBrand;
	}

	/**
	 * @return the phoneModel
	 */
	public String getPhoneModel() {
		return phoneModel;
	}

	/**
	 * @param phoneModel the phoneModel to set
	 */
	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	/**
	 * @return the phoneImei
	 */
	public String getPhoneImei() {
		return phoneImei;
	}

	/**
	 * @param phoneImei the phoneImei to set
	 */
	public void setPhoneImei(String phoneImei) {
		this.phoneImei = phoneImei;
	}

	/**
	 * @return the phonePrice
	 */
	public double getPhonePrice() {
		return phonePrice;
	}

	/**
	 * @param phonePrice the phonePrice to set
	 */
	public void setPhonePrice(double phonePrice) {
		this.phonePrice = phonePrice;
	}
}
