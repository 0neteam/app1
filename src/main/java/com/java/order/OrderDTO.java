package com.java.order;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    private int orderNo;
    private int bizNo;
    private String orderStatus;
    private String dstn;
    private LocalDate orderReqDate;
    private LocalDate orderCancelDate;
    private LocalDate perDate;
    private LocalDate deliDate;

}
