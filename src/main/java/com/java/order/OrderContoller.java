package com.java.order;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderContoller {

    private final OrderService orderService;

    @GetMapping("/order/orderRequest")
    public String orderRequest() {
       return "order/orderRequest";
    }

    @PostMapping ("/order/orderRequest")
    public String orderPostRequest(HttpServletRequest request) {
       return "order/orderRequest";
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

}
