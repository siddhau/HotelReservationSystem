package main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.Userdao.UserDAO;
import main.exception.HotelException;


@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		if(request.getParameter("signinup").equals("SignIn"))
		{
			UserDAO userDao = null;
			String username=request.getParameter("username");
			String passWord=request.getParameter("password");
			long userId = -1;
			userDao=new UserDAO();
			try{
				userId=userDao.isvalidUser(username,passWord);
				if(userId != -1)
				{	
					HttpSession session = request.getSession();
					session.setMaxInactiveInterval(30*60);
					//session.setMaxInactiveInterval(30*60);
					//response.sendRedirect("LoginSuccess.jsp");
					
					ArrayList<String>userDetails = new ArrayList<String>();
					userDetails = userDao.getUserDetails(userId);
					String name = userDetails.get(0);
					System.out.println(name);
					session.setAttribute("user",userDetails.get(0) );
					//Cookie userName = new Cookie("user", userDetails.get(0));
					//userName.setMaxAge(30*60);
					//response.addCookie(userName);
					session.setAttribute("userDet", userDetails);
					//response.sendRedirect("/UserPage.jsp");
					RequestDispatcher rd=request.getRequestDispatcher("/UserPage.jsp");
					rd.forward(request,response);
				}
				else
				{
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
					PrintWriter out= response.getWriter();
					out.println("<font color=red>Either user name or password is wrong.</font>");
					rd.include(request, response);
				}
			}
			catch(HotelException e)
			{
				request.setAttribute("message", e.getMessage());
				RequestDispatcher rd=request.getRequestDispatcher("/Error.jsp");
				rd.forward(request,response);
			}
		}

	}
}
