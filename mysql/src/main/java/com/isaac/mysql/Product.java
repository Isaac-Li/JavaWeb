package com.isaac.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Product {
	static final String DRIVER_NAME="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost/Product?useCursorFetch=true";
	static final String DB_USER_NAME= "root";
	static final String DB_PASSWORD="Maxweb12]";

	public static void main(String[] args) {

		buyProduct("XiaoMing", "bag",1);
	}

	public static void buyProduct(String buyer, String product, int count){
		Connection conn=null;
		Statement stat=null;
				
		try {
			Class.forName(DRIVER_NAME);
			conn=DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
			
			conn.setAutoCommit(false);
			stat=conn.createStatement();
			
			stat.addBatch("update inventory set inventory=(inventory-"+Integer.toString(count)
					+") where ProductName='"+product+"'");	
			stat.addBatch("Insert into orderlist (buyer, ProductName, Count) "
					+ "values('"+buyer+"','"+product+"',"+Integer.toString(count)+")");
			
			stat.executeBatch();
			conn.commit();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				try {
					if(conn!=null) 	conn.close();
					if(stat!=null)  stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
