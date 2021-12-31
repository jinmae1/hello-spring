package com.kh.spring.board.model.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// lombok은 부모 클래스의 @AllArgsConstructor 사용이 불가능하기 때문에 직접 입력해주기
public class Board extends BoardEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int attachCount; // 게시물별 첨부파일 수

	public Board(int no, String title, String memberId, String content, Date regDate, int readCount, int attachCount) {
		super(no, title, memberId, content, regDate, readCount);
		this.attachCount = attachCount;
	}

}
