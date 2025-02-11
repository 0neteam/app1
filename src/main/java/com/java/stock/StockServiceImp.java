package com.java.stock;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StockServiceImp implements StockService {
	
	private final StockDao stockDao;
	
	@Override
	public String searchIncome(Model model, HttpServletRequest req) {
		Integer pcode = Integer.parseInt(req.getParameter("pcode"));
		String icode = req.getParameter("icode");
		String pname = req.getParameter("pname");
		List<IncomeDTO> incList = stockDao.searchIncome(pcode, icode, pname);
		System.out.println("-------------------------------------");
		model.addAttribute("result", incList);
		return "stock/income";
	}

	@Override
	public String searchStock(Model model, HttpServletRequest req) {
		
		String category = req.getParameter("category");
		String search = req.getParameter("search");
		System.out.println(category);
		System.out.println(search);
		
		if(category.equals("1")) {
			try {
				int itemCode = Integer.parseInt(search);
				System.out.println(itemCode);
				StockDTO stockDTO = StockDTO.builder().category(category).itemCode(itemCode).build();
				System.out.println(stockDTO);
				List<StockDTO> stockDTOs = stockDao.searchStock(stockDTO);
				model.addAttribute("result", stockDTOs);		
				System.out.println(stockDTOs);
				return "stock/stock";
			} catch (NumberFormatException e) {
				return "stock/stock";
			}
		} else if (category.equals("2")) {
			String name = search;
			StockDTO stockDTO = StockDTO.builder().name(name).build();
			List<StockDTO> stockDTOs = stockDao.searchStock(stockDTO);
			model.addAttribute("result", stockDTOs);
			return "stock/stock";
		} else {
			return "stock/stock";
		}}

	@Override
	public String editincome(HttpServletRequest req) {
		return "0";
	}

	@Override
	public String editstock(HttpServletRequest req) {
		return "0";
	}

	@Override
	public String deleteincome(HttpServletRequest req) {
		return "0";
	}

	@Override
	public String deletestock(HttpServletRequest req) {
		return "0";
	}
	

}
