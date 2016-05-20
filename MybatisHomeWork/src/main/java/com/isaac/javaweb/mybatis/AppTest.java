package com.isaac.javaweb.mybatis;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class AppTest {

	public static void main(String[] args) {
		String resource="conf.xml";
		InputStream is=(InputStream) AppTest.class.getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(is);
		SqlSession session=sqlSessionFactory.openSession();
		
		try{			
			Op op=session.getMapper(Op.class);
			
			User user=op.getUser(3);
			System.out.println("Name: "+user.getUserName()+" Tel:"+ user.getTel());
			
			for(Product product:user.getProducts()){
				System.out.println("Product Name: "+ product.productName+" Catalog: "+product.catalog);
			}
			
			System.out.println("======================================================================");
			Product product=op.getProduct(2);
			System.out.println("Product Name: "+product.getProductName()+" Catalog: "+product.getCatalog());
			
		}finally{
			session.close();
		}

	}
	

}
