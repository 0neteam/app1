package com.java.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@RequiredArgsConstructor
@Repository
public class OrderDaoImp implements OrderDAO{
    private final OrderMapper orderMapper;

    // 발주 조회
    @Override
    public List<OrderDTO> orderInquiry() {
        return orderMapper.orderInquiry();
    }
    // 발주 조회 검색
    @Override
    public List<OrderDTO> searchOrders(String searchTerm) {
        return orderMapper.searchOrders(searchTerm);
    }
}
