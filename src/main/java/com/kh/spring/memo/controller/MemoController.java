package com.kh.spring.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/memo")
@Slf4j
public class MemoController {
	
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
	public void memo() {

	}
	
}
