<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SignUP</title>
</head>
<body>
<form action="HotelReservationContoller" method="get">
<div align=center>
	
	Mobile No: <input type="text" name="uMobileNo" required></input><br/>
	Password :<input type="password" name="password" required></input><br/>
	Name	 : <input type="text" name="uName" required></input><br/>
	Email    : <input type="text" name="uEmail" required></input><br/>
	Country: <input type="text" name="uCountry" required></input><br/>
		<input type="submit" value="SignUp" name="signinup"><br/>
	<a href = "Login.jsp"><h5>Login Page</h5></a>
</div>
</form>
</body>
</html>