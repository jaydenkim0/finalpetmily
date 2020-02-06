package com.kh.petmily.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.petmily.entity.FaqDto;
import com.kh.petmily.page.FaqPage;
import com.kh.petmily.repository.FaqDao;
import com.kh.petmily.service.board.FaqService;
import com.kh.petmily.service.board.FaqServiceImpl;
import com.kh.petmily.vo.FaqVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/faq")
public class FaqController {
	@Autowired
	FaqService faqService;
	
	@Autowired
	FaqDto faqDto;
	
	//게시글 목록
	@RequestMapping("/list")
	public String list (HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		int pagesize = 10;
		int navsize=10;
		int pno;
		try{
			pno =  Integer.parseInt(req.getParameter("pno"));
			if(pno <= 0) throw new Exception();
		}
		catch(Exception e) {
			pno = 1;
		}
		int finish = pno * pagesize;
		int start = finish - (pagesize -1);
		
		String type = req.getParameter("type");
		String keyword = req.getParameter("keyword");
		
		boolean isSearch = type != null && keyword != null;
		System.out.println("");
//		FaqService faqService = new FaqService();
		
		List<FaqVO>list;
		
		if(isSearch) {
			list = faqService.listAll(type,keyword,start,finish);
		}
		else {
			list = faqService.getList(start,finish);
		}
		int count = faqService.getCount(type, keyword);
		//뷰에서 필요한 데이터를 첨부(5개)
		req.setAttribute("pno", pno);
		req.setAttribute("count", count);
		req.setAttribute("list", list);
		req.setAttribute("pagesize", pagesize);
		req.setAttribute("navsize", navsize);
		
		model.addAttribute("list",list);
		
		return "board/faq/list";
	}
	
	//게시글 작성 화면
	@GetMapping("/write")
	public String write() {
		return "board/faq/write";
	}
	//게시글 작성 처리
	@PostMapping("/insert")
	public String insert(@ModelAttribute FaqVO faqVO,HttpSession session) throws Exception{
		String member_id = (String)session.getAttribute("member_id");
		faqVO.setMember_id(member_id);
		faqService.create(faqVO);
		return "redirect:list";
	}
	//게시글 상세내용 조회
	@GetMapping("/view")
	public ModelAndView view(@RequestParam int faq_no, HttpSession session)throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/faq/view");
		mav.addObject("faqVO", faqService.read(faq_no));
		return mav;
	}
	//게시글 수정
	@GetMapping("/update")
	public String update(@RequestParam int faq_no, Model model)throws Exception{
		//FaqDto view 내용을 수정
		FaqVO view = faqService.read(faq_no); 
		model.addAttribute("faqVO",view);
		return "board/faq/update";
	}
	//게시글 수정 처리
	@PostMapping("/update")
	public String update(FaqVO faqVO)throws Exception{
		faqService.update(faqVO);
		return "redirect:list";
	}
	//게시글 삭제
	@RequestMapping("/delete")
	public String delete(@RequestParam int faq_no) throws Exception{
		faqService.delete(faq_no);
		return "redirect:list";
	}
}

