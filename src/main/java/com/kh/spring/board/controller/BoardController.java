package com.kh.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping("/boardList.do")
	public void boardList(@RequestParam(defaultValue = "1") int cPage, Model model) {

		/**
		 * cPage = 1, 2, 3...
		 * RowBounds
		 * limit: 한 페이지 당 게시물 수(기존의 numPerPage) 여기선 10
		 * offset: 0, 10, 20... 몇개를 건너뛰어야 하는지, 이거는 limit과 cPage로 구한다.
		 * offset = (cPage - 1) * limit
		 */
		int limit = 10;
		int offset = (cPage - 1) * limit;
		Map<String, Object> param = new HashMap<>();
		param.put("offset", offset);
		param.put("limit", limit);

		List<Board> list = boardService.selectBoardList(param);
		log.debug("list = {}", list);
		model.addAttribute("list", list);
	}
}
