package com.java.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.java.biz.BizApiKeyDTO;

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
    public int insertOrder(OrderDTO order) {
        // OrderDTO 객체를 Mapper에 전달하여 DB에 주문 데이터를 삽입
        return orderMapper.insertOrderAndReturnOrderNo(order);
    }

    @Override
    public int insertOrderItem(OrderItemDTO orderItem) {
        // OrderItemDTO 객체를 Mapper에 전달하여 DB에 주문 항목 데이터를 삽입
        return orderMapper.insertOrderItem(orderItem);
    }


    public List<OrderBizDTO> findByBiz() {
        return orderMapper.findByBiz();
    }
    public BizApiKeyDTO findByBizApi(BizApiKeyDTO bizApiKeyDTO) {
        return orderMapper.findByBizApi(bizApiKeyDTO);
    }

    public OrderDTO findByOrderNo(int orderNo) {
        return orderMapper.findByOrderNo(orderNo);
    }

    public int orderEdit(OrderDTO order) {
        return orderMapper.orderEdit(order);
    }

    public int orderSyncIncoming(int orderNo) {
        return orderMapper.orderSyncIncoming(orderNo);
    }

}
