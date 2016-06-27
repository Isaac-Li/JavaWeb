package com.isaac.javaweb.spring.finalexam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.isaac.javaweb.spring.finalexam.meta.Product;


public interface IContentDao {

	@Select ("Select id as contentid, price, title, icon, abstract as brief, text from content")
	public List<Product> getAllContentInfoFromDao();
	
	@Insert ("Insert into content (price, title, abstract, icon,  text) "
			+ "values (#{price}, #{title}, #{brief}, #{icon,jdbcType=BLOB}, #{text,jdbcType=BLOB} ) " )
	@SelectKey (statement="SELECT LAST_INSERT_ID() as id",keyProperty="contentid", keyColumn = "id", before=false, resultType=int.class)
	public void addContentFromDao(Product product);
	
	@Select ("Select id as contentid, price, title, icon, abstract as brief, text from content"
			+ " where id=#{contentid} ")
	public Product getContentInfoFromDao(Product product);
	
	@Update ("Update content Set price=#{price}, title=#{title}, abstract=#{brief},"
			+ " icon=#{icon,jdbcType=BLOB},  text=#{text,jdbcType=BLOB} where id=#{contentid} " )			
	public int updateContentFromDao(Product product);
}
