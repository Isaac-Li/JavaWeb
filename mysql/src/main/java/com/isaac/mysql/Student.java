package com.isaac.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Student {
	static final String DRIVER_NAME="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost/school?useCursorFetch=true";
	static final String DB_USER_NAME= "root";
	static final String DB_PASSWORD="Maxweb12]";
	
	public static void main(String[] args) {
		try {
			//getStudent("XiaoMing' or 1=1 or 1='");
			getStudent("XiaoMing");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void getStudent(String name) throws ClassNotFoundException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	      Class.forName(DRIVER_NAME);
	      conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
	      
	      pstmt = conn.prepareStatement("select name,number from student where name =?");
	      pstmt.setString(1, name);
	      
	      rs = pstmt.executeQuery();
	      while (rs.next()) {
	        System.out.println(rs.getString("name") + ":" + rs.getInt("number"));
	      }
	    } catch (SQLException e) {
	      // ignore
	    	System.out.println(e.getMessage());
	    } finally {
	      if (rs != null) {
	        try {
	          rs.close();
	        } catch (Exception e) {
	          // ignore
	        }
	      }
	      if (pstmt != null) {
	        try {
	          pstmt.close();
	        } catch (Exception e) {
	          // ignore
	        }
	      }
	      if (conn != null) {
	        try {
	          conn.close();
	        } catch (SQLException e) {
	          // ignore
	        }
	      }
	    }
	  }	


	public static void getStudentwithSQLissue(String name) throws ClassNotFoundException {
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	      Class.forName(DRIVER_NAME);
	      conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
	      stmt = conn.createStatement();
	      String sql="select name,number from student where name ='" + name +"'";
	      rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	        System.out.println(rs.getString("name") + ":" + rs.getInt("number"));
	      }
	    } catch (SQLException e) {
	      // ignore
	    	System.out.println(e.getMessage());
	    } finally {
	      if (rs != null) {
	        try {
	          rs.close();
	        } catch (Exception e) {
	          // ignore
	        }
	      }
	      if (stmt != null) {
	        try {
	          stmt.close();
	        } catch (Exception e) {
	          // ignore
	        }
	      }
	      if (conn != null) {
	        try {
	          conn.close();
	        } catch (SQLException e) {
	          // ignore
	        }
	      }
	    }
	  }	

	

}
