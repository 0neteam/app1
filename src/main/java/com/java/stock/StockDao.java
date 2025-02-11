package com.java.stock;

import java.util.List;

public interface StockDao {
	
	public List<IncomeDTO> searchIncome(IncomeDTO incomeDTO);
    public List<StockDTO> searchStock(StockDTO stockDTO);
	public int editIncome(IncomeDTO incomeDTO);
	public int editStock(StockDTO stockDTO);
	public int deleteIncome(int no);
	public int deleteStock(int no);
	
}
