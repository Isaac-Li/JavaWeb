package com.isaac.JDBCconnectMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class JDBCconnectMySQL 
{
	static final String DRIVER_NAME="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost/product?useCursorFetch=true";
	static final String DB_USER_NAME= "root";
	static final String DB_PASSWORD="Maxweb12]";
	
    public static void main( String[] args ) throws ClassNotFoundException
    {
    	//getData();
    	getDataByUsingCur();
    }
    

    public static void getData() throws ClassNotFoundException
    {

    	Connection conn=null;
    	Statement stat=null;
    	ResultSet res=null;
    	
		//装载驱动程序
		Class.forName(DRIVER_NAME);
    	try{ 
    		//建立数据库连接
    		conn=DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
    		stat=conn.createStatement();
    		
    		//执行SQL语句
    		res=stat.executeQuery("select * from product where Id=1");
    		
    		//获取执行结果
    		while(res.next()){
    			System.out.println("Id:"+res.getString("Id")+" Product Name:"+res.getString("ProductName")+" Inventory:"+res.getString("Inventory"));
    		}
    	}catch(SQLException e){
    		e.printStackTrace();
    	}finally{
				try {
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

    public static void getDataByUsingCur(){
    	Connection conn=null;    	
    	ResultSet res=null;
    	PreparedStatement ptmt=null;
    	
    	try {
    		conn=DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
    		String sql="select * from product where Id=1";    	

			ptmt=conn.prepareStatement(sql);
		   	ptmt.setFetchSize(1);
		   	res=ptmt.executeQuery();
		   	
    		while(res.next()){
    			System.out.println("Id:"+res.getString("Id")+" Product Name:"+res.getString("ProductName")+" Inventory:"+res.getString("Inventory"));
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
	    		if(conn!=null)
	    			conn.close();
	    		if(ptmt!=null)
	    			ptmt.close();
	    		if(res!=null)
	    			res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
    	
 
    	
    }
}
