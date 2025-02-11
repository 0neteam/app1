package com.java.stock;

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

	private int no;
	private int itemCode;
	private int bizNo;
	private String name;
	private int qty;
	private int price;
	private String category;
	
}