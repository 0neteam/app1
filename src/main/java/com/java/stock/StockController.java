package com.java.stock;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class StockController {
	
	@GetMapping("/stock")
	public String list() {
		return "stock/stock";
	}

}
