package edu.uniandes.ecos.psp21.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uniandes.ecos.psp21.model.XValueFinder;

/**
 * This class acts as the controller for the MVC pattern
 * to reflect the user view for the Webb deploy of this application 
 * @author drenteria
 *
 */
public class WebController {
	
	/**
	 * This method sends to the response of the given request the printing
	 * of the input data form
	 * @param req The incoming HTTP request
	 * @param rsp The outgoing HTTP response
	 * @throws IOException
	 */
	public static void showInputForm(HttpServletRequest req, HttpServletResponse rsp) 
	throws IOException{
		String formString = "<html>" +
				"<body>" +
				"<h1>PSP21 X Value finder in T-Distribution using Simpson Rule Integration!</h1>" +
				"<p>Please, write needed numeric parameters to calculate X value.</p>" +
				"<form action=\"findx\" method=\"post\"><br/>" +
				"Degrees of Freedom: <input type=\"text\" name=\"dof\"><br/>" +
				"Expected P Value: <input type=\"text\" name=\"pvalue\"><br/>" +
				"Allowed Error (E): <input type=\"text\" name=\"error\"><br/>" +
				"<input type=\"submit\" value=\"Find X!\">" +
				"</body>" +
				"</html>";
		PrintWriter writer = rsp.getWriter();
		writer.write(formString);
	}
	
	/**
	 * This method makes the required calculations and prints the results
	 * in the 
	 * @param req
	 * @param rsp
	 * @param dof
	 * @param expectedP
	 * @param allowedError
	 * @throws Exception
	 */
	public static void showResultsForX(HttpServletRequest req, HttpServletResponse rsp, 
			Integer dof, Double expectedP, Double allowedError) 
	throws Exception {
		
		XValueFinder xFinder = new XValueFinder(dof, expectedP, allowedError);
		xFinder.findXValue();
		double xValue = xFinder.getXValue();
		double error = xFinder.getCurrentErrorValue();
		String formString = "<html>" +
				"<body>" +
				"<h1>PSP21 X Value finder in T-Distribution using Simpson Rule Integration!</h1>" +
				"<p>X value find results:.</p>" +
				"Degrees of Freedom: " + String.valueOf(dof) + "<br/>" +
				"Expected P Value: " + String.valueOf(expectedP) + "<br/>" +
				"Allowed Error (E): " + String.valueOf(allowedError) + "><br/>" +
				"Calculated Error: " + String.valueOf(error) + "><br/>" +
				"X closest value found: " + String.valueOf(xValue) + "><br/>" +
				"</body>" +
				"</html>";
		PrintWriter writer = rsp.getWriter();
		writer.write(formString);
		
	}

}
