package com.java.biz;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class BizController {

    private final BizService bizService;

    /*
    * list
    * create
    * detail
    * */
    //

    //조회 //첫화면, 거래처목록 뿌려주는놈.
    @GetMapping("biz/list")
    public String list(Model model, HttpServletRequest req) {
        return bizService.list(model, req);
    }

    //등록 aka "(창고->제조사) 거래처 가입화면"
    @GetMapping("biz/create")
    public String create(){
        return "biz/create";
    }
    
    //상세
    @GetMapping("biz/detail")
    public String detail(Model model, HttpServletRequest req){
        return bizService.detail(model,req);
    }
}
