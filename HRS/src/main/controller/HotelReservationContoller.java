package main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.bean.HotelBean;
import main.dao.HoteDAO;
import main.Userdao.UserDAO;
import main.exception.HotelException;

public class HotelReservationContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//ArrayList<HotelBean> rbList= null;
	//ArrayList<HotelBean>availableRooms = new ArrayList<HotelBean>();
	
       
   
    public HotelReservationContoller() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				{	HttpSession session= null;
					session = request.getSession();
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
		else if(request.getParameter("signinup").equals("SignUp"))
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
		else if(request.getParameter("signinup").equals("Log Out")){
			HttpSession session = request.getSession(false);
			String sessionID = session.getId();
			System.out.println("The session getting invalidated is = = " + sessionID);
			session.invalidate();
			response.sendRedirect("Login.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ArrayList<HotelBean> rbList= null;
		ArrayList<HotelBean>availableRooms = null;
		if((ArrayList<HotelBean>)session.getAttribute("ReservationCart") != null){
			rbList = (ArrayList<HotelBean>)session.getAttribute("ReservationCart");
		}
		if((ArrayList<HotelBean>)session.getAttribute("availableRooms") != null){
			availableRooms = (ArrayList<HotelBean>)session.getAttribute("availableRooms");
		}
		String ids = session.getId();
		System.out.println("HotelReservation  " + ids);
		HotelBean rb=new HotelBean();
		
		if(request.getParameter("button").equals("addRoom"))
		{	System.out.println("******************inside addRoom*****************");
			if(session!=null)
			{	
				availableRooms = (ArrayList<HotelBean>)session.getAttribute("availableRooms");
				
				int addPos = Integer.parseInt(request.getParameter("roomPosition"));
				System.out.println("Requested Pos is : " +  addPos);
				rb = availableRooms.get(addPos);
				String roomID = rb.getRoomId();
				HoteDAO hd = new HoteDAO();
				boolean available = hd.isAvailable(roomID);
				if(available){
					if((ArrayList<HotelBean>)session.getAttribute("ReservationCart") == null){
						rbList = new ArrayList<HotelBean>();
					}
					if(rbList == null){
						rbList = new ArrayList<HotelBean>();
					}
					rbList.add(rb);
					for(HotelBean room : rbList){
						System.out.println(room.getRoomId());
					}
					System.out.println(rbList.size());
					session.setAttribute("ReservationCart",rbList);
					RequestDispatcher rd=request.getRequestDispatcher("/AvailableRooms.jsp");
					rd.forward(request,response);
				}
				else
				{
					System.out.println("Sorry the room is not available now");
					RequestDispatcher rd=request.getRequestDispatcher("/AvailableRooms.jsp");
					rd.forward(request,response);
				}
			}
			
		}
		else if(request.getParameter("button").equals("delete"))
		{
			System.out.println("******************inside delete*****************");
			if(session!=null)
			{ 
				
				rbList=(ArrayList<HotelBean>)session.getAttribute("ReservationCart");
				Iterator<HotelBean> iterator1=rbList.iterator();
				int delcount=Integer.parseInt(request.getParameter("cancelBooking")); 
				System.out.println(delcount);
				int count=0;
				HoteDAO hd = new HoteDAO();
				while(iterator1.hasNext())
				{
					HotelBean rb1=(HotelBean)iterator1.next();
					if(count==delcount)
					{
						iterator1.remove();
						hd.setStatus(rb1.getRoomId(), "Available");
						
					}
					count++;
				}
				session.setAttribute("ReservationCart",rbList);
				RequestDispatcher rd=request.getRequestDispatcher("/AvailableRooms.jsp");
				rd.forward(request,response);
			}
		}
		else if(request.getParameter("button").equals("Delete All"))  //can be done in O(1) but then change of room status not possible
		{
			
			if(session!=null)
			{	HoteDAO hd = new HoteDAO();
				rbList=(ArrayList<HotelBean>)session.getAttribute("ReservationCart");
				Iterator<HotelBean> iterator_del = rbList.iterator();
				while(iterator_del.hasNext()){
					HotelBean hb = new HotelBean();
					hb = (HotelBean)iterator_del.next();
					hd.setStatus(hb.getRoomId(), "Available");
					iterator_del.remove();
					
				}
				session.setAttribute("ReservationCart",rbList);
				RequestDispatcher rd=request.getRequestDispatcher("/AvailableRooms.jsp");
				rd.forward(request,response);
			}
		}
		else if(request.getParameter("button").equals("Confirm Booking")) // Entry to selected rooms in database
		{
			rbList=(ArrayList<HotelBean>)session.getAttribute("ReservationCart");
			HoteDAO hotelDao=null;
			boolean result=false;
			String id=null;
			if(rbList!=null)
			{
				hotelDao=new HoteDAO();
				try 
				{
					//id=hotelDao.getId();
					id = "test1";
					result=hotelDao.addValues(rbList,id);
					if(result)
					{
						rbList.clear();
						System.out.println("values added");
						request.getRequestDispatcher("/Results.jsp").forward(request,response);
						
					}
					else
					{
						String mesg="Values entry failed";
						request.setAttribute("message",mesg);
						RequestDispatcher rd=request.getRequestDispatcher("/Error.jsp");
						rd.forward(request,response);
					}
				} catch (HotelException e) 
				{
					//session.setAttribute("message", e.getMessage());
					request.setAttribute("message", e.getMessage());
					RequestDispatcher rd=request.getRequestDispatcher("/Error.jsp");
					rd.forward(request,response);
				}
				
			}
			else
			{
				String message="No value";
				request.setAttribute("message",message);
				RequestDispatcher rd=request.getRequestDispatcher("/Error.jsp");
				rd.forward(request,response);
			}
			
			
		}
		else if(request.getParameter("button").equals("checkAvailability"))
		{	DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");; 
			Date checkin=null;
			Date checkout = null;
			try{
				 checkin = (Date)formatter.parse(request.getParameter("checkin"));
				checkout = (Date)formatter.parse(request.getParameter("checkout"));
			}
			catch(ParseException e){
				System.out.println("Error in date parsing" + e.getMessage());
			}
			//System.out.println(checkin+"   "+checkout);
			java.sql.Date checkIn = new java.sql.Date(checkin.getTime());
			java.sql.Date checkOut = new java.sql.Date(checkout.getTime());
			try{
				availableRooms = new HoteDAO().fetchHotel(checkIn,checkOut,"hot1");
				session.setAttribute("availableRooms", availableRooms);
				RequestDispatcher rd = request.getRequestDispatcher("AvailableRooms.jsp");
				rd.forward(request, response);
			}
			catch(HotelException e){
				System.out.println("can't fetch available hotels" + e.getMessage());
			}
		}
		
		
		
		
		
	}

}
