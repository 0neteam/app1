package com.java.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.biz.BizApiKeyDTO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public String processOrderRequest(String selectedRowsJson, String bizNo, String dueDate) {
        try {
            // JSON 문자열을 OrderDTO와 OrderItemDTO 객체로 변환
            LocalDate formattedDueDate = LocalDate.parse(dueDate);
            ObjectMapper objectMapper = new ObjectMapper();
            List<OrderItemDTO> selectedOrderItems = objectMapper.readValue(selectedRowsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, OrderItemDTO.class));
            OrderDTO order = OrderDTO.builder().bizNo(Integer.parseInt(bizNo)).orderStatus("발주 대기").dstn("부산").daliDate(formattedDueDate).build();
            orderDAO.insertOrder(order);


            // 각 주문 항목에 대해 처리
            for (OrderItemDTO orderItem : selectedOrderItems) {
                // orderNo와 dueDate는 반복문 밖에서 설정
                orderItem.setOrderNo(order.getOrderNo());  // 생성된 orderNo를 설정
                orderItem.setDueDate(formattedDueDate.toString());  // 모든 항목에 동일한 dueDate 설정

                // 주문 항목 테이블에 데이터 삽입

                // 주문 항목 삽입 (주문 항목에 대해 insert 수행)
                orderDAO.insertOrderItem(orderItem);
            }

            // 처리 후 리턴할 페이지 (예: 주문 완료 페이지)
            return "redirect:/order/orderInquiry"; // 예시: 성공적인 주문 완료 페이지

        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // 에러 처리 페이지
        }
    }

    public OrderResDTO findByBiz() {
        List<OrderBizDTO> bizs = orderDAO.findByBiz();
        boolean status = (bizs == null) ? false : true;
        String msg = (bizs == null) ? "거래처 정보가 없습니다." : null;
        return OrderResDTO.builder().status(status).result(bizs).msg(msg).build();
    }

    public OrderApiDTO findByItems(int bizNo) {
        BizApiKeyDTO bizApiKeyDTO = orderDAO.findByBizApi(bizNo);
        if(bizApiKeyDTO != null) {
            try {

                ResponseEntity<OrderApiDTO> response = RestClient.create().post()
                    .uri("http://localhost:8081" + bizApiKeyDTO.getUrl())
                    .header("Authorization", bizApiKeyDTO.getKey())
                    .retrieve()
                    .toEntity(OrderApiDTO.class);

                return response.getBody();
            } catch (RestClientException e) {
                e.printStackTrace();
            }
        }
        return OrderApiDTO.builder().status(false).build();
    }

}
