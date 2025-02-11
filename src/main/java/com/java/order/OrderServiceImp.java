package com.java.order;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}
