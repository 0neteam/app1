package com.java.order;

import java.util.List;

public interface OrderDAO {

    // 발주 조회
    public List<OrderDTO> orderInquiry();
    // 발주 조회 검색
    public List<OrderDTO> searchOrders(String searchTerm);

    void insertOrder(OrderDTO order);  // 주문 정보 삽입
    void insertOrderItem(OrderItemDTO orderItem);  // 주문 항목 삽입
}
