package com.java.stock;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StockServiceImp implements StockService {
	
	@Override
	public List<IncomeDTO> findIncome(Model model, HttpServletRequest req) {
		return null;
	}

	@Override
	public List<StockDTO> findStock(Model model, HttpServletRequest req) {
		return null;
	}

	@Override
	public int editincome(HttpServletRequest req) {
		return 0;
	}

	@Override
	public int editstock(HttpServletRequest req) {
		return 0;
	}

	@Override
	public int deleteincome(HttpServletRequest req) {
		return 0;
	}

	@Override
	public int deletestock(HttpServletRequest req) {
		return 0;
	}

}
