package edu.uniandes.ecos.psp21.model;

import edu.uniandes.ecos.psp2.model.SimpsonRuleIntegration;

/**
 * This class is designed to find the closest value of
 * Xi for the T-Distribution function integrated using Simpson
 * Rule, given a integral value (p)
 * @author drenteria
 *
 */
public class XValueFinder {
	
	/**
	 * Stores the degrees of freedom required to integrate
	 */
	private int degreesOfFreedom;
	
	/**
	 * Stores the value to be evaluated against the integration
	 */
	private double trialValue;
	
	/**
	 * Stores the maximum possible error to to be accepted
	 * for the integration value
	 */
	private double acceptedError;
	
	/**
	 * Variable to represent the integration function
	 * using Simpson Rule
	 */
	private SimpsonRuleIntegration integrator;
	
	/**
	 * The difference to be added or subtracted to the trial
	 * value to evaluate the integral
	 */
	private double diffTrial;
	
	/**
	 * Represents the expected p value required
	 */
	private double expectedP;
	
	/**
	 * Empty constructor for class
	 */
	public XValueFinder(){
		this.degreesOfFreedom = 0;
		this.trialValue = 1.0;
		this.acceptedError = 0.0001;
		this.diffTrial = 0.5;
		this.integrator = new SimpsonRuleIntegration(10, trialValue, 
				acceptedError, degreesOfFreedom);
	}
	
	/**
	 * Parameter constructor for class
	 * @param dof	Degrees of freedom
	 * @param expectedP	Expected p value to evaluate against integration
	 * @param error	Accepted error for evaluation
	 */
	public XValueFinder(int dof, double expectedP, double error){
		this();
		this.degreesOfFreedom = dof;
		this.expectedP = expectedP;
		this.acceptedError = error;
		this.integrator = new SimpsonRuleIntegration(10, trialValue, 
				acceptedError, degreesOfFreedom);
	}
	
	public double findXValue() throws Exception{
		double pValue = integrator.getIntegralApproximation();  
		
		System.out.println("x: " + trialValue);
		System.out.println("Expected p: " + expectedP);
		System.out.println("Actual p: " + pValue);
		System.out.println("Error: " + (pValue - expectedP));
		
		return pValue;
	}

}
