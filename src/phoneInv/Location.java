/**
 * 
 */
package phoneInv;

/**
 * @author Mohamed
 *
 */
public class Location {
	
	private String locationId;
	private boolean locationFull;
	
	
	Location(String aLocation, boolean afull){
		this.locationId = aLocation;
		this.locationFull = afull;
	}


	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}


	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}


	/**
	 * @return the locationFull
	 */
	public boolean isLocationFull() {
		return locationFull;
	}


	/**
	 * @param locationFull the locationFull to set
	 */
	public void setLocationFull(boolean locationFull) {
		this.locationFull = locationFull;
	}
	

}
