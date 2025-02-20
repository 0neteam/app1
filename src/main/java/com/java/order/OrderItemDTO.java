package com.java.order;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {

    private int orderItemNo;
    private int orderNo;
    private int itemCode;
    private String itemName;
    private int qty;

}
