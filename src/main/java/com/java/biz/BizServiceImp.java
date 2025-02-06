package com.java.biz;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BizServiceImp implements BizService {

    //private final BizDao bizDao;

    @Override
    public String create(HttpServletRequest req) {
        return "biz/create";
    }
}
