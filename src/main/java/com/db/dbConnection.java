package com.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {
	
	public static Connection getConnection()
	{
		Connection con=null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/loginandregister","root","root");
			
			
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return con;
	}

}