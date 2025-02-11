package com.java.biz;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

public interface BizService {

    String list(Model model, HttpServletRequest req);

	String detail(Model model, HttpServletRequest req);

}
