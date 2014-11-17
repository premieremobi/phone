/**
 * 
 */
package phoneInv;

import java.sql.Date;

/**
 * @author Mohamed
 *
 */
public class Sale {
	
	private Date saleDate;
	private String user;
	private int stockSn;
	
	public Sale(Date asaleDate, String aUser, int aSN){
		this.saleDate = asaleDate;
		this.user = aUser;
		this.stockSn = aSN;
	}

	/**
	 * @return the saleDate
	 */
	public Date getSaleDate() {
		return saleDate;
	}

	/**
	 * @param saleDate the saleDate to set
	 */
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the stockSn
	 */
	public int getStockSn() {
		return stockSn;
	}

	/**
	 * @param stockSn the stockSn to set
	 */
	public void setStockSn(int stockSn) {
		this.stockSn = stockSn;
	}

}
