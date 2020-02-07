package com.kh.petmily.service;

import java.util.List;

import com.kh.petmily.entity.BlackListDto;
import com.kh.petmily.entity.CareConditionNameDto;
import com.kh.petmily.entity.CarePetTypeNameDto;
import com.kh.petmily.entity.LocationDto;
import com.kh.petmily.entity.MemberDto;
import com.kh.petmily.entity.PetsitterDto;
import com.kh.petmily.entity.SkillNameDto;
import com.kh.petmily.entity.SkillsDto;
import com.kh.petmily.vo.PetsitterVO;

public interface AdminService {
	
	// 총 회원수
	int memberTotal();
	// 총 펫시터 수 
	int petsitterTotal();
	// 총 관리자 수
	int admimTotal();

	// 회원 리스트
	List<MemberDto> memberList(MemberDto memberDto);
	
	// 펫시터 리스트
	List<PetsitterVO> petsitterList();
	
	// 펫시터 신청 리스트
	List<PetsitterVO> petsitterApplyList();
	
	// 휴면 펫시터 리스트
	List<PetsitterVO> petsitterSleepList();
	
	// 펫시터 승인
	void petsitterapply(String sitter_id);
	
	// 펫시터 거부
	void petsitterNegative(String sitter_id, int sotter_no);
	
	// 펫시터 단일 검색
	PetsitterVO PetsitterSearchOne(String sitter_id);
	
	// 펫시터 차단
	void blackSitter(PetsitterDto petsitterDto, String blacklist_content);
	
	// 펫시터 상태 변환
	void sitter_status(PetsitterDto petsitterDto);
	
	// 펫시터 옵션 : 돌봄가능동물
	List<CarePetTypeNameDto> carePetType();
	List<SkillNameDto> petSkillsName();
	List<CareConditionNameDto> petCareCondition();
	
	
	// 펫시터 옵션 등록 : 돌봄가능동물
	void carePetTypeI(String care_type);
	// 펫시터 옵션 삭제 : 돌봄가능동물
	void carePetTypeD(int care_type_no);
	
	
	// 펫시터 옵션 등록 : 스킬
	void petSkillNameI(String skill_name);
	// 펫시터 옵선 삭제 : 스킬
	void petSkillNameD(int skill_no);
	

	// 펫시터 옵션 등록 : 환경
	void petCareConditionI(String care_condition_name);
	// 펫시터 옵션 삭제 : 환경
	void petCareConditionD(int care_condition_no);
	
	// 펫시터 블랙리스트
	List<BlackListDto> sitterBlackList();
	List<BlackListDto> memberBlackList();
	
	// 페시터 회원 정보 (단일조회)
	PetsitterVO petsitterdetail(int pet_sitter_no);
	// 펫시터 회원정보 (지역) 
	List<LocationDto> petsitterdetailLocation(int pet_sitter_no);
	// 펫시터 회원정보 (돌봄가능동물) 
	List<CarePetTypeNameDto> petsitterdetailCarePet(int pet_sitter_no);
	// 펫시터 회원정보 (스킬) 
	List<SkillNameDto> petsitterdetailSkills(int pet_sitter_no);
	// 펫시터 회원정보 (펫시터 환경) 
	List<CareConditionNameDto> petsitterdetailCareCondition(int pet_sitter_no);
	
	
}
