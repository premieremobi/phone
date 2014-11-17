/**
 * 
 */
package phoneInv;

/**
 * @author Mohamed
 *
 */
public class Repair extends Phone {
	
	private String repairService;
	private String repairStats;
	private String repairComment;
	/**
	 * @param aBrand
	 * @param aModel
	 * @param aImei
	 * @param aPrice
	 */
	public Repair(String aBrand, String aModel, String aImei, double aPrice,
			String aService, String aStats, String aComment) {
		super(aBrand, aModel, aImei, aPrice);
		this.repairService = aService;
		this.repairStats = aStats;
		this.repairComment = aComment;
	}
	
	/**
	 * @return the repairService
	 */
	public String getRepairService() {
		return repairService;
	}
	/**
	 * @param repairService the repairService to set
	 */
	public void setRepairService(String repairService) {
		this.repairService = repairService;
	}
	/**
	 * @return the repairStats
	 */
	public String getRepairStats() {
		return repairStats;
	}
	/**
	 * @param repairStats the repairStats to set
	 */
	public void setRepairStats(String repairStats) {
		this.repairStats = repairStats;
	}
	/**
	 * @return the repairComment
	 */
	public String getRepairComment() {
		return repairComment;
	}
	/**
	 * @param repairComment the repairComment to set
	 */
	public void setRepairComment(String repairComment) {
		this.repairComment = repairComment;
	}

}
