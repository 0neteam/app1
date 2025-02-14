package com.java.stock;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockReqDTO {
    
    private String state; // edit, delete
    private List<IncomeDTO> inComes;
    private List<StockDTO> stocks;
    
}
