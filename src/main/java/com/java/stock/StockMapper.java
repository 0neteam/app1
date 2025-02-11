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
            "oi.no AS no, " +
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
            "WHERE 1=1 AND oi.useYN = 'Y'" +
	        "<if test='category == 1'>AND incomeCode = #{incomeCode} </if> " +
	        "<if test='category == 2'>AND itemCode = #{itemCode} </if>" +
	        "<if test='category == 3'>AND name LIKE CONCAT('%', #{name}, '%')</if>" +
            "ORDER BY incomeId ASC, orderItemNo ASC" +
            "</script>")
	public List<IncomeDTO> searchIncome(IncomeDTO incomeDTO);
	
	@Select("<script>" +
	        "SELECT no, itemCode, name, qty FROM stg_stock " +
	        "WHERE 1=1 AND oi.useYN = 'Y'" +
	        "<if test='category == 1'>AND itemCode = #{itemCode} </if> " +
	        "<if test='category == 2'>AND name LIKE CONCAT('%', #{name}, '%') </if>" +
	        "ORDER BY itemCode ASC, name ASC" +
	        "</script>")
	public List<StockDTO> searchStock(StockDTO stockDTO);
	
	@Update("UPDATE stg_incoming SET incomeQty = #{orderQty}, status = #{status} WHERE no = #{no}")
	public int editIncome(IncomeDTO incomeDTO);
	
	@Update("UPDATE stg_stock SET name = #{name}, qty = #{qty} WHERE no = #{no}")
	public int editStock(StockDTO stockDTO);
	
	@Update("UPDATE stg_incoming SET useYN = 'N' WHERE no = #{no}")
	public int deleteIncome(int no);
	
	@Update("UPDATE stg_stock SET useYN = 'N' WHERE no = #{no}")
	public int deleteStock(int no);

}
