package com.java.stock;

import java.sql.Date;
import java.sql.Timestamp;

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
	
	private int no;
	private int orderItemNo;
	private String incomeId;
	private int qty;
	private Date reqDate;
	private Timestamp incomeDate;
	private String status;
	private String useYN;
	
	private int orderQty;

}