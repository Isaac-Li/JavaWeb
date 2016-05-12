package com.isaac.javaweb.SpringAOPTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ≤‚ ‘¿‡
 *
 */
public class App 
{
    public static void main( String[] args )
    {
   	
    	ApplicationContext context=new ClassPathXmlApplicationContext("bean.xml");
    	DBManager dbm=context.getBean("dbmanager", DBManager.class);
    	dbm.DBConnect();
    	dbm.getProductInventory("bag");
    	dbm.DBDisconnect();
    	try{
    		dbm.DBTrowException();
    	}catch(Exception e)
    	{
    	}
    	  
    	((ConfigurableApplicationContext) context).close();
    }
}
