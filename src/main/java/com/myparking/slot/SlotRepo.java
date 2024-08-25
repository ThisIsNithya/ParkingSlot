package com.myparking.slot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myparking.dbconfig.DbConnection;
import com.myparking.location.Location;
import com.myparking.location.LocationRepo;

public class SlotRepo {
	
	DbConnection dbConnection =new DbConnection();

	public List<Slot> getAllSlot() throws SQLException{
		
		 List<Slot> slotList = new ArrayList<>();
		 
		 String sql = "select slots.*,locations.location_name from slots join locations on locations.locationid = slots.locationid";
		 
		 try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
				try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					Slot slot = new Slot();
					slot.setSlotid(resultSet.getInt("slotid"));
					slot.setLocationid(resultSet.getInt("locationid"));
					slot.setSlotno(resultSet.getString("slotno"));
					slot.setStatus(resultSet.getInt("status"));
					slot.setLocation(resultSet.getString("location_name"));
					slotList.add(slot);
				}
			 }
		}
		
		return slotList;
		
	}

	public Slot getListById( int slotId ) throws SQLException{
		
		Slot slot = null; 
		 
		 String sql = "select * from slots where slotid = ? ";
		 
		 try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			 stmt.setInt(1, slotId);	
			 try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					slot.setSlotid(resultSet.getInt("slotid"));
					slot.setLocationid(resultSet.getInt("locationid"));
					slot.setSlotno(resultSet.getString("slotno"));
					slot.setStatus(resultSet.getInt("status"));
				}
			 }
		}
		
		return slot;
		
	}
	
	
	public List<Map<String, Object>> getAllAvailableSlots() throws SQLException{
		
		 List<Map<String, Object>> resultsFinal = new ArrayList<>();  // Final Slots details based on location grouped
         
		 List<Map<String, Object>> results = new ArrayList<>();
		 String sql = "SELECT slots.slotid,slots.slotno,slots.status,locations.locationid,locations.area,locations.location_name,locations.slots FROM slots\r\n"
		 		+ "join locations on locations.locationid = slots.locationid";
		 
		 try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
				try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					Map<String, Object> availbaleSlots = new HashMap<>();

					availbaleSlots.put("slotid", resultSet.getString("slotid"));
					availbaleSlots.put("slotno", resultSet.getString("slotno"));
					availbaleSlots.put("status", resultSet.getInt("status"));
					availbaleSlots.put("location_name", resultSet.getString("location_name"));
					availbaleSlots.put("area", resultSet.getString("area"));
					availbaleSlots.put("locationid", resultSet.getString("locationid"));
					availbaleSlots.put("slots", resultSet.getInt("slots"));
					results.add(availbaleSlots);
				}
			 }
			 LocationRepo location = new LocationRepo();
			 List<Location> locationList = location.getAllLocation();  // Get All Location
			 
			 
			 for (Location location1 : locationList) {
				 ArrayList locationBasedArray = new ArrayList<>();
				 Map<String, Object> detailedList = new HashMap<>();
				 String str1 = location1.getLocation_name();
				 for (Map<String, Object> map : results) {
					 String str2 = (String) map.get("location_name");
					 if(str1.equals(str2)) {
						 locationBasedArray.add(map);
					 }
				 }
				 detailedList.put("location_id", location1.getLocationid());
				 detailedList.put("location_name", str1);
				 detailedList.put("slot", location1.getSlots());
				 detailedList.put("address", location1.getArea());
				 detailedList.put("available_slots", locationBasedArray);
				 resultsFinal.add(detailedList);
			 }
		}
		return resultsFinal;
		
	}
	
	public List<Slot> getAllSlotByLocation( int locationId) throws SQLException{
		
		 List<Slot> slotList = new ArrayList<>();
		 
		 String sql = "select * from slots where locationid = ?";
		 
		 try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			   stmt.setInt(1, locationId);
				try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					Slot slot = new Slot();
					slot.setSlotid(resultSet.getInt("slotid"));
					slot.setLocationid(resultSet.getInt("locationid"));
					slot.setSlotno(resultSet.getString("slotno"));
					slot.setStatus(resultSet.getInt("status"));
					slotList.add(slot);
				}
			 }
		}
		
		return slotList;
		
	}
	
	public List<Slot> getAvailableAllSlotList() throws SQLException{
		
		 List<Slot> slotList = new ArrayList<>();
		 
		 String sql = "select slots.*,locations.location_name from slots join locations on locations.locationid = slots.locationid where slots.status = 0";
		 
		 try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
				try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					Slot slot = new Slot();
					slot.setSlotid(resultSet.getInt("slotid"));
					slot.setLocationid(resultSet.getInt("locationid"));
					slot.setSlotno(resultSet.getString("slotno"));
					slot.setStatus(resultSet.getInt("status"));
					slot.setLocation(resultSet.getString("location_name"));
					slotList.add(slot);
				}
			 }
		}
		
		return slotList;
		
	}
	
	public void storeSlots(Slot slot) throws Exception {
		
		/** Insert Slots details **/
		String sql = "INSERT INTO slots (locationid,slotno,status) VALUES(? ,? , ?)";
		Connection connection =  dbConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setInt(1, slot.getLocationid());
		statement.setString(2, slot.getSlotno());
		statement.setInt(3, 0);
		statement.executeUpdate();
		/** Update Slots counts **/
		String sql1 = "UPDATE locations SET slots = slots + 1 where locationid = ?";
		Connection connection1 =  dbConnection.getConnection();
		PreparedStatement statement1 = connection1.prepareStatement(sql1); 
		statement1.setInt(1, slot.getLocationid());
		statement1.executeUpdate();
		
		connection.close();      
}

}
