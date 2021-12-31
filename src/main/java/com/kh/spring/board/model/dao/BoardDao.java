package com.kh.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.vo.Board;

public interface BoardDao {

	List<Board> selectBoardList(Map<String, Object> param);

}
