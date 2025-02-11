package com.java.order;

import java.util.List;

public interface OrderDAO {

    // 발주 조회
    public List<OrderDTO> orderInquiry();
    // 발주 조회 검색
    public List<OrderDTO> searchOrders(String searchTerm);
}
