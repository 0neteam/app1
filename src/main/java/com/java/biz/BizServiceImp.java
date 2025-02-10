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
//
//        try {
//			//아래 코드들은 앞에서 가져올때 쓰는것들.
////			String bizName = req.getParameter("bizName");
////			String bizNum = req.getParameter("bizNum");
////			String adr = req.getParameter("adr");
////			String adrDetail = req.getParameter("adrDetail");
////			String bizType = req.getParameter("bizType");
////			String regDate = req.getParameter("regDate");
////			//
//// 			// 도움! stg_client 테이블말고 다른 테이블꺼 가져올수있는지?
////			//
////			BizDTO bizDTO = BizDTO.builder().bizName(bizName).bizNum(bizNum).adr(adr).adrDetail(adrDetail).
////					bizType(bizType).regDate(regDate).;
//
//            model.addAttribute("rs", bizDTO);
//            return "detail";
//} catch (NumberFormatException e) {
//            e.printStackTrace();
//            return "redirect:/";
//}
		return null; //임시
	}






}
