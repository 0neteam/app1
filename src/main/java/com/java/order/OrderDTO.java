package com.java.order;

import lombok.*;

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
    private Date orderReqDate;
    private Date orderCancelDate;
    private String orderStatus;
    private Date perDate;
    private String dstn;
    private String transpStatus;
    private String departure;
    private String driverName;
    private String bizNo;

}
