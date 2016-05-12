package com.isaac.javaweb.SpringAOPTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	static final String DRIVER_NAME="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost/product?useCursorFetch=true";
	static final String DB_USER_NAME= "root";
	static final String DB_PASSWORD="Maxweb12]";
	
	private Connection conn=null;
	private PreparedStatement pstat=null;
	private ResultSet rs=null;
	
	
	public void DBConnect(){
		try {
			Class.forName(DRIVER_NAME);
			conn=DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getProductInventory(String productName){
		int count=0;
		try {			
			pstat=conn.prepareStatement("select inventory from inventory where ProductName=?");
			pstat.setString(1, productName);
			rs=pstat.executeQuery();
		    while (rs.next()) {
			        System.out.println("the count of "+productName+": "+rs.getString("inventory"));
			        count= rs.getInt("inventory");
			      }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return count;
	}
	
	public void DBDisconnect(){		
			try {
				if(conn!=null)	conn.close();
				if(pstat!=null) pstat.cancel();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void DBTrowException() throws Exception{
		throw new Exception("throw exception!") ;
	}
	
}
