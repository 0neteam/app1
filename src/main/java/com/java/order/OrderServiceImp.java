package com.java.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
@RequiredArgsConstructor
@Service
public class OrderServiceImp implements OrderService {

    private final OrderDAO orderDAO;

    // 발주 조회
    @Override
    public String orderInquiry(Model model) {
        List<OrderDTO> ex1s = orderDAO.orderInquiry();
        model.addAttribute("result", ex1s);
        return "order/orderInquiry";
    }
    // 발주 조회 검색
    @Override
    public String searchOrders(Model model ,String searchTerm) {
        List<OrderDTO> ex2s = orderDAO.searchOrders(searchTerm);
        model.addAttribute("result", ex2s);
        return "order/orderInquiry";

    }

    @Override
    @Transactional
    public String processOrderRequest(String selectedRowsJson) {
        try {
            // JSON 문자열을 OrderDTO와 OrderItemDTO 객체로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            List<OrderDTO> selectedOrders = objectMapper.readValue(selectedRowsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, OrderDTO.class));
            List<OrderItemDTO> selectedOrderItems = objectMapper.readValue(selectedRowsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, OrderItemDTO.class));

            // 각 주문에 대해 처리
            for (OrderDTO order : selectedOrders) {
                // 주문 테이블에 데이터 삽입
                orderDAO.insertOrder(order);
            }

            // 각 주문 항목에 대해 처리
            for (OrderItemDTO orderItem : selectedOrderItems) {
                // 주문 항목 테이블에 데이터 삽입
                orderDAO.insertOrderItem(orderItem);
            }

            // 처리 후 리턴할 페이지 (예: 주문 완료 페이지)
            return "order/orderInquiry";  // 예시: 성공적인 주문 완료 페이지

        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // 에러 처리 페이지
        }
    }


}
