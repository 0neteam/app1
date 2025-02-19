package com.java.order;

import java.util.List;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderReqDTO {
    
    private OrderDTO order;
    private List<OrderItemDTO> orderItems;

}
