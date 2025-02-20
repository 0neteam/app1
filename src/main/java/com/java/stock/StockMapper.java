package com.java.stock;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StockMapper {
	
	@Select("<script>" +
            "SELECT IFNULL(inc.no, 0) as incomeNo, " + 
            "		oi.orderNo, " + 
			"       oi.itemCode, " + 
			"		oi.itemName AS stockName," + 
			"		oi.qty AS orderQty, " + 
			"		inc.qty AS incomeQty, " +
			"		o.orderReqDate AS orderDate, " + 
			"		IFNULL(inc.incomeDate, '0000-00-00 00:00:00') AS incomeDate, " + 
			"		o.bizNo," +
			"		inc.status AS status " + 
			"FROM stg_incoming inc " + 
			"LEFT OUTER JOIN stg_order_item oi " + 
			"  ON (oi.orderItemNo = inc.orderItemNo AND inc.useYN = 'Y') " + 
			"INNER JOIN stg_order o " + 
			"  ON (oi.orderNo = o.orderNo) " + 
			"LEFT OUTER JOIN stg_stock st " + 
			"  ON (oi.itemCode = st.itemCode AND st.useYN = 'Y') " + 
			"WHERE oi.useYN = 'Y' " + 
			
	        "<if test='category1 == 1 and orderNo != null and orderNo != \"\"'> " +
	        "   AND oi.orderNo = #{orderNo} " +
	        "</if> " +
	        "<if test='category1 == 2 and itemCode != null and itemCode != \"\"'> " +
	        "   AND st.itemCode = #{itemCode} " +
	        "</if> " +
	        "<if test='category1 == 3 and stockName != null and stockName != \"\"'> " +
	        "   AND st.name LIKE concat('%', #{stockName}, '%') " +
	        "</if> " +

	        "<if test='category2 == 1'>AND inc.status is null </if>" +
	        "<if test='category2 == 2'>AND inc.status = '운송비정상' </if>" +
	        "<if test='category2 == 3'>AND inc.status = '거래처비정상' </if>" +
	        "<if test='category2 == 4'>AND inc.status = '정상' </if>" +

			"ORDER BY 1 ASC" +
            "</script>")
	public List<IncomeDTO> searchIncome(IncomeDTO incomeDTO);
	

	@SelectKey(statement = "SELECT LAST_INSERT_ID() AS no", keyProperty = "incomeNo", before = false, resultType = int.class)
	@Insert("INSERT INTO stg_incoming "
			+" (`orderItemNo`, `qty`, `status`) "
			+"VALUE "
			+" ( fn_order_item_no(#{orderNo}, #{itemCode}) , #{incomeQty}, #{status})")
	public int createIncome(IncomeDTO incomeDTO);

	@Update("UPDATE stg_incoming SET qty = #{incomeQty}, status = #{status} WHERE no = ${incomeNo}")
	public int editIncome(IncomeDTO incomeDTO);
	
	@Update("UPDATE stg_incoming SET useYN = 'N' WHERE no = ${incomeNo}")
	public int deleteIncome(IncomeDTO incomeDTO);
	
	
	@Select("<script>" +
	        "SELECT itemCode, name, qty FROM stg_stock " +
	        "WHERE useYN = 'Y' " +
	        "<if test='category == 1 and itemCode != null and itemCode != \"\"'>AND itemCode = #{itemCode} </if> " +
	        "<if test='category == 2 and name != null and name != \"\"'>AND name LIKE CONCAT('%', #{name}, '%') </if>" +
	        "</script>")
	public List<StockDTO> searchStock(StockDTO stockDTO);
	
	@Update("UPDATE stg_stock SET name = #{name}, qty = #{qty} WHERE itemCode = #{itemCode}")
	public int editStock(StockDTO stockDTO);
		
	@Update("UPDATE stg_stock SET useYN = 'N' WHERE itemCode = #{itemCode}")
	public int deleteStock(int no);
	
	
	@Select("SELECT qty FROM stg_incoming WHERE no = #{incomeNo}")
	public Integer searchQty(int incomeNo);

	@Update("UPDATE stg_stock SET qty = qty + #{no} WHERE itemCode = #{itemCode}")
	public int updateQtyP(@Param("itemCode") int itemCode, @Param("no") int no);

	@Update("UPDATE stg_stock SET qty = qty - #{no} WHERE itemCode = #{itemCode}")
	public int updateQtyM(@Param("itemCode") int itemCode, @Param("no") int no);


	// 1단계 : Stock 수정 전 입고 데이터 가져오기
	@Select("SELECT `no` as incomeNo, qty FROM stg_incoming WHERE useYn = 'Y' AND `no` = #{incomeNo}")
	public IncomeDTO findByItem(int incomeNo);
	
	// 2단계 : Stock 테이블에 해당 품목 여부 확인
	@Select("SELECT * FROM stg_stock WHERE bizNo = #{bizNo} AND name = #{stockName}")
	public StockDTO findByBizAndItem(IncomeDTO incomeDTO);

	// 3-1단계 : 해당 폼목이 없으면 추가
	@Insert("INSERT INTO stg_stock (bizNo, name, qty) VALUE (#{bizNo}, #{name}, #{qty})")
	public int createStock(StockDTO stockDTO);

	// 3-2단계 : 해당 폼목이 있으면 수정
	@Update("UPDATE stg_stock SET qty = #{qty} WHERE itemCode = #{itemCode}")
	public int updateStock(StockDTO stockDTO);
	
}
