package com.java.order;

import java.time.LocalDate;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuoOrderDTO {

    private int orderNo;
    private int bizNo;
    private String dstn;
    private LocalDate deliDate;
    private LocalDate orderDate;
    
}
