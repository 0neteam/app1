package com.java.order;

import java.util.List;

import com.java.biz.BizApiKeyDTO;

public interface OrderDAO {

    // 발주 조회
    public List<OrderDTO> orderInquiry();
    // 발주 조회 검색
    public List<OrderDTO> searchOrders(String searchTerm);

    int insertOrder(OrderDTO order);  // 주문 정보 삽입
    int insertOrderItem(OrderItemDTO orderItem);  // 주문 항목 삽입

    
    public List<OrderBizDTO> findByBiz(); // 거래처(제조) 목록
    public BizApiKeyDTO findByBizApi(BizApiKeyDTO bizApiKeyDTO);
    public OrderDTO findByOrderNo(int orderNo);

}
