package com.sist.web.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.*;
import com.sist.web.vo.*;
/*
	JPA 방식
	 1. return 형
	 2. 매개변수
	 3. 메소드명 => 자동 지정
	 필요 시 메소드 규칙에 맞게 제작
	 WHERE 문장 => findBy 변수명 명령어 
	 WHERE name LIKE => less then, greater then
	 SQL 문장 필요 시 반드시 native query
 */
@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	public void saveMember(Member member)
	{
		memberRepository.save(member);
	}
	public void deleteMember(Member member)
	{
		memberRepository.delete(member);
	}
	public Iterable<Member> getAllMembers()
	{
		return memberRepository.findAll();
	}
	public Member getMemberId(String id)
	{
		return memberRepository.findById(id).orElse(null);
	}
}
