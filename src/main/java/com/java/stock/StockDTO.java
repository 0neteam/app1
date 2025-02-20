package com.java.stock;

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
public class StockDTO {

	private int itemCode;
	private String name;
	private int qty;
	
    private int category;			// 카테고리 (검색용)
    private String search;			// 서치
    
    // private String bizNo;
    private int bizNo;
	
	
	public static StockDTO setStock(Map<String, String> paramMap) {
        StockDTO stockDTO = new StockDTO();
        
        String category = paramMap.get("category");
        String search = paramMap.get("search"); // search 값을 올바르게 가져옴
 
        if (category != null) {
            try {
            	stockDTO.setCategory(Integer.parseInt(category));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (search != null && !search.isEmpty()) {
            try {
                switch (stockDTO.getCategory()) {
                    case 1:
                    	stockDTO.setItemCode(Integer.parseInt(search));
                    	break;
                    case 2:
                    	stockDTO.setName(search);
                    	break;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        
        return stockDTO;
        
    }
	
}