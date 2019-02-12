<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="main.bean.HotelBean"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservation Cart</title>
<script>
 function rowIndex1(x) {
	var idx = x.closest('tr').rowIndex;
	idx = idx - 1;
	idx = idx.toString();
  	document.getElementById('delRoom').value =  idx;
}
</script>


</head>
<body>
<center>
<h3>Reservation Cart</h3>
</center>
<form name="ReserveCart" action="HotelReservationContoller"  method="post">
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
		HttpSession newSession = request.getSession(false);
		ArrayList<HotelBean> rbList = (ArrayList<HotelBean>)newSession.getAttribute("ReservationCart");
		Iterator<HotelBean> HotelBeanIterator = rbList.iterator();
		
		int i=0;
		while (HotelBeanIterator.hasNext())

		{

			 rb = (HotelBean) HotelBeanIterator.next();
			
			  %>
		<tr>	
			<td><%= rb.getRoomNo()%></td>
			<td><%= rb.getRoomType()%></td>
			<td><input id="delRoom" type="hidden" value="0" name="cancelBooking"/><input type="submit" onclick="rowIndex1(this);" value="delete" name="button"></td> 
		</tr>
	<%
		i++;
		}
	%>
<tr><td><input type="submit" value="Confirm Booking" name="button"/>
	<input type="submit" value="Delete All" name="button"/></td></tr>
</table>
</form>
</body>
</html>