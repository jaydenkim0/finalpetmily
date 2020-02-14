package com.kh.petmily.repository;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.petmily.entity.MemberDto;
import com.kh.petmily.entity.MemberImageDto;
import com.kh.petmily.entity.PetDto;
import com.kh.petmily.entity.PetImageDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void regist(MemberDto memberDto) {
		sqlSession.insert("member.regist", memberDto);		
	}

	@Override
	public MemberDto login(MemberDto memberDto) {		
		
		return sqlSession.selectOne("member.login", memberDto);		
	}

	//내정보조회
	@Override
	public MemberDto mylist(String id) {
		return sqlSession.selectOne("member.mylist",id);
	}
	
	//반려동물조회
	@Override
	public List<PetDto> mylistpet(String id){
		return sqlSession.selectList("member.mylistpet",id);
	}

	//최종로그인일시 업데이트
	@Override
	public void updatelastlogin(String id) {
		sqlSession.update("member.updatelastlogin",id);
		
	}

	//아이디찾기
	@Override
	public String findid(MemberDto memberDto) {
		return sqlSession.selectOne("member.findid",memberDto);
	}

	// 비밀번호 변경
	@Override
	public void pwchange(MemberDto memberDto) {	
		sqlSession.update("member.pwchange", memberDto);
	}


	@Override
	public void mylistchange(MemberDto memberDto) {
		sqlSession.update("member.mylistchange", memberDto);		
	}
	
	

	//펫등록
	@Override
	public void pet_regist(PetDto petDto) {
		sqlSession.insert("member.pet_regist",petDto);
	}

	//아이디중복검사
	@Override
	public int userIdCheck(String user_id) {
		return sqlSession.selectOne("member.userIdCheck",user_id);
	}

	//회원탈퇴처리
	@Override
	public void memberdelete(MemberDto memberDto) {
		sqlSession.delete("member.memberdelete",memberDto);
	}

	//회원 탈퇴되었는지 검사
	@Override
	public int idExist(String id) {
		return sqlSession.selectOne("member.idExist",id);
	}

	//회원 이미지 등록
	@Override
	public void member_image_regist(MemberImageDto memberImageDto) {
		sqlSession.insert("member.member_image_regist",memberImageDto);
	}

	//펫 번호 구해오기
	@Override
	public int pet_no() {
		return sqlSession.selectOne("member.pet_no");
	}

	//펫 이미지 등록
	@Override
	public void pet_image_regist(PetImageDto petImageDto) {
		sqlSession.insert("member.pet_image_regist",petImageDto);
	}


}
