package main.controller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.Userdao.UserDAO;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("signinup").equals("SignUp"))
		{	
			
			UserDAO userDao = new UserDAO();
			String uMobile = request.getParameter("uMobileNo");
			String passWord = request.getParameter("password");
			String uName = request.getParameter("uName");
			String uEmail = request.getParameter("uEmail");
			String uCountry = request.getParameter("uCountry");
			int result = -1;
			result = userDao.isNewUser(uMobile, passWord, uName, uEmail, uCountry);
			if(result==1){
				RequestDispatcher rd = request.getRequestDispatcher("/SignUp.jsp");
				PrintWriter out= response.getWriter();
				out.println("<div align=center><font color=red>Account Created Successfully.</font></div>");
				rd.include(request, response);
			
			}
			else if(result == 2){
				RequestDispatcher rd = request.getRequestDispatcher("/SignUp.jsp");
				PrintWriter out= response.getWriter();
				out.println("<div align=center><font color=red>Account Already Exists.</font></div>");
				rd.include(request, response);
			}
			else{
				RequestDispatcher rd = request.getRequestDispatcher("/SignUp.jsp");
				PrintWriter out= response.getWriter();
				out.println("<div align=center><font color=red>Account Creation Unsuccessful! Try Again.</font></div>");
				rd.include(request, response);
			}
		}			
	}

}

