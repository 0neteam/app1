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
public class OrderDTO {

    private int orderId;
    private int orderNo;
    private String orderReqDate;
    private String orderCancelDate;
    private String orderStatus;
    private String perDate;
    private String dstn;
    private String transpStatus;
    private String departure;
    private String driverName;
    private int bizNo;
    private LocalDate daliDate;
    private String adr;

}
