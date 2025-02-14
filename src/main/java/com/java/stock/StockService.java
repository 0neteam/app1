package com.java.stock;

import org.springframework.ui.Model;

public interface StockService {
	
	public String searchIncome(Model model, IncomeDTO incomeDTO);
	public StockResDTO incomeEdit(StockReqDTO stockReqDTO);
	
	public String searchStock(Model model, StockDTO stockDTO);
	public StockResDTO stockEdit(StockReqDTO stockReqDTO);

	public StockResDTO biz(Integer bizNo);

}
