package com.java.stock;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StockMapper {
	
	@Select("<script>" +
            "SELECT " +
            "oi.orderitemNo AS orderItemNo, " +
            "oi.no AS incomeId, " +
            "st.name AS name, " +
            "oi.qty AS orderQty, " +
            "inc.qty AS incomeQty, " +
            "o.orderReqDate AS orderDate, " +
            "inc.incomeDate AS incomeDate, " +
            "inc.status AS status " +
            "FROM stg_order_item oi " +
            "JOIN stg_incoming inc ON oi.orderitemNo = inc.incomeId " +
            "JOIN stg_order o ON oi.orderNo = o.orderNo " +
            "JOIN stg_stock st ON oi.itemCode = st.itemCode " +
            "WHERE 1=1 " +
            "<if test='pcode != null'>AND oi.orderitemNo = #{pcode} </if> " +
            "<if test='icode != null'>AND inc.orderNo = #{icode} </if> " +
            "<if test='pname != null'>AND st.name LIKE CONCAT('%', #{pname}, '%') </if>" +
            "<if test='pcode == null and icode == null and pname == null'>" +
            "ORDER BY incomeDate DESC</if> " +
            "</script>")
	public List<IncomeDTO> searchIncome(Integer pcode, String icode, String pname);
	
	@Select("<script>" +
	        "SELECT itemCode, name, qty FROM stg_stock " +
	        "WHERE 1=1 " +
	        "<if test='itemCode != null and category == 1'>AND itemCode = #{itemCode} </if> " +
	        "<if test='itemCode != null and category == 2'>AND name LIKE CONCAT('%', #{name}, '%') </if>" +
	        "<if test='itemCode == null or category == null'>ORDER BY itemCode ASC</if>" +
	        "</script>")
	public List<StockDTO> searchStock(StockDTO stockDTO);
	
	@Update("UPDATE stg_stock SET qty = #{qty} WHERE no = #{no}")
	public int editIncome(IncomeDTO incomeDTO);
	
	@Update("UPDATE stg_stock SET qty = #{qty} WHERE no = #{no}")
	public int editStock(StockDTO stockDTO);
	
	@Update("UPDATE stg_incoming SET use = 'N' WHERE no = #{no}")
	public int deleteIncome(int no);
	
	@Update("UPDATE stg_stock SET use = 'N' WHERE no = #{no}")
	public int deleteStock(int no);

}
