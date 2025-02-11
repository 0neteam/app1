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
public class StockSeachDTO {
	
    private String category;
    private String search;

//    public static StockSearchDTO setDTO(Map<String, String> paramMap){
//        if(paramMap.isEmpty()) {
//            return StockSearchDTO.builder().build();
//        } else {
//            return StockSearchDTO.builder().category(paramMap.get("category")).search(paramMap.get("search")).build();
//        }
//    }

}
