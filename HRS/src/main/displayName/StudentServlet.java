package main.displayName;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();

    }
    protected void processRequest ( HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		
    	response.setContentType("text/html;charset=UTF-8");
		try{
//			out.println("<!DOCTYPE html>");
//			out.println("<html>");
//			out.println("<head>");
//			out.println("<title> Display Student Details </title>");
//			out.println("</head>");
//			out.println("<body>");
			
			ArrayList<Student> std = new ArrayList<Student>();
			
			std.add(new Student(22,"Siddhau","BE in IT"));
			std.add(new Student(23,"Saakshi","BE in IT"));
			std.add(new Student(22,"Prabodh","BE in CSE"));
//			for(Student s : std) {		
//				System.out.println(s.getAge()  + "    " + s.getCourse() + "   " + s.getName());
//			 } 
			request.setAttribute("details", std);
			
			RequestDispatcher rd = request.getRequestDispatcher("display.jsp");
			rd.forward(request,response);
			
//			out.println("/body");
//			out.println("/html");
//			
		}
		finally{
			
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

}
