package com.java.stock;

import java.util.List;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

public interface StockService {
	
	public String searchIncome(Model model, HttpServletRequest req);
	public String searchStock(Model model, HttpServletRequest req);
	public String editincome(HttpServletRequest req);
	public String editstock(HttpServletRequest req);
	public String deleteincome(HttpServletRequest req);
	public String deletestock(HttpServletRequest req);

}
