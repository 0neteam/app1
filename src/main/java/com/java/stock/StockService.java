package com.java.stock;

import java.util.List;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

public interface StockService {
	
	public List<IncomeDTO> findIncome(Model model, HttpServletRequest req);
	public List<StockDTO> findStock(Model model, HttpServletRequest req);
	public int editincome(HttpServletRequest req);
	public int editstock(HttpServletRequest req);
	public int deleteincome(HttpServletRequest req);
	public int deletestock(HttpServletRequest req);

}
