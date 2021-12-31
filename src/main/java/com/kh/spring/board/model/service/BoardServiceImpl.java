package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public List<Board> selectBoardList(Map<String, Object> param) {
		return boardDao.selectBoardList(param);
	}

	@Override
	public int selectTotalContent() {
		return boardDao.selectTotalContent();
	}

	@Override
	public int insertBoard(Board board) {
		int result = boardDao.insertBoard(board);
		log.debug("boardNo = {}", board.getNo());
		List<Attachment> attachments = board.getAttachments();
		/*
			Servlet 쓸 때는 여기서 seq_board_no를 따로 구해줬어야 했다.
		*/
		if(attachments != null) {
			for(Attachment attach : attachments) {
				// fk컬럼 boardNo값 설정
				attach.setBoardNo(board.getNo());
				result = insertAttachment(attach);
			}
		}
		
		return result;
	}
	
	public int insertAttachment(Attachment attach) {
		return boardDao.insertAttachment(attach);
	}
}
