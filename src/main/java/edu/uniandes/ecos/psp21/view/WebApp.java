package edu.uniandes.ecos.psp21.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import edu.uniandes.ecos.psp21.controller.WebController;

/**
 * This class acts as the Web view to interact with users
 * @author drenteria
 *
 */
public class WebApp extends HttpServlet {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Main method, executed by Heroku and Foreman to start a web server and
	 * deploy the web application
	 * @param args Arguments String array to execute app
	 */
	public static void main(String[] args) {
		Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new WebApp()), "/*");
        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(WebApp.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	/**
	 * This method processes the GET request from server
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse rsp){
		try {
			WebController.showInputForm(req, rsp);
		} catch (IOException e) {
			Logger.getLogger(WebApp.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
	}
	
	/**
	 * This method processes the POST request from server
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse rsp){
		try{
			Integer dof = Integer.valueOf(req.getParameter("dof"));
			Double pValue = Double.valueOf(req.getParameter("pvalue"));
			Double allError = Double.valueOf(req.getParameter("error"));
			WebController.showResultsForX(req, rsp, dof, pValue, allError);
		}
		catch (Exception e) {
			Logger.getLogger(WebApp.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
	}

}
