package com.java.stock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeDTO {
	
    private int incomeNo;           // 입고 코드
    private int orderNo;            // 발주 코드
    private int itemCode;           // 품목 코드
    private String stockName;       // 품목명
    private int orderQty;           // 발주 수량
    private int incomeQty;          // 입고 수량
    private LocalDate orderDate;         // 발주 일자   
    private LocalDateTime incomeDate;   // 입고 일자
    private int bizNo;              // 거래처 코드
    private String status;          // 입고 상태
    
    private int category1;			// 카테고리1 (검색용)
    private int category2;		// 카테고리(상태) (검색용)
    private String search;			// 서치
    
    public static IncomeDTO setIncome(Map<String, String> paramMap) {
        IncomeDTO incomeDTO = new IncomeDTO();

        String category1 = paramMap.get("category1");
        String category2 = paramMap.get("category2");
        String search = paramMap.get("search"); // search 값을 올바르게 가져옴

        if (category1 != null) {
            try {
                incomeDTO.setCategory1(Integer.parseInt(category1));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (category2 != null) {
            try {
                incomeDTO.setCategory2(Integer.parseInt(category2));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (search != null && !search.isEmpty()) {
            try {
                switch (incomeDTO.getCategory1()) {
                    case 1:
                        incomeDTO.setOrderNo(Integer.parseInt(search));
                        break;
                    case 2:
                        incomeDTO.setItemCode(Integer.parseInt(search));
                        break;
                    case 3:
                        incomeDTO.setStockName(search);
                        break;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return incomeDTO;
    }
}