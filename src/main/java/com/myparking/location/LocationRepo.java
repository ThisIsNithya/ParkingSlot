package com.myparking.location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myparking.dbconfig.DbConnection;

public class LocationRepo {

	DbConnection dbConnection =new DbConnection();

	public List<Location> getAllLocation() throws SQLException{
		
		 List<Location> locationList = new ArrayList<>();
		 
		 String sql = "select * from locations";
		 
		 try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
				try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					Location location = new Location();
					location.setLocationid(resultSet.getInt("locationid"));
					location.setLocation_name(resultSet.getString("location_name"));
					location.setArea(resultSet.getString("area"));
					location.setSlots(resultSet.getInt("slots"));
					locationList.add(location);
				}
			 }
		}
		
		return locationList;
		
	}

	public Location getListById( int locationId ) throws SQLException{
		 
		Location location = null; 
		 
		 String sql = "select * from locations where locationid = ? ";
		 
		 try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			 stmt.setInt(1, locationId);	
			 try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					location = new Location();
					location.setLocationid(resultSet.getInt("locationid"));
					location.setLocation_name(resultSet.getString("location_name"));
					location.setArea(resultSet.getString("area"));
					location.setSlots(resultSet.getInt("slots"));
					System.out.println(location);
				}
			 }
		}
		
		return location;
		
	}
	
	public void storeLocation(Location location) throws Exception {
			
			/** Insert Locations **/
			String sql = "INSERT INTO locations (location_name,area,slots) VALUES(? ,? , ?)";
			Connection connection =  dbConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); 
			statement.setString(1, location.getLocation_name());
		    statement.setString(2, location.getArea());
		    statement.setInt(3, 0);
			statement.executeUpdate();
			connection.close();      
	}
}
