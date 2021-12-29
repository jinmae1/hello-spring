package com.kh.spring.memo.controller;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.memo.model.service.MemoService;
import com.kh.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/memo")
@Slf4j
public class MemoController {
	
	@Autowired
	private MemoService memoService;
	
	/*
	@GetMapping("/memo.do")
	public String memo() {

		return "memo/memo";
	}
	*/
	
	/**
	 * 위에 처럼 안하고 아래처럼 void로 리턴을 안 주어도 된다.
	 * 리턴하는 ViewName이 없는 경우, 요청 url로부터 jsp 경로를 유추해서 찾아간다.
	 * (ViewNameTranslator bean)
	 * 
	 *	/memo/memo.do -> "memo/memo"
	 */

	@GetMapping("/memo.do")
	public void memo(Model model) {
		log.debug("컨트롤러 주업무");
		List<Memo> list = memoService.selectMemoList();
		log.info("list = {}", list);
		
		model.addAttribute("list", list);
	}
	
	@PostMapping("/insertMemo.do")
	public String insertMemo(Memo memo, RedirectAttributes redirectAttr) {

		try {
			log.debug("memo = {}", memo);
			int result = memoService.insertMemo(memo);
			String msg = "메모 등록 성공!";
			redirectAttr.addFlashAttribute("msg", msg);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e; // spring container이 처리하도록 throw(위임, 전달)
		}
			return "redirect:/memo/memo.do";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// boolean allowEmpty - true, 빈문자열 ""인 경우 null 반환(아래 CustomDateEditor 생성자의 두번째 인자)
		PropertyEditor editor = new CustomDateEditor(sdf, true);
		// java.util.Date 변환 시, 해당 editor 객체 사용
		binder.registerCustomEditor(Date.class, editor);
	}
	
}
