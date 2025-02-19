package com.java.order;

import java.util.List;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderReqQuoDTO {

    private int orderNo;
    private QuoOrderDTO quoOrderDTO;
    private List<QuoOrderItemDTO> quoOrderItem;
    
}
