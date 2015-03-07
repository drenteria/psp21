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
	 * Stores the error get at the moment of the calculations
	 */
	private double currentError;
	
	/**
	 * Empty constructor for class
	 */
	public XValueFinder(){
		this.degreesOfFreedom = 0;
		this.trialValue = 1.0;
		this.acceptedError = 0.00001;
		this.diffTrial = 1.0;
		this.currentError = 0D;
		this.integrator = null;
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
	}
	
	/**
	 * This method evaluates the integration to find the closest <i>p</i>
	 * value for the given <i>x</i> value.
	 * @throws Exception
	 */
	public void findXValue() throws Exception {
		
		if(expectedP == 0D){
			throw new Exception("Expected p value must not be 0. Try again with a different value.");
		}
		
		System.out.println("Expected p: " + expectedP);
		System.out.println("DOF: " + degreesOfFreedom);
		
		integrator = new SimpsonRuleIntegration(10, trialValue, 
				acceptedError, degreesOfFreedom);
		double pValue = integrator.getIntegralApproximation();
		currentError = (pValue - expectedP);
		
		while(!isWithinRange(pValue)){
			adjustDiffValue(pValue, diffTrial);
			adjustTrialValue(pValue);
			integrator = new SimpsonRuleIntegration(10, trialValue, 
					acceptedError, degreesOfFreedom);
			pValue = integrator.getIntegralApproximation();
			currentError = (pValue - expectedP);
		}
		
		System.out.println("X value found: " + trialValue);
		System.out.println("Error obtained: " + currentError);
		System.out.println("--");
		
	}
	
	/**
	 * Evaluates whether or not the obtained integration value is
	 * within the range of accepted error
	 * @param p The obtained <i>p</i> value
	 * @return <code>true</code> if the value is within the error range.
	 * <code>false</code> otherwise
	 */
	private boolean isWithinRange(double p){
		if(Math.abs(p - expectedP) <= acceptedError){
			return true;
		}
		return false;
	}
	
	/**
	 * This method evaluates if the d value must be changed
	 * and changes it, if needed
	 * @param currentP	Current P value 
	 * @param diff	The current d value
	 * @throws Exception When d value has reached the 0 value and the error is still significant
	 */
	private void adjustDiffValue(double currentP, double diff) throws Exception{
		if(diffTrial == trialValue){
			diffTrial = 0.5;
			return;
		}
		if(currentP > expectedP){
			diffTrial = diff / 2;
		}
		if(diffTrial == 0D){
			throw new Exception("Diff (d) value has become 0. Significant error difference could not be reached");
		}
	}
	
	/**
	 * This method adjusts the x trial value to evaluate the values
	 * to integrate
	 * @param currentPValue	Current <i>p</i> integration value
	 * @throws Exception If the x trial value has become 0, the app
	 * could not continue
	 */
	private void adjustTrialValue(double currentPValue) throws Exception{
		if(currentPValue < expectedP){
			trialValue += diffTrial;
		}
		else {
			trialValue -= diffTrial;
		}
		if(trialValue == 0D){
			throw new Exception("X trial value has become 0. Unable to continue");
		}
	}
	
	/**
	 * Obtain the current X trial value for this instance
	 * @return The x trial value
	 */
	public double getXValue(){
		return trialValue;
	}
	
	/**
	 * Obtains the current error when calculating X value
	 * @return The current error
	 */
	public double getCurrentErrorValue(){
		return currentError;
	}

}
