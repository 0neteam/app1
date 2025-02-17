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


    @Override
    public void insertOrder(OrderDTO order) {
        // OrderDTO 객체를 Mapper에 전달하여 DB에 주문 데이터를 삽입
        orderMapper.insertOrderAndReturnOrderNo(order);
    }

    @Override
    public void insertOrderItem(OrderItemDTO orderItem) {
        // OrderItemDTO 객체를 Mapper에 전달하여 DB에 주문 항목 데이터를 삽입
        orderMapper.insertOrderItem(orderItem);
    }

}
