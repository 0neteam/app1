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
	
    // stg_incoming (입고)
    private int no;             // 입고번호 (PK)
    private String incomeId;    // 입고 코드
    private int incomeQty;      // 입고 수량
    private Date reqDate;       // 입고 요청 일자
    private Timestamp incomeDate; // 입고 일자
    private String status;      // 입고 상태
    private String useYN;       // 사용 여부

    // stg_order_item (발주 품목)
    private int orderItemNo;    // 발주 요청 품목 번호 (PK)
    private int orderNo;        // 발주 코드
    private int itemCode;       // 품목 코드
    private String name;        // 품목명
    private int orderQty;       // 발주 수량
    private Date orderDate;     // 발주 일자

    // 거래처 정보 (stg_client 참조)
    private String supplierName; // 거래처
    

}