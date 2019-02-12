<%@ include file="header1.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="main.bean.HotelBean" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rooms Available</title>
<script>
function rowIndex(x) {
		var idx = x.closest('tr').rowIndex;
		idx = idx - 1;
		idx = idx.toString();
	  	document.getElementById('posRoom').value =  idx;
	}
</script>
</head>
<body>
<center>
<h3>Rooms Available</h3>
</center>
<form name="availableRooms" action="HotelReservationContoller" method="post">
<table border="1" align="center">
	<thead bgcolor="#FDE6B5">
		<font color="white">
		<tr>
			<td>Room Number</td>
			<td>Room Type</td>
			<td></td>
		</tr>
		</font>
	</thead>
	<%
	  HotelBean rb;
		HttpSession newSession=request.getSession(false);
		ArrayList<HotelBean> availableRooms = (ArrayList<HotelBean>)session.getAttribute("availableRooms");
		Iterator<HotelBean> HotelBeanIterator = availableRooms.iterator();
		
		int i=0;
		while (HotelBeanIterator.hasNext())
		{	
			 rb = (HotelBean) HotelBeanIterator.next();
			
			  %>
		<tr>	
			<td><%= rb.getRoomNo()%></td>
			<td><%= rb.getRoomType()%></td>
			<td><input id="posRoom" type="hidden" value="0" name="roomPosition"/><input type="submit" onclick="rowIndex(this);" value="addRoom" name="button"></td>	
 		</tr>
	<%
		i++;
		}
	%>	
<!-- <input type="submit" value="addRoom" name="button">
 --></table>

 <%
if(newSession!=null)
{
	ArrayList<HotelBean> pickedRooms=null;

	pickedRooms=(ArrayList<HotelBean>)newSession.getAttribute("ReservationCart");
	
		newSession.setAttribute("ReservationCart",pickedRooms);
	%>
	<% if(pickedRooms != null){%>
	<jsp:include page="ReserveCart.jsp" flush="true"/>
	<%} %>
	}
	<%}%> 
</form>

</body>
</html>