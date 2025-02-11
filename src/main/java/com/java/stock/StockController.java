package com.java.stock;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class StockController {
	
	@GetMapping("/stock")
	public String stocklist() {
		return "stock/stock";
	}
	
	@GetMapping("/income")
	public String incomelist() {
		return "stock/income";
	}

}
