package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
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

	// 기본값인데 뭔지 알기위해 적어놓음
	// Exception.class만 원래 runtimeException이 기본값이다.(아마)
	// 클래스 레벨로 올리기 위해 주석처리
//	@Transactional(
//			propagation = Propagation.REQUIRED,
//			isolation = Isolation.READ_COMMITTED,
//			rollbackFor = Exception.class)
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
	
	// 메소드 레벨에 일일이 적어놓은거를 클래스 레벨로 올려서 일괄적으로 처리할 수 있다.
//	@Transactional(rollbackFor = Exception.class)
	public int insertAttachment(Attachment attach) {
		return boardDao.insertAttachment(attach);
	}

	@Override
	public Board selectOneBoard(int no) {
		// 1. board 테이블 조회
		Board board = boardDao.selectOneBoard(no);
		
		// 2. attachments 테이블 조회
		List<Attachment> attachments = boardDao.selectAttachmentListByBoardNo(no);
		
		// 3. 합치기
		board.setAttachments(attachments);

		return board;
	}

	@Override
	public Board selectOneBoardCollection(int no) {
		return boardDao.selectOneBoardCollection(no);
	}
}
