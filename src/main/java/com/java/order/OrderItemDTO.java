package com.java.order;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {

    private int orderItemNo;
    private String orderNo;
    private String itemCode;
    private int qty;

}
