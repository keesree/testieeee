package code;

public class RegularAppliance extends Appliance 
{
	//add method from low wattage
	private boolean off;
	private boolean on;
	public boolean status;
	


	public RegularAppliance(int locationID, String appName, int onPower, double probOn, boolean appType, double lowPower) 
	{
		super(locationID, appName, onPower, probOn, appType, lowPower);
		this.off = false;
		this.on = true;
		
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
