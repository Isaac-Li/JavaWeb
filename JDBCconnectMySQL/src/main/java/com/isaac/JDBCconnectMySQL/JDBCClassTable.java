package com.isaac.JDBCconnectMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

public class JDBCClassTable {

	static final String DRIVER_NAME="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost/School?useCursorFetch=true&useSSL=false";
	static final String DB_USER_NAME= "root";
	static final String DB_PASSWORD="Maxweb12]";
	
	public static void main(String[] args) {
		//getUser("ZhangSan");
		//getUserByCursor();
		//getUserByPool("ZhangSan");
		changeUserCourse("ZhangSan","math","LiSi","math");
	}
	
	public static void getUser(String UserName){
		Connection  conn= null;
		Statement   stat=null;
		ResultSet 	res=null;
		
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			conn=DriverManager.getConnection(DB_URL,DB_USER_NAME,DB_PASSWORD);
			stat=conn.createStatement();
			
			res=stat.executeQuery("select * from classtable where UserName='"+UserName+"'");
			while(res.next()){
				System.out.println("Id:"+res.getString("id")+" User Name:"+res.getString("UserName")+" Course:"+res.getString("CourseName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
	    		if(conn!=null)
	    			conn.close();
	    		if(stat!=null)
	    			stat.close();
	    		if(res!=null)
	    			res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}

	public static void getUserByCursor(){
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet res=null;
		
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conn=DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
			pstat=conn.prepareStatement("Select * from classtable");
			pstat.setFetchSize(1);
			
			res=pstat.executeQuery();
			while(res.next()){
				System.out.println("Id:"+res.getString("id")+" User Name:"+res.getString("UserName")+" Course:"+res.getString("CourseName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				try {
					if(conn!=null) conn.close();
					if(pstat!=null) pstat.close();
					if(res!=null) res.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
	}
	
	public static void getUserByPool(String UserName){
		BasicDataSource bdb= new BasicDataSource();
		bdb.setUrl(DB_URL);
		bdb.setDriverClassName(DRIVER_NAME);
		bdb.setUsername(DB_USER_NAME);
		bdb.setPassword(DB_PASSWORD);
		
    	Connection cnn=null;
    	Statement stat=null;
    	ResultSet res=null;
    	
    	String sql="select * from classtable where UserName='"+UserName+"'";
    	
    	try {
			cnn=bdb.getConnection();
			stat=cnn.createStatement();
			res=stat.executeQuery(sql);
			
			while(res.next()){
				System.out.println("Id:"+res.getString("id")+" User Name:"+res.getString("UserName")+" Course:"+res.getString("CourseName"));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{			
				try {
					if(cnn!=null)  cnn.close();
					if(stat!=null) stat.close();
					if(res!=null)  res.close();
					if(bdb!=null)  bdb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    	
	}

	public static void changeUserCourse(String removeUserName, String removeCourseName, String newUserName, String newCourseName){
		Connection conn=null;
		Statement stat=null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn=DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
			conn.setAutoCommit(false);
			stat=conn.createStatement();

			stat.addBatch("delete from classtable where UserName='"+removeUserName+"' and CourseName='"+removeCourseName+"'");
			stat.addBatch("insert into classtable (UserName, CourseName) values ('"+newUserName+"','"+newCourseName+"')");
			stat.executeBatch();
			conn.commit();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
				try {
					if(conn!=null)	conn.close();
					if(stat!=null)  stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
		}
		
	}
}
