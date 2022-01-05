package com.kh.spring.board.model.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.kh.spring.member.model.vo.Member;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
/**
 * lombok이 super.toString()을 하지 않고 그냥 this.toString()만 해버려서 this의 필드만 toString()하기 때문에 명시적으로 @ToString(callSuper=true) 해준다.
 * 이 이전 커밋과 이후 커밋을 비교하여 출력결과를 비교해보기
 * 다시말해 super.toString() + " [attachCount=" + attachCount + "]"; 해줘야 한다는 소리
 *
 */
@ToString(callSuper=true)
@NoArgsConstructor
// lombok은 부모 클래스의 @AllArgsConstructor 사용이 불가능하기 때문에 직접 입력해주기
public class Board extends BoardEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int attachCount; // 게시물별 첨부파일 수
	private List<Attachment> attachments;
	private Member member;

	public Board(int no, String title, String memberId, String content, Date regDate, int readCount, int attachCount, List<Attachment> attachments, Member member) {
		super(no, title, memberId, content, regDate, readCount);
		this.attachCount = attachCount;
		this.attachments = attachments;
		this.member = member;
	}

}
