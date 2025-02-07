package com.java.stock;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StockMapper {

	@Select("SELECT itemCode, name, qty FROM stg_stock WHERE no = #{no}")
	public List<IncomeDTO> findIncome(int no);
	
	@Select("SELECT itemCode, name, qty FROM stg_stock WHERE no = #{no}")
	public List<StockDTO> findStock(int no);
	
	@Update("UPDATE stg_incoming SET qty = #{qty} WHERE no = #{no}")
	public int editincome(StockDTO stockDTO);
	
	@Update("UPDATE stg_stock SET qty = #{qty} WHERE no = #{no}")
	public int editstock(StockDTO stockDTO);
	
	@Update("UPDATE stg_incoming SET use = 'N' WHERE no = #{no}")
	public int deleteincome(int no);
	
	@Update("UPDATE stg_stock SET use = 'N' WHERE no = #{no}")
	public int deletestock(int no);

}
