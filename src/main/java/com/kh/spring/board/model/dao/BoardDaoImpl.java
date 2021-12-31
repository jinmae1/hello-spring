package com.kh.spring.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<Board> selectBoardList() {
		return session.selectList("board.selectBoardList");
	}

}
