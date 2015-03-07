package edu.uniandes.ecos.psp21.view;

import java.util.Scanner;

import edu.uniandes.ecos.psp21.model.XValueFinder;

/**
 * This class represents the interaction between user and program
 * via System console
 *
 */
public class ConsoleApp 
{
	/**
	 * The main method to execute via Console this Application
	 * @param args	Execution arguments
	 */
    public static void main( String[] args )
    {
    	System.out.println("--- X value Finder for a given p value for T-Distribution ---");
    	System.out.println("--- Using Simpson Rule Integration ---");
		System.out.println("NOTE: Please be sure all numbers you write are decimals or at least numbers. No letters!");
		
		Scanner scanner = new Scanner(System.in);
		Integer degreesOfFreedom = 0;
		Double expectedP = 0D;
		Double allowedError = 0.0001;
		XValueFinder finder = null;
		
		try{
			System.out.println("Type the degrees of freedom:");
			degreesOfFreedom = Integer.valueOf(scanner.nextLine());
			
			System.out.println("Type the expected p value:");
			expectedP = Double.valueOf(scanner.nextLine());
			
			System.out.println("Type the allowed error (E):");
			allowedError = Double.valueOf(scanner.nextLine());
			
			finder = new XValueFinder(degreesOfFreedom, expectedP, allowedError);
			finder.findXValue();
			
		}
		catch (NumberFormatException ex){
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			scanner.close();
		}
		
		
    }
}
