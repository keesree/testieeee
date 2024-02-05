package code;

public class SmartAppliance extends Appliance 
{
	private boolean off;
	private boolean on;
	private boolean low;
	public boolean status;
	//add a method to the low power from wattage

	public SmartAppliance(int locationID, String appName, int onPower, double probOn, boolean appType, double lowPower) 
	{
		super(locationID, appName, onPower, probOn, appType, lowPower);
		this.off = false;
		this.on = true;
		
		// TODO Auto-generated constructor stub
	}
	
	public boolean turnOff() 
	{
		off = true;
		this.onPower = 0;
		return off;
				
	}
	public boolean turnON() 
	{
		on = true;
		return on;
				
	}
	public void turnLow() {
		
		this.onPower = this.onPower - (int)this.getLowPower() * this.onPower;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}