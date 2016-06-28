package com.isaac.javaweb.spring.finalexam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.isaac.javaweb.spring.finalexam.meta.Product;


public interface IContentDao {

	//@Select ("Select id as contentid, price, title, icon, abstract as brief, text from content")
	@Select ("Select con.id as contentid, con.price, con.title, con.icon, con.abstract as brief, "
			+ "con.text, trx.contentid,trx.personId, trx.price from content con left join trx on con.id=trx.contentid ")
	public List<Product> getAllContentInfoFromDao();
	
	@Insert ("Insert into content (price, title, abstract, icon,  text) "
			+ "values (#{price}, #{title}, #{brief}, #{icon,jdbcType=BLOB}, #{text,jdbcType=BLOB} ) " )
	@SelectKey (statement="SELECT LAST_INSERT_ID() as id",keyProperty="contentid", keyColumn = "id", before=false, resultType=int.class)
	public void addContentFromDao(Product product);
	
//	@Select ("Select id as contentid, price, title, icon, abstract as brief, text from content"
//			+ " where id=#{contentid} ")
	@Select ("Select con.id as contentid, con.price, con.title, con.icon, con.abstract as brief, "
			+ "con.text, trx.contentid,trx.personId, trx.price from content con left join trx on con.id=trx.contentid  where con.id=#{contentid} ")
	public Product getContentInfoFromDao(Product product);
	
	@Update ("Update content Set price=#{price}, title=#{title}, abstract=#{brief},"
			+ " icon=#{icon,jdbcType=BLOB},  text=#{text,jdbcType=BLOB} where id=#{contentid} " )			
	public int updateContentFromDao(Product product);
}
