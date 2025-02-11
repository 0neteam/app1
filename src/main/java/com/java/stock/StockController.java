package com.java.stock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class StockController {
	
	@GetMapping("/stock")
	public String stocklist(Model model) {
		
		
		
		List<StockDTO> stocks = new ArrayList<>();
		stocks.add(StockDTO.builder().no(1).itemCode(1).name("효신").qty(10).build());
		stocks.add(StockDTO.builder().no(2).itemCode(2).name("임지").qty(30).build());
		stocks.add(StockDTO.builder().no(3).itemCode(3).name("경일").qty(50).build());
		model.addAttribute("result", stocks);
		
		
		return "stock/stock";
	}
	
	@GetMapping("/income")
	public String incomelist(Model model) {
		
		
		List<IncomeDTO> incomes = new ArrayList<>();
		incomes.add(IncomeDTO.builder().no(1).orderNo(1).itemCode(1)
				.name("효신").orderQty(10).incomeQty(10)
				.status("정상").build());
		incomes.add(IncomeDTO.builder().no(2).orderNo(2).itemCode(2)
				.name("임지").orderQty(30).incomeQty(20)
				.status("거래처비정상").build());
		incomes.add(IncomeDTO.builder().no(3).orderNo(3).itemCode(3)
				.name("경일").orderQty(50).incomeQty(30)
				.status("운송비정상").build());
		model.addAttribute("result", incomes);
		
		
		return "stock/income";
	}

}
