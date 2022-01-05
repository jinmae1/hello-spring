package com.kh.spring.member.model.dao;

import java.util.List;
import java.util.Map;

import com.kh.spring.member.model.vo.Member;

public interface MemberDao {

	int insertMember(Member member);

	Member selectOneMember(String id);

	List<Member> findMemberByName(Map<String, Object> param);

}
