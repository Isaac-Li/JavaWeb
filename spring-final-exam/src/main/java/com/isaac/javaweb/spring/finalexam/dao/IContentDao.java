package com.isaac.javaweb.spring.finalexam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.isaac.javaweb.spring.finalexam.meta.Product;
import com.isaac.javaweb.spring.finalexam.meta.Trx;


public interface IContentDao {

	//@Select ("Select id as contentid, price, title, icon, abstract as brief, text from content")
//	@Select ("Select con.id as contentid, con.price, con.title, con.icon, con.abstract as brief, "
//			+ "con.text, trx.contentid,trx.personId, trx.price from content con left join trx on con.id=trx.contentid ")
	@Select ("Select con.id as contentid, con.price, con.title, con.icon, con.abstract as brief, "
			+ "con.text from content con")
	@Results(value={
		@Result(property="contentid",     column="contentid"),
		@Result(property="price",     column="price"),
		@Result(property="title",     column="title"),
		@Result(property="icon",     column="icon"),
		@Result(property="brief",     column="brief"),
		@Result(property="text",     column="text"),
		@Result(property="trxes", javaType=List.class, column="contentid", many=@Many(select="getTrxes")),
	}) 
	public List<Product> getAllContentInfoFromDao();
	
	@Insert ("Insert into content (price, title, abstract, icon,  text) "
			+ "values (#{price}, #{title}, #{brief}, #{icon,jdbcType=BLOB}, #{text,jdbcType=BLOB} ) " )
	@SelectKey (statement="SELECT LAST_INSERT_ID() as id",keyProperty="contentid", keyColumn = "id", before=false, resultType=int.class)
	public void addContentFromDao(Product product);
	
//	@Select ("Select id as contentid, price, title, icon, abstract as brief, text from content"
//			+ " where id=#{contentid} ")
	@Select ("Select con.id as contentid, con.price, con.title, con.icon, con.abstract as brief, "
			+ "con.text from content con where con.id=#{contentid} ")
	@Results(value={
		@Result(property="contentid",     column="contentid"),
		@Result(property="price",     column="price"),
		@Result(property="title",     column="title"),
		@Result(property="icon",     column="icon"),
		@Result(property="brief",     column="brief"),
		@Result(property="text",     column="text"),
		@Result(property="trxes", javaType=List.class, column="contentid", many=@Many(select="getTrxes")),
	}) 
	public Product getContentInfoFromDao(Product product);
	
	@Select ("Select trx.contentid as trxcontentId, trx.personId, trx.price, trx.time from trx where contentid=#{contentid} ")
	@Results(value={
		@Result(property="trxcontentId",     column="trxcontentId"),
		@Result(property="personId",     column="personId"),
		@Result(property="price",     column="price"),
		@Result(property="time",     column="time")
	})
	List<Trx> getTrxes(Product product);
	
	@Update ("Update content Set price=#{price}, title=#{title}, abstract=#{brief},"
			+ " icon=#{icon,jdbcType=BLOB},  text=#{text,jdbcType=BLOB} where id=#{contentid} " )			
	public int updateContentFromDao(Product product);
	
	@Delete ("delete from content where id=#{contentid}")
	public int deleteContentFromDao(Product product);
}
