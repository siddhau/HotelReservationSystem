package main.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class DBConnection {

	private Connection con;
	public DBConnection()
		{
				
			       con=null;
			
		}
		
		public Connection getConnection()
		{	
			
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db0", "root", "root1234"); 
				
				 if (con != null) {
		             //System.out.println("Connected to the database1");
		         }
				 else
				 {
					 System.out.println("Not Connected");
				 }
		    
			}  catch (SQLException e) {
				
				
				System.out.println("SQL Exception "+e.getMessage());
			} catch( ClassNotFoundException e){
				System.out.println(e.getMessage());
		}
			
			return con;
		}

}


//<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" id="WebApp_ID" version="2.5">

