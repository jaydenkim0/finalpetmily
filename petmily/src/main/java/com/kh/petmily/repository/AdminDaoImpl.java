package com.kh.petmily.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.petmily.entity.MemberDto;
import com.kh.petmily.entity.PetsitterDto;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getMtotal() {
		return sqlSession.selectOne("admin.membercount");
	}
	
	@Override
	public int getPtotal() {
		return sqlSession.selectOne("admin.petsittercount");
	}
	
	@Override
	public int getAtotal() {
		return sqlSession.selectOne("admin.admincount");
	}
	
	// 회원리스트
	@Override
	public List<MemberDto> getMemberList(MemberDto memberDto) {		
		return sqlSession.selectList("admin.memberList", memberDto);
	}

	// 펫시터 리스트
	@Override
	public List<PetsitterDto> getPetsitterList(PetsitterDto petsitterDto) {		
		return sqlSession.selectList("admin.petsitterList", petsitterDto); 
	}

	// 펫시터 신청한 회원 검색
	@Override
	public List<PetsitterDto> getPetsitterApplyList(PetsitterDto petsitterDto) {
		return sqlSession.selectList("admin.petsitterApplyList", petsitterDto); 
	}

	// 펫시터 승인
	@Override
	public void petsitterApply(String sitter_id) {
		sqlSession.update("admin.petsitterApply", sitter_id);
		
	}


	

}
