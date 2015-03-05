/**
 * 
 */
package phoneInv;

/**
 * @author Mohamed
 *
 */
public class Model {
	
	public String model;
	public String brand;
	
	public Model(String aModel, String aBrand) {
		this.model = aModel;
		this.brand = aBrand;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

}
