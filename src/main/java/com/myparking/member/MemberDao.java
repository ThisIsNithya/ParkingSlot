package com.myparking.member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.myparking.dbconfig.DbConnection;

public class MemberDao {
	
	DbConnection dbConnection =new DbConnection();

	
	public void registerMemberDetails(Member member) throws Exception {
		
		Date currentDate = new Date(System.currentTimeMillis());
		String sql = "INSERT INTO users (email,fullname,mobno,password,address,role,created_date,modified_date) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	    Connection connection =  dbConnection.getConnection();
	    PreparedStatement statement = connection.prepareStatement(sql); 
	    
	    statement.setString(1, member.getEmail());
	    statement.setString(2, member.getFullname());
	    statement.setString(3, member.getMobno());
	    statement.setString(4, member.getPassword());
	    statement.setString(5, member.getAddress());
	    statement.setString(6, "User");
		statement.setDate(7, currentDate);
		statement.setDate(8, currentDate);
		
		statement.executeUpdate();
		connection.close();
		
		System.out.println("Done");        
	}
	
	public Member validateCredential( String emailId , String password ) throws Exception {
		
		Member loggedMember = new Member();
    	int rowCount = 0;
		String sql = "SELECT * FROM users where email = ?  and password = ?";
	    try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, emailId);
	        stmt.setString(2, password);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	            	loggedMember.setEmail(rs.getString("email"));
	            	loggedMember.setRole(rs.getString("role"));
	            	loggedMember.setFullname(rs.getString("fullname"));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return loggedMember;
		
	}

}
