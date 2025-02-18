package com.java.order;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResDTO {
    
    private boolean status;
    private Object result;
    private String msg;

}
