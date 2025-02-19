package com.java.order;

import java.util.Map;

import org.springframework.ui.Model;

public interface OrderService {
    // 발주 조회
    public String orderInquiry(Model model);
    // 발주 조회 검색
    public String searchOrders(Model model ,String searchTerm);


    String processOrderRequest(String selectedRowsJson ,  String bizNo, String dueDate);

    public OrderResDTO findByBiz();
    public OrderApiDTO findByItems(int bizNo);
    public OrderResDTO orderCreate(OrderReqDTO orderReqDTO);

}
