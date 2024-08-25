package com.myparking.vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myparking.dbconfig.DbConnection;

public class VehicleRepo {
	
	DbConnection dbConnection =new DbConnection();
	
	public List<Vehicle> getAllVehicle() throws SQLException{
		
		 List<Vehicle> vehicleList = new ArrayList<>();
		 
		 String sql = "select * from vehicle";
		 
		 try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
				try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					Vehicle vehicle = new Vehicle();
					vehicle.setVehicleid(resultSet.getInt("vehicleid"));
					vehicle.setVehicle_type(resultSet.getString("vehicle_type"));
					vehicle.setCost(resultSet.getFloat("cost"));
					vehicleList.add(vehicle);
				}
			 }
		}
		
		return vehicleList;
		
	}
	
	public Vehicle getListById( int vehicleId ) throws SQLException{
		
		Vehicle vehicle = null; 
		 
		 String sql = "select * from vehicle where vehicleid = ? ";
		 
		 try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			 stmt.setInt(1, vehicleId);	
			 try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					vehicle = new Vehicle();
					vehicle.setVehicleid(resultSet.getInt("vehicleid"));
					vehicle.setVehicle_type(resultSet.getString("vehicle_type"));
					vehicle.setCost(resultSet.getFloat("cost"));
				}
			 }
		}
		
		return vehicle;
		
	}
	
	public void storeVehicle(Vehicle vehicle) throws Exception {
			
			/** Insert vehicle **/
			String sql = "INSERT INTO vehicle (vehicle_type,cost) VALUES(? ,?)";
			Connection connection =  dbConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql); 
			statement.setString(1, vehicle.getVehicle_type());
			statement.setFloat(2, vehicle.getCost());
			statement.executeUpdate();
			connection.close();      
	}

}
