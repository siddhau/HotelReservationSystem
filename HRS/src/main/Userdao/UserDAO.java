package main.Userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.dao.DBConnection;

import main.exception.HotelException;

public class UserDAO {
	
public int isNewUser(String uMobile,String  passWord,String  uName,String  uEmail,String  uCountry){
		
		int result = 0;
		Connection connection = null;
		try{
			connection = new DBConnection().getConnection();
			String query = "select pass from User_login where userMobile = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, uMobile);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				result = 2;
			}
			else{
				query = "insert into User_login(userMobile,pass) values(?,?)";
				ps = connection.prepareStatement(query);
				ps.setString(1, uMobile);
				ps.setString(2, passWord);
				ps.executeUpdate();
				query = "insert into User_details(uName,Email,Country) values(?,?,?)";
				ps = connection.prepareStatement(query);
				ps.setString(1, uName);
				ps.setString(2, uEmail);
				ps.setString(3, uCountry);
				ps.executeUpdate();
				result = 1;
			}
			
		}
		catch(SQLException e){
			System.out.println("some sql problem in newUserEntry " + e.getMessage());
		}
		return result;
	}
	public long isvalidUser(String userMobile,String passWord) throws HotelException  
	{
		long userId = -1;
		DBConnection connectionGetter = null;
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		
		String newPassword=null;
		try
		{
			try
			{	
				connectionGetter = new DBConnection();
				connection = connectionGetter.getConnection();
				preparedStatement = connection.prepareStatement("select pass,userId from User_login where userMobile=?");
				preparedStatement.setString(1,userMobile);
				resultSet=preparedStatement.executeQuery();
				if(resultSet.next())
				{
					newPassword=resultSet.getString(1);
					
					if(passWord.equals(newPassword))
					{
						userId = resultSet.getInt("UserId");

					}
				}
				else
				{
					userId = -1;
				}

			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(resultSet != null)
					resultSet = null;
				if(preparedStatement != null)
					preparedStatement.close();
				if(connection != null)
					connection.close();
			}
		}
		catch(SQLException exception)
		{
			throw new HotelException("Sql connection problem");

		} 
		
		return userId;
	}
	
	public ArrayList<String> getUserDetails(long userId){
		ArrayList<String> userDetails = new ArrayList<String>();
		try
		{
			Connection connection = new DBConnection().getConnection();
			String query = "select uName,Email,Country from User_details where user_Id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				userDetails.add(rs.getString(1));
				userDetails.add(rs.getString(2));
				userDetails.add(rs.getString(3));
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("Error in fetching user details" + e.getMessage());
		}
		
		return userDetails;
	}

}
