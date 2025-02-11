package com.java.biz;

import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Service
public class BizServiceImp implements BizService {

    private final BizDao bizDao;

	@Override
	public String list(Model model, HttpServletRequest req) {
		List<BizDTO> bizDtos = bizDao.findList();
		model.addAttribute("bizList", bizDtos);
		return "biz/list";
	}

	@Override
	public String detail(Model model, HttpServletRequest req) {
		try{
			int no = Integer.parseInt(req.getParameter("bizNo"));
			BizDTO biz1 = bizDao.findOne(no);
			model.addAttribute("bizResult", biz1);
			return "biz/detail";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}



}
