package main.dao;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import main.bean.HotelBean;
import main.exception.HotelException;



public class HoteDAO {
	
	
	public ArrayList<HotelBean> fetchHotel(Date checkIn,Date checkOut,String hotelId) throws HotelException
	{
		ArrayList<HotelBean> availableHotels= new ArrayList<HotelBean>();
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try
		{
		try
		{
		connection = new DBConnection().getConnection();
		if (connection != null) 
		{
			String query="select r.roomId ,r.roomNo ,r.roomType ,r.hotelId from roomDB as r where "
					+ "r.hotelId = ? and r.currStatus = \"Available\" and r.roomId NOT IN (select rv.roomID from reservation as rv where rv.hotelId = ? and (NOT (rv.checkIn>=? or rv.checkOut<=?)))";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, hotelId);
			preparedStatement.setString(2, hotelId);
			preparedStatement.setDate(3, checkOut);
			preparedStatement.setDate(4, checkIn);
			resultSet=preparedStatement.executeQuery();
			HotelBean hb = null;
		while(resultSet.next())
		{	hb = new HotelBean();
			hb.setRoomId(resultSet.getString(1));
			hb.setRoomNo(resultSet.getInt(2));
			hb.setRoomType(resultSet.getString(3));
			hb.setHotelId(resultSet.getString(4));
			availableHotels.add(hb);
			
		}

		}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally
		{
			if(resultSet!=null)
			{
				resultSet.close();
			}
			if(preparedStatement!=null)
			{
				preparedStatement.close();
			}
			if(connection!=null)
			{
				connection.close();
			}
			
		}
		}
		catch(SQLException exception)
		{
			throw new HotelException("Sql connection problem");

		} 
		//System.out.println("****** THE SIZE OF AVAILABLE HOTELS ****" + availableHotels.size());
		return availableHotels;
	}
	
	public ArrayList fetchExistingBookings(){
		ArrayList list = null;
		return list;
	}
	
	public String getId() throws HotelException
	{
		String id=null;
			Connection connection = null;
			ResultSet resultSet = null;
			PreparedStatement preparedStatement = null;
			try
			{
				connection = new DBConnection().getConnection();	
				String sqlStmt="SELECT BOOKING_ID from hotel_booking_802366";
				preparedStatement = connection.prepareStatement(sqlStmt);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next())
				{
					id=resultSet.getString(1);

				}
			}
			catch(SQLException exception)
			{
				throw new HotelException("Sql connection problem");

			} 
		return id;
	}
	public boolean addValues(ArrayList<HotelBean> rbList,String id) throws HotelException
	{
		
		boolean result=false;
		Date sqlDate=null;
		Connection connection = null;
		ResultSet resultSet = null;
		
			connection = new DBConnection().getConnection();	
			Iterator<HotelBean> iterator=rbList.iterator();

			while(iterator.hasNext())
			{	
				HotelBean bean=iterator.next();
				String hotelId=null;
				
				
			}
			
	

		return result;
		
	}
	
	public boolean isAvailable(String roomId){
		boolean result = false;
		try{
			Connection connection = new DBConnection().getConnection();
			String query =" select currStatus from roomDB where roomID = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, roomId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getString(1).equals("Available")){
					result = true;
					System.out.println("Room Available");
					query =" update roomDB  set currStatus=\"Pending\" where roomID = ?";
					ps = connection.prepareStatement(query);
					ps.setString(1, roomId);
					ps.executeUpdate();
				}
			}
		}
		catch(SQLException e){
			System.out.println("Can't get current statusess" + e.getMessage());
		}
		return result;
		
	}
	
	public void setStatus(String roomId,String status){
		try{
				Connection connection = new DBConnection().getConnection();
				String query ="update roomDB  set currStatus=? where roomID = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1,status);
				ps.setString(2, roomId);
				ps.executeUpdate();
				
			}
			catch(SQLException e){
				System.out.println("Can't get current status" + e.getMessage());
			}
		
	}


}
