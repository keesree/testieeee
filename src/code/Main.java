package code;
import java.util.*;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException; 
public class Main {
//C:\Users\rsree\eclipse-workspace\CS1400Project3\output.txt
	public static double allowedPower;
	public static String txtFile;
	//C:\Users\mrsve\OneDrive\Desktop\app.txt
	public static int timesteps;
	public static int turnedToLow;
	public static int numberToLow;
	public static int numberBrownOut;
	public static int numberAffected;
	public static int maxAffected;
	public static String newFile;
	public static String appliancesAffected;
	public static ArrayList<SmartAppliance> smart = new ArrayList<SmartAppliance>(); 
	public static ArrayList<RegularAppliance> regular = new ArrayList<RegularAppliance>(); 
	public static ArrayList<Appliance> addedAppliances = new ArrayList<Appliance>(); 
	public static int[] locationsAffected = new int[101];	
	
	
	public static void addAppliance() 
	{
		Scanner scan;
		scan = new Scanner(System.in);
		while(true) {
			try {
				//Location ID must be 8-digit int
				System.out.println("Please insert location ID.");
				int cloneID = scan.nextInt();
				if (cloneID < 10000000) {
					throw new Exception("Invalid ID. Please enter an 8-digit location ID");
				}
				
				//Appliance name must be string
				System.out.println("Please insert appliance name.");
				String cloneName = scan.next();
				
				//Appliance power must be nonnegative int
				System.out.println("Please insert appliance power.");
				int clonePower = scan.nextInt();
				if (clonePower < 0) {
					throw new Exception("Your input is not positive or zero. Please try again"); 
				}
				
				//Probability must be double between 0 and 1
				System.out.println("Please insert probability.");
				double cloneProb = scan.nextDouble();
				if (cloneProb < 0 || cloneProb > 1) {
					throw new Exception("Probability must be in between 0 and 1. Please try again");
				}
				
				//Appliance type must be boolean
				System.out.println("Please appliance type (true for smart, false for regular)");
				String cloneTypes = scan.next();
				boolean cloneType = cloneTypes.equals("true");
				
				//Low power must be a percentage between 0 and 1, and 0.0 if regular appliance
				System.out.println("If it is a smart device, insert low power in percentage. If not, insert 0.0");
				double cloneLowPow = scan.nextDouble();
				if (cloneLowPow < 0 || cloneLowPow > 1) {
					throw new Exception("Percentage must be in between 0 and 1. Please try again");
				}
				if (cloneType == false && cloneLowPow != 0.0) {
					throw new Exception("Regular appliances cannot have a low power. Please insert 0.0");
				}
				
				//Adds smart/regular appliance to corresponding arraylist
				if(cloneType) 
				{
					SmartAppliance clone = new SmartAppliance(cloneID, cloneName, clonePower, cloneProb, cloneType, cloneLowPow);
					
					addedAppliances.add(clone);
					//smart.add(clone);
		
					System.out.println("Smart appliance added.\n");
					
				}
				else 
				{
					RegularAppliance clone = new RegularAppliance(cloneID, cloneName, clonePower, cloneProb, cloneType, cloneLowPow);
		
					//regular.add(clone);
					addedAppliances.add(clone);
					System.out.println("Regular appliance added\n");
				}
				break;
			
			}
			catch (InputMismatchException e) {
				System.out.println("Input did not match type. Please try again");
				scan.next();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	//Deletes appliance based on user input
	public static void deleteAppliance() 
	{
		Scanner scan;
		scan = new Scanner(System.in);
		
		while(true) {
			try {
				//Location ID must be 8-digit int
				System.out.println("Please insert location ID.");
				int cloneID = scan.nextInt();
				if (cloneID < 10000000) {
					throw new Exception("Invalid ID. Please enter an 8-digit location ID");
				}
				
				//Appliance name must be string
				System.out.println("Please insert appliance name.");
				String cloneName = scan.next();
				
				//Appliance power must be nonnegative int
				System.out.println("Please insert appliance power.");
				int clonePower = scan.nextInt();
				if (clonePower < 0) {
					throw new Exception("Your input is not positive or zero. Please try again"); 
				}
				
				//Probability must be double between 0 and 1
				System.out.println("Please insert probability.");
				double cloneProb = scan.nextDouble();
				if (cloneProb < 0 || cloneProb > 1) {
					throw new Exception("Probability must be in between 0 and 1. Please try again");
				}
				
				//Appliance type must be boolean
				System.out.println("Please appliance type (true for smart, false for regular)");
				String cloneTypes = scan.next();
				boolean cloneType = cloneTypes.equals("true");
				
				//Low power must be a percentage between 0 and 1, and 0.0 if regular appliance
				System.out.println("If it is a smart device, insert low power in percentage. If not, insert 0.0");
				double cloneLowPow = scan.nextDouble();
				if (cloneLowPow < 0 || cloneLowPow > 1) {
					throw new Exception("Percentage must be in between 0 and 1. Please try again");
				}
				if (cloneType == false && cloneLowPow != 0.0) {
					throw new Exception("Regular appliances cannot have a low power. Please insert 0.0");
				}
				
				//Checks if clone appliance is in corresponding arraylist and deletes it

				
				Appliance clone = new Appliance(cloneID, cloneName, clonePower, cloneProb, cloneType, cloneLowPow);
				int x=0;
				for (int i = 0; i < addedAppliances.size(); i++) {
					
					if(clone.equals(addedAppliances.get(i)))
					{
						addedAppliances.remove(addedAppliances.get(i));
						System.out.println("Appliance deleted.\n");
						x=1;
					}
					
					
					
				}
				if(x==0) {
					
					System.out.println("Could not find appliance.\n");
				}
				
				
				
				
				
				break;
			}
		
			catch (InputMismatchException e) {
				System.out.println("Input did not match type. Please try again");
				scan.next();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}	
	}
	
	//Prints object names in arraylist
	public static void listAppliances() 
	{
		
		for(int i = 0 ; i<addedAppliances.size();i++) {
			
			System.out.println(addedAppliances.get(i).getAppName());

			
		}
		for(int i = 0 ; i<smart.size();i++) {
			
			System.out.println(smart.get(i).getAppName());

			
		}
		for(int i = 0 ; i<regular.size();i++) {
			
			System.out.println(regular.get(i).getAppName());

			
		}

		
	}
	
	public static void Simulation() 
	{
		Scanner scnr1 = new Scanner(System.in);
		while (true) {
			

			
			
			try {
				//Input validation for allowedPower to be a double greater than or equal to 0
				System.out.println("Total allowed wattage (power): ");
				allowedPower = scnr1.nextDouble();
				
				if (allowedPower < 0) {
					throw new Exception("Your input is not positive or zero. Please try again.");
				}

				//Input validation for timesteps to be an integer greater than 0
				System.out.println("Timesteps: ");
				timesteps = scnr1.nextInt();
					
				if (timesteps < 0) {
					throw new Exception("Your input is not greater than 0. Please try again");
				}
					break;
				}
			
			catch (InputMismatchException e) {
				System.out.println("Input did not match type. Please try again");
				scnr1.next();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		//System.out.println("Comma separate text file: ");

		
			
			
			
			
			
			
			
		
	for(int timestep = 0; timestep < timesteps; timestep++) {
		for(int i = 0 ; i<smart.size(); i++) {
			smart.remove(i);
			
		}
		for(int i = 0 ; i<regular.size(); i++) {
			regular.remove(i);
		}
		
		for(int i = 0 ; i< addedAppliances.size(); i++) {
			
			if(addedAppliances.get(i).getAppType())
	
						
						smart.add(new SmartAppliance(addedAppliances.get(i).getLocationID(), 
								addedAppliances.get(i).getAppName(), 
								addedAppliances.get(i).getOnPower(), 
								addedAppliances.get(i).getProbOn(), 
								addedAppliances.get(i).getAppType(), 
								addedAppliances.get(i).getLowPower()));
			else {
				regular.add(new RegularAppliance(addedAppliances.get(i).getLocationID(), 
						addedAppliances.get(i).getAppName(), 
						addedAppliances.get(i).getOnPower(), 
						addedAppliances.get(i).getProbOn(), 
						addedAppliances.get(i).getAppType(), 
						addedAppliances.get(i).getLowPower()));
			
						
				
			}
			
			
			
			}
		
		// the actual code
		readAppFile(newFile);
		
		for(int i = 0 ; i < smart.size(); i++) {
			

			double randNum = Math.random();
			if(randNum <= smart.get(i).getProbOn() ) {
				
				smart.get(i).status = true;
				
			}
			else {
				smart.get(i).status = false;
				
			}
			
			
		}
		for(int i = 0 ; i < regular.size(); i++) {
			
			double randNum = Math.random();
			if(randNum <= regular.get(i).getProbOn() ) {
				
				regular.get(i).status = true;
				
			}
			else {
				regular.get(i).status = false;
				
			}
			
			
		}
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------//

		
		int totalPower = 0;
		for(int i = 0 ; i < smart.size(); i++) {
			//
			totalPower += smart.get(i).getOnPower();
			
		}
		for(int i = 0 ; i < regular.size(); i++) {
			//
			totalPower += regular.get(i).getOnPower();
			
		}
		//System.out.print("total power: "+ totalPower);
		System.out.println();
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------//

		//System.out.println("test: " + totalPower + "  " + allowedPower);

		if(totalPower > allowedPower) {
			//System.out.print("in here!");
			//algorithm
			
			//sort the on appliances 
			

			// from 0 - size, find min
			// after that add the appliances 
			
			int listSize = smart.size();
			int minpow = smart.get(0).getOnPower();
			
			int minpos = 0;
			for(int k = 0; k < smart.size(); k++) { //1
				
				
				
				for(int i = 0; i < listSize; i++) { //2
					if( smart.get(i).getOnPower() >= minpow ) {
						
						minpow = smart.get(i).getOnPower();
						minpos = i;
						//System.out.println(minpos + ", " + appliances.get(i).getOnPower());
						//appliances.remove(i);
						
					}
				} //2
				//System.out.print(minpow + ", ");
				
				
			
			//	(int locationID, String appName, int onPower, double probOn, boolean appType, double lowPower)

				
				smart.add(new SmartAppliance(smart.get(minpos).getLocationID(), 
						smart.get(minpos).getAppName(), 
						smart.get(minpos).getOnPower(), 
						smart.get(minpos).getProbOn(), 
						smart.get(minpos).getAppType(), 
						smart.get(minpos).getLowPower()));
				//appliances.get(minpos).setOnPower(-1);
				smart.remove(minpos);
				listSize--;
				minpow = smart.get(0).getOnPower();
			} //1
			
			for(int i = 0 ; i < smart.size(); i++) {
				//System.out.print(smart.get(i).getOnPower() + ", ");
			}
			System.out.println();
			//System.out.println("reached");

			
			 // current status: sorted the arraylist successfully according to On Power
			// implement the turn to low methods successfully
			// keep using turn to low methods until total allowed power is reached
			//brown out if necessary
			
			for(int app = 0; app < smart.size(); app++) {
				
				
				int total = 0;
				for(int i = 0 ; i < smart.size(); i++) {
					//System.out.print("in here!");
					total += smart.get(i).getOnPower();
				}
				//System.out.println("above if statements");
				if(total > allowedPower ) {
					//System.out.print("at low - upper if statement!");
					if(smart.get(app).getOnPower() < totalPower -  allowedPower) {
						
						smart.get(app).turnLow();
						numberToLow ++;
						//System.out.print("at low!");
						
						locationsAffected[smart.get(app).getLocationID() - 10000000]++;
						appliancesAffected+=smart.get(app).getAppName() + " - turned to low" + "\n";
						
						//System.out.println("turning to low: " + smart.get(app).getAppName() + ": " + smart.get(app).getOnPower());
						
					}
					
					//System.out.println("reached to 1");
					
				}
				else {
					break;
				}
				
				
			}
				
				
				
			
			// for the brown out code
			// create a new array(tempLocationID) to hold the locations 
			// iterate through each element of appliances
			// for each location : tempLocationID[10000009 - 10000000]++;
			// sort tempLocationID in increasing order
			// 
			
			int[] tempLocationID =  new int[1000]; //change to arraylist
			//int[] tempLocationID2 =  new int[1000]; //change to arraylist

			int counter = 0;
			if(smart.size()>regular.size()) {
				counter = smart.size();
			}
			else {
				
				counter = regular.size();
			}
			
			
			for(int i = 0; i < counter; i++) {
				if( i<smart.size() ) {
					
					tempLocationID[smart.get(i).getLocationID() - 10000000]++;
				}
				
				if( i<regular.size() ) {
					tempLocationID[regular.get(i).getLocationID() - 10000000]++;
				}
				
				
				
				
			}

			
			
			for(int i = 0; i < tempLocationID.length; i++) {
				
				int total = 0;
				for(int j = 0 ; j < smart.size(); j++) {
					//System.out.print("in here!");
					total += smart.get(j).getOnPower();
				}
				for(int j = 0 ; j < regular.size(); j++) {
					//System.out.print("in here!");
					total += regular.get(j).getOnPower();
				}
				
				
				
				int min = 10000;
				int minposit = 0;
				for(int m = 0;m < tempLocationID.length; m++) {
					
					if(tempLocationID[m] <= min && tempLocationID[m]!=0) {
						min = tempLocationID[m];
						minposit = m;
						
					}
					
				}
				tempLocationID[minposit] = 0;
				
				if(total>allowedPower) {
					//System.out.println("brown out");
					for(int k = 0; k < smart.size(); k++) {
						//System.out.println(min == (appliances.get(k).locationID - 10000000));
						if( minposit == (smart.get(k).locationID - 10000000) ){
							smart.get(k).setOnPower(0);
							locationsAffected[smart.get(k).locationID - 10000000]++;
							appliancesAffected+= smart.get(k).getAppName() + " - browned out" + "\n";
							//System.out.println("here");
						}
					}
					for(int k = 0; k < regular.size(); k++) {
						//System.out.println(min == (appliances.get(k).locationID - 10000000));
						if( minposit == (regular.get(k).locationID - 10000000) ){
							regular.get(k).setOnPower(0);
							locationsAffected[regular.get(k).locationID - 10000000]++;
							appliancesAffected+= regular.get(k).getAppName() + " - browned out" + "\n";
							//System.out.println("here");
						}
					}					
					
					numberBrownOut++;
					
				}			
				

				else {
					//System.out.println();
					//.out.println("break: " + total);
					break;
				}
				
			//System.out.println("total: " + total);
				}
				
				
				
		
			
			
		}
		
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------//

		//System.out.println("REACHED!!!!");
		for(int i = 0 ; i < smart.size(); i++) {
			//System.out.print(smart.get(i).getOnPower() + ", ");
		}
		System.out.println();
		for(int i = 0 ; i < regular.size(); i++) {
			//System.out.print(regular.get(i).getOnPower() + ", ");
		}
		System.out.println();
		int t = 0;
		for(int i = 0 ; i < smart.size(); i++) {
			//
			t += smart.get(i).getOnPower();
			
		}
		for(int i = 0 ; i < regular.size(); i++) {
			//
			t += regular.get(i).getOnPower();
			
		}
		//System.out.print("total power after computation: "+ t);
		System.out.println();
		// ------------------
		
		
		
		
		//output stuff
		System.out.println("Number of appliances turned to low: " + numberToLow ); 
		System.out.println("Number of locations browned out: " + numberBrownOut); 
		
		//
		int countAffected = 0;
		for(int i =0; i<locationsAffected.length; i++) {
			
			if(locationsAffected[i] != 0) {
				countAffected++;
			}
			
		}
		System.out.println("Total number of locations affected: " + countAffected);

		int maxAffectedLoc = 0;
		for(int i =0; i<locationsAffected.length; i++) {
			
			if(locationsAffected[i] > maxAffected) {
				maxAffectedLoc = i;
			}
			
		}		
		System.out.println("Maximum affected location : " + (maxAffectedLoc+10000000));
		
		
		
		
		try {
			String name  = "summaryReport" + (timesteps+1) + ".txt";
			System.out.print(timestep);
			File myObj = new File(name);
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("...");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		try {
			System.out.print(timestep);
			String name  = "summaryReport" + (timesteps+1) + ".txt";
		      FileWriter report = new FileWriter("name");
		    //code
	    	  //report.write("timestep "+(timesteps+1));
	    	report.write("Locations affected: " + "\n");
	  		for(int i =0; i<locationsAffected.length; i++) {
				
				if(locationsAffected[i] != 0) {
					report.write(i+10000000 + "\n");
				}
				
			}
	    	report.write(appliancesAffected);
	          
	          report.close();
	    	//code  
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		


		
		//refreshing the locations affected array
		for(int i =0; i<locationsAffected.length; i++) {
			
			locationsAffected[i] = 0;
			
		}
		int totalPower1 = 0;
		for(int i = 0 ; i < smart.size(); i++) {
			//
			totalPower1 += smart.get(i).getOnPower();
			
		}
		for(int i = 0 ; i < regular.size(); i++) {
			//
			totalPower1 += regular.get(i).getOnPower();
			
		}
		//System.out.print("total power: "+ totalPower);
		System.out.println();
		appliancesAffected = "";
		numberToLow = 0;
		numberBrownOut = 0;
		//System.out.println("total apps: "+ smart.size() + regular.size());
	}
	}

public static boolean readAppFile(String s) {
		
		//Removes existing appliances

		
		try {
			// Open and read file
		    File myObj = new File(s);
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	String data = myReader.nextLine();
		        
			    int prevComma = 0;
			    int count = 0;
			      
			    int tempID = 0;
			    String tempDescription = "";
			    int tempPowerOn = 0;
			    double tempProb = 0;
			    boolean tempSmart = true;
			    double tempPercentReduc = 0;
   
			    for(int i = 0 ; i < data.length(); i++) {
			    	  //System.out.println(data.substring(i,i+1));
			    	  if(data.substring(i,i+1).equals(",") && count<5) {
						
						if(count == 0) {
							tempID = Integer.valueOf(data.substring(0,i));
							//System.out.println("0000000  " + tempID + "  00000000");
							count++;
							prevComma = i;
							continue;
							
						}
						
						if(count == 1) {
							tempDescription = data.substring(prevComma+1,i);
							//System.out.println("1111111  " + tempDescription + "  1111111111");
							count++;
							prevComma = i;
							continue;
							
						}	
						
						if(count == 2) {
							tempPowerOn = Integer.valueOf(data.substring(prevComma+1,i));
							//System.out.println("22222222  " + tempPowerOn + "  2222222222");
							count++;
							prevComma = i;
							continue;
						}
						
						if(count == 3) {	
							tempProb = Double.valueOf(data.substring(prevComma+1,i));
							//System.out.println("33333333  " + tempProb + "  33333333");
							count++;
							prevComma = i;
							continue;
						}
						
						if(count == 4) {	
							String test = data.substring(prevComma+1,i);
							if(test.equals("true")) {
								tempSmart = true;	
							}
							
							else {
								tempSmart = false;	
							}
							
							//System.out.println("4444444  " + tempSmart + "  444444444");
							count++;
							prevComma = i;
							continue;
						}
					//	System.out.println("reached5");

					}
			    	  
					if(count ==5 && i == data.length()-1) {
						tempPercentReduc = Double.valueOf(data.substring(prevComma+1,i));
						//System.out.println("55555555  " + tempPercentReduc + "  55555555");
						count++;
						continue;
					}	
				}
			          
				if (tempSmart == true) {
					SmartAppliance temp = new SmartAppliance(tempID, tempDescription, tempPowerOn, tempProb, tempSmart,
							tempPercentReduc);
					smart.add(temp);
				} else {

					RegularAppliance temp = new RegularAppliance(tempID, tempDescription, tempPowerOn, tempProb, tempSmart,
							tempPercentReduc);
					regular.add(temp);
				}
		      }
		      myReader.close();	
		      return true;
		    }
		catch (FileNotFoundException  e) {
		      System.out.println("Could not read file. Please try again");
		      return false;
		    }
		catch (IllegalArgumentException e) {
			System.out.println("File content does not match arguments. Please try again");
			return false;
		}
	}
	
	
	public static void main(String args[]) {
		
		Scanner scnr = new Scanner(System.in);
		
		
		while(true){// Application menu to be displayed to the user.
			System.out.println("Select an option:");
			System.out.println("Type \"A\" Add an appliance");
			System.out.println("Type \"D\" Delete an appliance");	
			System.out.println("Type \"L\" List the appliances");
			System.out.println("Type \"F\" Read Appliances from a file");
			System.out.println("Type \"S\" To Start the simulation");
			System.out.println("Type \"Q\" Quit the program");
			String option1=scnr.nextLine();
			/* Complete the skeleton code below */
		
			
/* Complete the skeleton code below */
			
			if(option1.equalsIgnoreCase("A")) 
			{
				addAppliance();
					
			}
			else if(option1.equalsIgnoreCase("D")) 
			{
				deleteAppliance();
				
			}
			else if(option1.equalsIgnoreCase("L")) 
			{
				
				listAppliances();
				
			}
			else if(option1.equalsIgnoreCase("F")) 
			{
				System.out.println("Input for new file.");
				String tempStr = scnr.nextLine();
				 newFile = tempStr;
				
				while(!readAppFile(newFile)) {
					newFile = scnr.nextLine();
				}
			}
			
			else if(option1.equalsIgnoreCase("S")) 
			{
				//will add all the attributes defined in the beginning to this method's arguments
				Simulation();
			}
			else if(option1.equalsIgnoreCase("Q")) 
			{
				break;

			}
			else {
				System.out.println("Invalid input. Please try again\n");
			}
			
			
			
			
			
			
			
			
				
		}
		

		// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------//
		
		//
		
		//turning on or off randomly

		
		
	}
	
	
	
	
	
	
}
