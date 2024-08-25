package com.myparking.member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.myparking.dbconfig.DbConnection;

public class MyBookingDao {

	DbConnection dbConnection =new DbConnection();
	
	
	public List<MyBooking> getMyBooking(String emailId) throws SQLException {
		
	    List<MyBooking> mybookings = new ArrayList<>();
	    
	    
        
        
	    String sql = "SELECT bookings.bookingid,bookings.email,bookings.vehicle_no,bookings.vehicle_type,bookings.date,slots.slotno,locations.location_name,bookings.date,bookings.time,bookings.cost,bookings.paid,bookings.duration FROM  bookings \r\n"
	    		+ "join slots on slots.slotid = bookings.slotid\r\n"
	    		+ "join locations on locations.locationid = bookings.locationid\r\n"
	    		+ "where bookings.email = ?";
	    try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, emailId);
	        try (ResultSet resultSet = stmt.executeQuery()) {
	        while (resultSet.next()) {
	        	MyBooking mybooking = new MyBooking();
	        	mybooking.setBookingid(resultSet.getInt("bookingid"));
	        	mybooking.setEmail(resultSet.getString("email"));
	        	mybooking.setVehicle_type(resultSet.getString("vehicle_type"));
	        	mybooking.setVehicle_no(resultSet.getString("vehicle_no"));
	        	mybooking.setLocationName(resultSet.getString("location_name"));
	        	mybooking.setSlotName(resultSet.getString("slotno"));
	        	mybooking.setDate(resultSet.getDate("date"));
	        	mybooking.setTime(resultSet.getString("time"));
	        	mybooking.setDuration(resultSet.getFloat("duration"));
	        	mybooking.setCost(resultSet.getDouble("cost"));
	        	mybooking.setPaid(resultSet.getInt("paid"));
	        	mybookings.add(mybooking);
	        }
	     }
	    }
	    return mybookings;
	    
	}
	
	public List<MyBooking> getAllBooking() throws SQLException {
		
	    List<MyBooking> mybookings = new ArrayList<>();
	    
	    
        
        
	    String sql = "SELECT bookings.bookingid,bookings.email,bookings.vehicle_no,bookings.vehicle_type,bookings.date,slots.slotno,locations.location_name,bookings.date,bookings.time,bookings.cost,bookings.paid,bookings.duration FROM  bookings \r\n"
	    		+ "join slots on slots.slotid = bookings.slotid\r\n"
	    		+ "join locations on locations.locationid = bookings.locationid\r\n";
	    try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        try (ResultSet resultSet = stmt.executeQuery()) {
	        while (resultSet.next()) {
	        	MyBooking mybooking = new MyBooking();
	        	mybooking.setBookingid(resultSet.getInt("bookingid"));
	        	mybooking.setEmail(resultSet.getString("email"));
	        	mybooking.setVehicle_type(resultSet.getString("vehicle_type"));
	        	mybooking.setVehicle_no(resultSet.getString("vehicle_no"));
	        	mybooking.setLocationName(resultSet.getString("location_name"));
	        	mybooking.setSlotName(resultSet.getString("slotno"));
	        	mybooking.setDate(resultSet.getDate("date"));
	        	mybooking.setTime(resultSet.getString("time"));
	        	mybooking.setDuration(resultSet.getFloat("duration"));
	        	mybooking.setCost(resultSet.getDouble("cost"));
	        	mybooking.setPaid(resultSet.getInt("paid"));
	        	mybookings.add(mybooking);
	        }
	     }
	    }
	    return mybookings;
	    
	} 

	public void bookMemberSlot(MyBooking mybooking) throws Exception {
		
		Date currentDate = new Date(System.currentTimeMillis());
		
		/** Insert Booking **/
		String sql = "INSERT INTO bookings (email,vehicle_type,vehicle_no,locationid,slotid,date,time,duration,cost,paid) VALUES(? ,? ,? ,? ,?, ? ,?,?,?,?)";
		Connection connection =  dbConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql); 
		
		statement.setString(1, mybooking.getEmail());
	    statement.setString(2, mybooking.getVehicle_type());
	    statement.setString(3, mybooking.getVehicle_no());
	    statement.setInt(4, mybooking.getLocationid());
	    statement.setString(5, mybooking.getSlotid());
	    statement.setDate(6, currentDate);
	    statement.setString(7, mybooking.getTime());
	    statement.setFloat(8, mybooking.getDuration());
		statement.setDouble(9, mybooking.getCost());
	    statement.setFloat(10, mybooking.getPaid());
		
		statement.executeUpdate();
		
		/** Update Slot **/
		String sql1 = "UPDATE slots SET status = ? WHERE slotid = ? and locationid = ?";
	    PreparedStatement statement1 = connection.prepareStatement(sql1);
		statement1.setInt(1, 1);
		statement1.setString(2, mybooking.getSlotid());
		statement1.setInt(3, mybooking.getLocationid());
		statement1.executeUpdate();
		

		/** Update Slot Count **/
		String sql2 = "UPDATE locations SET slots = slots-1 WHERE locationid = ?";
	    PreparedStatement statement2 = connection.prepareStatement(sql2);
		statement2.setInt(1, mybooking.getLocationid());
		statement2.executeUpdate();
		connection.close();      
	}
	
	
}
