package com.java.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class OrderContoller {

    private final OrderService orderService;

    @GetMapping("/order/orderRequest")
    public String orderRequest(Model model) {
        model.addAttribute("bizs", orderService.findByBiz().getResult());
       return "order/orderRequest";
    }

    @PostMapping("/order/orderRequest")
    public String orderPostRequest(
            @RequestParam("selectedRows") String selectedRowsJson,  // JSON 문자열 받기
            @RequestParam("_csrf") String csrfToken,               // CSRF 토큰 받기
            @RequestParam String bizNo,                             // bizNo 값 받기
            @RequestParam String dueDate)                           // dueDate 값 받기
    {
        try {
            // 서비스 호출하여 주문 처리 후 리턴된 페이지 이름을 받아서 리턴
            return orderService.processOrderRequest(selectedRowsJson, bizNo, dueDate);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // 에러 처리
        }
    }

    // 발주 조회
    @GetMapping("/order/orderInquiry")
    public String orderInquiry(Model model) {
        return orderService.orderInquiry(model);
    }
    // 발주 조회 검색
    @GetMapping("/order/search")
    public String searchOrders(@RequestParam(value = "searchTerm", required = false) String searchTerm , Model model) {
        return orderService.searchOrders(model ,searchTerm);  // 검색 결과 반환
    }

    // 견적서 번호 받기


    @GetMapping("/estimate")
    public String handleQuoRequest(HttpServletRequest request) {

        int orderId = Integer.parseInt(request.getParameter("orderId"));

        System.out.println("text--------------------------------------: " + orderId);



        return "order/orderInquiry";
    }


    @GetMapping("/order/quotationReview")
    public String quotationReview() {
        return "order/quotationReview";
    }

    @GetMapping("/order/deliveryStatus")
    public String deliveryStatus() {
        return "order/deliveryStatus";
    }

    @ResponseBody
    @PostMapping("/order/findByBiz")
    public OrderResDTO findByBiz() {
        return orderService.findByBiz();
    }

    @ResponseBody
    @PostMapping("/order/list")
    public OrderApiDTO findByItems(@RequestParam("bizNo") Integer bizNo) {
        return orderService.findByItems(bizNo);
    }

}
