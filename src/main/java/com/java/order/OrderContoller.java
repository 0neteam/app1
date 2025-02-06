package com.java.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class OrderContoller {

    @GetMapping("/order/orderRequest")
    public String orderRequest() {
       return "order/orderRequest";
    }

    @GetMapping("/order/orderInquiry")
    public String orderInquiry() {
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
