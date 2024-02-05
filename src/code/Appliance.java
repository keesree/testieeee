
package code;
public class Appliance 
{
	//String str;
	int locationID;
	String appName;
	int onPower;
	double probOn;
	boolean appType;
	double lowPower;
	public boolean status;

	

	public Appliance(int locationID, String appName, int onPower, double probOn, boolean appType, double lowPower) 
	{
		super();
		this.locationID = locationID;
		this.appName = appName;
		this.onPower = onPower;
		this.probOn = probOn;
		this.appType = appType;
		this.lowPower = lowPower;
	}
	


	public int getLocationID() {
		return locationID;
	}





	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}





	public String getAppName() {
		return appName;
	}





	public void setAppName(String appName) {
		this.appName = appName;
	}





	public int getOnPower() {
		return onPower;
	}





	public void setOnPower(int onPower) {
		this.onPower = onPower;
	}





	public double getProbOn() {
		return probOn;
	}





	public void setProbOn(float probOn) {
		this.probOn = probOn;
	}





	public boolean getAppType() {
		return appType;
	}





	public void setAppType(boolean appType) {
		this.appType = appType;
	}





	public double getLowPower() {
		return lowPower;
	}





	public void setLowPower(int lowPower) {
		this.lowPower = lowPower;
	}





	public static void main(String[] args) 
	{
		

	}

}