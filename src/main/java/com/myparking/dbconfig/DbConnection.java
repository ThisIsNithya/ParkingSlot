package com.myparking.dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	String url = "jdbc:mysql://localhost:3306/parking_slot_new";
	String username = "root";
	String password =  "Password@ofs";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url,username,password);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}	
}
