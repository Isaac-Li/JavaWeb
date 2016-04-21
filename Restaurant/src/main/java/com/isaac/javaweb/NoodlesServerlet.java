package com.isaac.javaweb;

import java.io.IOException;
import java.io.PrintWriter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoodlesServerlet extends HttpServlet{
	
	   private static Logger logger= Logger.getLogger(NoodlesServerlet.class);
		
	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        PrintWriter writer = response.getWriter();
	        String vegetable = request.getParameter("vegetable");
	        logger.info("加入了"+vegetable);
	        if (vegetable == null) {
	            vegetable = "Tomato";
	            logger.info("加入了西红柿");
	        }

	        writer.println("<html><body>");
	        writer.println("<h1> Noodles with " + vegetable + "</h1>"); 
	        writer.println("</body></html>");
	    }

}
