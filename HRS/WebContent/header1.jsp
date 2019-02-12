<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<head>
  <title>User - <%= (String)session.getAttribute("user") %></title>
</head>
<body>
 <% if(session.getAttribute("user") == null)
	  
response.sendRedirect("error.html");
	  %> 
<div style="margin-right : 0px;">
		<form action="HotelReservationContoller" method="get">
			<input type="submit" value="Log Out" name="signinup" ><br/>
		</form>
	</div>
</body>
</html>
  