package com.java.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.biz.BizApiKeyDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;
import java.util.ArrayList;
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
        if(ex1s.size() > 0) {
            int bizNo = ex1s.get(0).getBizNo();
            BizApiKeyDTO bizApiKeyDTO = BizApiKeyDTO.builder().bizNo(bizNo).type("order").build();
            bizApiKeyDTO = orderDAO.findByBizApi(bizApiKeyDTO);
            model.addAttribute("key", bizApiKeyDTO.getKey());
        }
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
            OrderDTO order = OrderDTO.builder().bizNo(Integer.parseInt(bizNo)).orderStatus("발주 대기").dstn("부산").deliDate(formattedDueDate).build();
            orderDAO.insertOrder(order);


            // 각 주문 항목에 대해 처리
            for (OrderItemDTO orderItem : selectedOrderItems) {
                // orderNo와 dueDate는 반복문 밖에서 설정
                orderItem.setOrderNo(order.getOrderNo());  // 생성된 orderNo를 설정

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
        BizApiKeyDTO bizApiKeyDTO = BizApiKeyDTO.builder().bizNo(bizNo).type("list").build();
        bizApiKeyDTO = orderDAO.findByBizApi(bizApiKeyDTO);
        if(bizApiKeyDTO != null) {
            try {

                ResponseEntity<OrderApiDTO> response = RestClient.create().post()
                    .uri(bizApiKeyDTO.getUrl())
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

    public OrderResDTO orderCreate(OrderReqDTO orderReqDTO) {
        boolean status = false;
        String url = "";
        try {
            OrderDTO order = orderReqDTO.getOrder();
            String orderStatus = order.getOrderStatus();
            order.setOrderStatus(OrderStatusEnum.getDescriptionByCode(Integer.parseInt(orderStatus)));
            if(orderDAO.insertOrder(order) == 1) {
                for(OrderItemDTO orderItemDTO : orderReqDTO.getOrderItems()) {
                    orderItemDTO.setOrderNo(order.getOrderNo());
                    if(orderDAO.insertOrderItem(orderItemDTO) > 0) {
                        status = true;
                        url = "/order/orderInquiry"; // orderNo 존재 한다.
                    }
                }
            }
            if(status) {
                order.setOrderStatus(orderStatus);
                status = quoApiOrder(orderReqDTO);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return OrderResDTO.builder().status(status).result(url).build();
    }

    private boolean quoApiOrder(OrderReqDTO orderReqDTO) {
        try {
            OrderDTO order = orderReqDTO.getOrder();
            String orderStatus = order.getOrderStatus();

            // 제조쪽으로 발주 보내기!!
            BizApiKeyDTO bizApiKeyDTO = BizApiKeyDTO.builder().bizNo(order.getBizNo()).type("order").build();
            bizApiKeyDTO = orderDAO.findByBizApi(bizApiKeyDTO);
            order = orderDAO.findByOrderNo(order.getOrderNo());
            if(bizApiKeyDTO != null) {

                String uri = bizApiKeyDTO.getUrl() + "/" + orderStatus;
                OrderReqQuoDTO orderReqQuoDTO = OrderReqQuoDTO.builder().build();

                if("1".equals( orderStatus ) ) { // 발주 대기 단계
                    orderReqQuoDTO.setOrderNo(order.getOrderNo());
                    QuoOrderDTO quoOrderDTO = QuoOrderDTO.builder()
                        .orderNo(order.getOrderNo())
                        .bizNo(order.getBizNo())
                        .dstn(order.getDstn())
                        .deliDate(order.getDeliDate())
                        .orderDate(order.getOrderReqDate())
                        .build();
                    orderReqQuoDTO.setQuoOrderDTO(quoOrderDTO);
                    List<QuoOrderItemDTO> quoOrderItems = new ArrayList<>();
                    for(OrderItemDTO orderItemDTO : orderReqDTO.getOrderItems()) {
                        QuoOrderItemDTO quoOrderItemDTO = QuoOrderItemDTO.builder().itemCode(orderItemDTO.getItemCode()).qty(orderItemDTO.getQty()).build();
                        quoOrderItems.add(quoOrderItemDTO);
                    }
                    orderReqQuoDTO.setQuoOrderItem(quoOrderItems);
                } 

                if("2".equals( orderStatus ) ) { // 발주 취소 단계
                    orderReqQuoDTO.setOrderNo(order.getOrderNo());
                }

                if("3".equals( orderStatus ) ) { // 발주 확정 단계
                    orderReqQuoDTO.setOrderNo(order.getOrderNo());
                }

                MappingJacksonValue params = new MappingJacksonValue(orderReqQuoDTO);

                OrderApiDTO orderApiDTO = RestClient.create().post()
                    .uri(uri)
                    .header("Authorization", bizApiKeyDTO.getKey())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(params)
                    .retrieve()
                    .toEntity(OrderApiDTO.class)
                    .getBody();

                if(orderApiDTO != null) {
                    if(orderApiDTO.isStatus()) return true;
                } 
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return false;
    }

}
