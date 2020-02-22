package com.kh.petmily.repository.petsitter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.petmily.entity.PetDto;
import com.kh.petmily.entity.PetsitterDto;
import com.kh.petmily.vo.MemberPetsVO;
import com.kh.petmily.vo.petsitter.PetsitterPetsVO;
import com.kh.petmily.vo.petsitter.PetsitterVO;
import com.kh.petmily.vo.petsitter.SitterlocationVO;

@Repository
public class PetsitterDaoImpl implements PetsitterDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getSequence() {
		int sitterNo = sqlSession.selectOne("petsitter.getSequence");
		return sitterNo;
	}
	
	@Override
	public void regist(PetsitterDto petsitterDto) {
		sqlSession.insert("petsitter.regist", petsitterDto);
	}

	@Override
	public List<PetsitterVO> getList(int pet_sitter_no) {
		List<PetsitterVO> petsitterList = sqlSession.selectList("petsitter.getList", pet_sitter_no);
		return petsitterList;
	}

	@Override
	public List<PetsitterPetsVO> getPetList(int pet_sitter_no) {
		List<PetsitterPetsVO> petList = sqlSession.selectList("petsitter.getPetList", pet_sitter_no);
		return petList;
	}

	@Override
	public List<PetsitterVO> getListAll() {
		List<PetsitterVO> petsitterListAll = sqlSession.selectList("petsitter.getListAll");
		return petsitterListAll;
	}

	@Override
	public List<SitterlocationVO> locationListAll(int start, int end, String cityKeyword, String areaKeyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		param.put("cityKeyword", cityKeyword);
		param.put("areaKeyword", areaKeyword);
		return sqlSession.selectList("petsitter.locationListAll", param);
	}

	@Override
	public int countlocation(String cityKeyword, String areaKeyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("cityKeyword", cityKeyword);
		param.put("areaKeyword", areaKeyword);
		return sqlSession.selectOne("petsitter.countlocation", param);
	}

	@Override
	public List<PetDto> getPet(String id) {
		return sqlSession.selectList("member.mylistpet", id);
	}

	@Override
	public PetsitterVO noGet(int pet_sitter_no) {
		PetsitterVO petsitterVO = sqlSession.selectOne("petsitter.noGet", pet_sitter_no);
		return petsitterVO;
	}
	@Override
	public PetsitterVO idGet(String id) {
		PetsitterVO petsitterVO = sqlSession.selectOne("petsitter.idGet", id);
		return petsitterVO;
	}

	@Override
	public void updatePetsitter(PetsitterDto petsitterDto) {
		sqlSession.update("petsitter.update", petsitterDto);
		
	}
	
}
