package com.java.biz;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class bizController {

    private final BizService bizService;

    @GetMapping("/comp/create")
    public String create(HttpServletRequest req){
        return bizService.create(req);
        
    }
}
