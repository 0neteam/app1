package com.java.stock;

import java.util.List;

public interface StockDao {
	
	public List<IncomeDTO> searchIncome(IncomeDTO incomeDTO);
	public int createIncome(IncomeDTO incomeDTO);
	public int editIncome(IncomeDTO incomeDTO);
	public int deleteIncome(IncomeDTO incomeDTO);

	public List<StockDTO> searchStock(StockDTO stockDTO);
	public int editStock(StockDTO stockDTO);
	public int deleteStock(int no);

	public IncomeDTO findByItem(int incomeNo);
	public StockDTO findByBizAndItem(IncomeDTO incomeDTO);
	public int createStock(StockDTO stockDTO);
	public int updateStock(StockDTO stockDTO);
	
}
