package com.java.stock;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class StockController {

	private final StockService stockService;
	
	@GetMapping("/stock")
	public String stocklist(Model model, @RequestParam(required = false) Map<String, String> paramMap) {
		return stockService.searchStock(model, StockDTO.setStock(paramMap));
	}
	
	@GetMapping("/income")
	public String incomelist(Model model, @RequestParam(required = false) Map<String, String> paramMap) {
		return stockService.searchIncome(model, IncomeDTO.setIncome(paramMap));
	}
	
	@ResponseBody
	@PostMapping("/income/biz")
	public StockResDTO biz(@RequestParam("bizNo") Integer bizNo) {
		return stockService.biz(bizNo);
	}

	@ResponseBody
	@PostMapping("/income/edit")
	public StockResDTO incomeEdit(@RequestBody StockReqDTO stockReqDTO) {
		return stockService.incomeEdit(stockReqDTO);
	}
	
	@ResponseBody
	@PostMapping("/stock/edit")
	public StockResDTO stockEdit(@RequestBody StockReqDTO stockReqDTO) {
		return stockService.stockEdit(stockReqDTO);
	}
	
}
