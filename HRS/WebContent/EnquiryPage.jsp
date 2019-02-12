<%@ include file="header1.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@page import="java.util.ArrayList" %>
<%@page import="main.bean.ReservationBean" %>
<html> 
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<title>reservation Page</title>
	</head>

<body>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("user") == null){
	response.sendRedirect("Login.html");
}else user = (String) session.getAttribute("user");
String userName = null;
String sessionID = session.getId();
/* Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
} */
%>
	<h3>The session id is <%= sessionID %></h3></br></br>
	<h3 align=center>Hotel Enquiry Page</h3>
	<form action="HotelReservationContoller" method="post">
		<div align=center style="margin-top: 200px;">
		  	 Check-In : <input type="date" name="checkin"  placeholder="Check-in" />
		  	Check-Out : <input type="date" name="checkout"  placeholder="Check-out"/>
		  	<input type="submit"  name="button" value="checkAvailability" >
	  	 </div>
	  	 
	  	 
	<%--   	 <%
		if(newSession!=null)
		{
			ArrayList<ReservationBean> availableRooms=null;
		
			availableRooms=(ArrayList<ReservationBean>)newSession.getAttribute("availableRooms");
			
				newSession.setAttribute("availableRooms",availableRooms);
			%>
			<jsp:include page="AvailableRooms.jsp" flush="true"/>
			<%
			
		}
		%> --%>
	  	 
	 </form>

</body>