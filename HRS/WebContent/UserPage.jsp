<%@ include file="header1.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="main.bean.ReservationBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
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
} */

%>
	<h3>The session id is <%= sessionID %></h3>
	<% 
		ArrayList<String> userDetails = (ArrayList<String>)session.getAttribute("userDet");
	 %>
	<h2 align=center><%= userDetails.get(0)%></h2>	
	<div align=center><h5>Email : <%= userDetails.get(1) %></h5><pre></pre><h5>Country : <%= userDetails.get(2) %></h5>
	<a href="EnquiryPage.jsp" ><button>Enquiry Page</button></a></div> <br>
	
	
	<%-- <%
if(newSession!=null)
{
	ArrayList<ReservationBean> list1=null;

	list1=(ArrayList<ReservationBean>)newSession.getAttribute("ReservationCart");
	
		newSession.setAttribute("ReservationCart",list1);
	%>
	<jsp:include page="CurrentBookings.jsp" flush="true"/>
	<%
	
}
%> --%>
</form>
</body>
</html>