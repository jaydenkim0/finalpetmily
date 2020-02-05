package com.kh.petmily.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.kh.petmily.entity.MemberDto;
import com.kh.petmily.entity.PetDto;

public interface MemberDao {	
	
	void regist(MemberDto memberDto);
	
	MemberDto login(MemberDto memberDto);

	//내정보조회
	MemberDto mylist(String id);

	//반려동물조회
	List<PetDto> mylistpet(String id);

	//최종로그인일시업데이트
	void updatelastlogin(String id);

	//아이디찾기
	String findid(MemberDto memberDto);
}
