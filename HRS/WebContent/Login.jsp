<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotel Reservation System</title>
<script type="text/javascript" src="HotelValidation.js">
	 </script>
</head>
<body>
<form action="HotelReservationContoller" method="get">
<div align=center>
	UserName : <input type="text" name="username" ></input><br/>
	Password :<input type="password" name="password"></input><br/>
		<input type="submit" value="SignIn" name="signinup" ><br/>
	
		<a href = "SignUp.jsp"> Don't have an account ? Sign up </a>
</div>
</form>

</body>
</html>