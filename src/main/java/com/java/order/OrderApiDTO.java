package com.java.order;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderApiDTO {
    
    private boolean status;
    private Object data;
    private String msg;

}
