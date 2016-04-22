package com.isaac.javaweb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletTest extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4140553978112858697L;

	/**
	 * 
	 */


	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		login(req, resp);
	}

	protected void login(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = null;
		String usernameFromCookie="";
		String user=request.getParameter("userName");
		String password=request.getParameter("userPassword");
		
		//create user name cookie
		Cookie usernameCookie=new Cookie("username",user);	
		usernameCookie.setMaxAge(10*30);
		response.addCookie(usernameCookie);
		
		//create session
		HttpSession session =request.getSession();
		session.setAttribute("password", password);
		String userpasswordFromsession=(String)session.getAttribute("password");
		
		if(userpasswordFromsession!=null)
		{
			session.invalidate();
		}
		
		//get cookies
		Cookie[] cookies= request.getCookies();
		
		if(cookies !=null)
		{
			for(Cookie cookie: cookies)
			{
				if(cookie.getName().equals("username"))
				{
					usernameFromCookie=cookie.getValue();
				}
			}

		
			try{
				if(!usernameFromCookie.equals(user))
				{		
					System.out.println(usernameFromCookie);
					System.out.println(user);
					PrintWriter writer = response.getWriter();
					writer.println("<html>");
					writer.println("<head><title>用户中心</title></head>");
					writer.println("<body>");
					writer.println("<p>用户名：" + user + "不正确</p>");			
					writer.println("</body>");
					writer.println("</html>");
					writer.close();
				}else{
					dispatcher = request.getRequestDispatcher("/success.html");
					dispatcher.forward(request, response);			
				}
			}catch(Exception e){
				e.printStackTrace();
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		}
		else
		{
			try{
		
					PrintWriter writer = response.getWriter();
					writer.println("<html>");
					writer.println("<head><title>用户中心</title></head>");
					writer.println("<body>");
					writer.println("<p>用户名：" + user + "第一次登陆</p>");			
					writer.println("</body>");
					writer.println("</html>");
					writer.close();

			}catch(Exception e){
				e.printStackTrace();
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}			
		}

		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		login(req, resp);
	}
	

}
