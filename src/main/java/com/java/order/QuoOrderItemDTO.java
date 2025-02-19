package com.java.order;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuoOrderItemDTO {
    
    private int itemCode;
    private int qty;
    
}
