package com.java.stock;

import java.util.List;

public interface StockDao {
	
	public List<IncomeDTO> findIncome(int no);
	public List<StockDTO> findStock(int no);
	public int editincome(StockDTO stockDTO);
	public int editstock(StockDTO stockDTO);
	public int deleteincome(int no);
	public int deletestock(int no);
	
}
