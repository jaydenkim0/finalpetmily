package com.kh.petmily.repository;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.petmily.entity.AccountDto;
import com.kh.petmily.entity.BankImageDto;
import com.kh.petmily.entity.BlackListContentDto;
import com.kh.petmily.entity.BlackListDto;
import com.kh.petmily.entity.CareConditionNameDto;
import com.kh.petmily.entity.CarePetTypeNameDto;
import com.kh.petmily.entity.IdCardFileDto;
import com.kh.petmily.entity.InfoImageDto;
import com.kh.petmily.entity.LicenseFileDto;
import com.kh.petmily.entity.LocationDto;
import com.kh.petmily.entity.PayDto;
import com.kh.petmily.entity.PayinfoDto;
import com.kh.petmily.entity.PetDto;
import com.kh.petmily.entity.PetImageDto;
import com.kh.petmily.entity.PetsitterDto;
import com.kh.petmily.entity.QnaDto;
import com.kh.petmily.entity.SkillNameDto;
import com.kh.petmily.vo.AccountVO;
import com.kh.petmily.vo.CalculateVO;
import com.kh.petmily.vo.MemberVO;
import com.kh.petmily.vo.QnaVO;
import com.kh.petmily.vo.petsitter.PetsitterVO;

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
	// 어제 가입한 회원의 수
	@Override
	public int memberJoinall() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("admin.memberJoinall");
	}
	// 어제 펫시터 신청한 수
	@Override
	public int petsitterApplyup() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("admin.petsitterApplyup");
	}
	// 어제 등록된 신고게시물의 수
	@Override
	public int blackqnacount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("admin.blackqnacount");
	}
	// 어제 신고된 회원의 수
	@Override
	public int blacklistmembercount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("admin.blacklistmembercount");
	}
	// 어제 신고된 펫시터의 수
	@Override
	public int blacklistpetsittercount() {
		// TODO Auto-generated method stub
		return  sqlSession.selectOne("admin.blacklistpetsittercount");
	}




	
	
	

	// 펫시터 리스트
	@Override
	public List<PetsitterVO> getPetsitterList() {		
		return sqlSession.selectList("admin.petsitterList"); 
	}

	// 펫시터 휴면 리스트
	@Override
	public List<PetsitterVO> getPetsitterSleep() {
		return sqlSession.selectList("admin.petsleepList");
	}
	
	// 펫시터 신청한 회원 검색
	@Override
	public List<PetsitterVO> getPetsitterApplyList() {
		return sqlSession.selectList("admin.petsitterApplyList"); 
	}

	// 펫시터 승인
	@Override
	public void petsitterApply(String sitter_id) {
		sqlSession.update("admin.petsitterApply", sitter_id);
		
	}
	// 펫시터 거부 (삭제)
	@Override
	public void petsitterNegative(String sitter_id, int sitter_no) {
		// 펫시터 삭제
		sqlSession.delete("admin.petsitterNegative", sitter_id);
		// 등록 지역 삭제
		sqlSession.delete("admin.locationNegative", sitter_no);
		// 등록 돌봄가능 동물 삭제
		sqlSession.delete("admin.carePetTypeNegative", sitter_no);
		// 등록 스킬 삭제
		sqlSession.delete("admin.skillsNegative", sitter_no);
		// 등록 환경 삭제
		sqlSession.delete("admin.careConditionNegative", sitter_no);		
	}

	// 펫시터 단일 검색
	@Override
	public PetsitterVO petsitterSearchOne(String sitter_id) {
		return sqlSession.selectOne("admin.petsitterSearchOne", sitter_id);
	}	

	// 블랙리스트 등록 시퀀스 번호 미리 가지고 오기
	public int blackListsno() {
		return sqlSession.selectOne("admin.blackListsno");
	}
	
	// 펫시터 경고 등록 (블랙리스트 등록)
	@Override
	public void blackSitter(PetsitterDto petsitterDto, PetsitterVO petsitterVO
											,BlackListContentDto blackListContentDto) {
		// 펫시터 상태 휴면으로 변경
		sqlSession.update("admin.sitter_status", petsitterDto);
		// 블랙리스트 테이블에 등록
		sqlSession.insert("admin.blackList", petsitterVO);		
		// 블랙리스트 컨텐츠 테이블에 등록
		sqlSession.insert("admin.blackListContent", blackListContentDto);
	}
	// 회원 경고 등록
	@Override
	public void blackMember(BlackListDto blackListDto,
			BlackListContentDto blackListContentDto) {
		sqlSession.insert("admin.blackListMember", blackListDto);
		// 블랙리스트 컨텐츠 테이블에 등록
		sqlSession.insert("admin.blackListContent", blackListContentDto);
	}

	// 펫시터 상태 변환
	@Override
	public void sitter_status(PetsitterDto petsitterDto) {		
		sqlSession.update("admin.sitter_status", petsitterDto);		
	}
	// 블랙리스트 테이블에서 권한 변경
	@Override
	public void blackListgradechange(String sitter_id) {
		sqlSession.update("admin.blackListgradechange", sitter_id);
	}


	// 펫시터 옵션 등록 : 돌봄가능동물
	// 불러오기
	@Override
	public List<CarePetTypeNameDto> getCarePetType() {
		return sqlSession.selectList("admin.getCarePetType") ;
	}
	// 펫시터 옵션 등록 : 스킬 이름
	// 불러오기
	@Override
	public List<SkillNameDto> getPetSkills() {		
		return sqlSession.selectList("admin.getPetSkills");
	}
	// 펫시터 옵션 등록 : 환경 목록
	// 불러오기
	@Override
	public List<CareConditionNameDto> getPetCareCondition() {		
		return sqlSession.selectList("admin.getPetCareCondition");
	}
	
	
	//등록하기
	@Override
	public void carePetTypeI(String care_type) {
		sqlSession.insert("admin.carePetType", care_type);
	}
	// 삭제하기
	@Override
	public void carePetTypeD(int care_type_no) {	
		sqlSession.delete("admin.carePetTypeDelete", care_type_no);
	}


	// 등록하기
	@Override
	public void petSkillNameI(String skill_name) {
		sqlSession.insert("admin.petSkillNameI", skill_name);	
	}
	// 삭제하기
	@Override
	public void petSkillNameD(int skill_no) {
		sqlSession.delete("admin.petSkillNameD", skill_no);	
	}
	
	
	// 등록하기
	@Override
	public void petCareConditionI(String care_condition_name) {
		sqlSession.insert("admin.petCareConditionI", care_condition_name);
	}
	// 삭제하기
	@Override
	public void petCareConditionD(int care_condition_no) {
		sqlSession.delete("admin.petCareConditionD", care_condition_no)	;
	}

	// 펫시터 블랙리스트 불러오기
	@Override
	public List<BlackListDto> getSitterBlackList() {
		return sqlSession.selectList("admin.getSitterBlackList");
	}

	// 펫시터 블랙리스트 불러오기
	@Override
	public List<BlackListDto> getMemberBlackList() {
		return sqlSession.selectList("admin.getMemberBlackList");
	}

				// 페시터 회원 정보 (단일조회)
				@Override
				public PetsitterVO getPetsitterdetail(int pet_sitter_no) {		
					return sqlSession.selectOne("admin.getPetsitterdetail", pet_sitter_no);
				}
				// 펫시터 회원정보 (지역) 
				@Override
				public List<LocationDto> getPetsitterdetailLocation(int pet_sitter_no) {
					return sqlSession.selectList("admin.getPetsitterdetailLocation", pet_sitter_no);
				}
				// 펫시터 회원정보 (돌봄가능동물) 
				@Override
				public List<CarePetTypeNameDto> getPetsitterdetailCarePet(int pet_sitter_no) {
					return sqlSession.selectList("admin.getPetsitterdetailCarePet", pet_sitter_no);
				}
				// 펫시터 회원정보 (스킬) 
				@Override
				public List<SkillNameDto> getPetsitterdetailSkills(int pet_sitter_no) {
					return sqlSession.selectList("admin.getPetsitterdetailSkills", pet_sitter_no);
				}
				// 펫시터 회원정보 (펫시터 환경) 
				@Override
				public List<CareConditionNameDto> getPetsitterdetailCareCondition(int pet_sitter_no) {
					return sqlSession.selectList("admin.getPetsitterdetailCareCondition", pet_sitter_no);
				}

				
	// 회원 디테일 페이지 정보
	@Override
	public MemberVO getMemberdetail(String id) {
		return sqlSession.selectOne("admin.getMemberdetail", id);
	}
	// 회원 정보 페이지에 보여줄 반려동물 
	@Override
	public List<PetDto> getPets(String id) {					
		return sqlSession.selectList("admin.getPets", id);
	}	

	
	// 블랙리스트 등록 여부 검사
	@Override
	public int blackLsitcheck(String id) {		
		return sqlSession.selectOne("admin.blackLsitcheck", id);
	}

	// 블랙리스트 회원 탈퇴 (삭제)
	@Override
	public void memberdelete(String id) {
		sqlSession.delete("admin.memberdelete", id);		
	}

	// 블랙리스트에서 삭제
	@Override
	public void blackListdelete(String id) {
		sqlSession.delete("admin.blackListdelete", id);		
	}
	// 펫시터 블랙리스트 탈퇴시 등급변경
	@Override
	public void petsittersecession(String sitter_id) {
		sqlSession.update("admin.petsittersecession", sitter_id);
		
	}

	// 블랙리스트 디테일 페이지 내용 가지고 오기
	@Override
	public PetsitterVO blackListdetailSearch(String id) {		
		return sqlSession.selectOne("admin.blackListdetailSearch", id);
	}
	// 블랙리스트컨텐츠 내용 가지고 오기
	@Override
	public List<BlackListContentDto> blacklistcontent(String id) {		
		return sqlSession.selectList("admin.blacklistcontent", id);
	}
	
	
	// 펫시터 가진 소개정보가 몇개인지 가지고오기
	@Override
	public List<InfoImageDto> sitterInfoimage(int pet_sitter_no) {		
		return sqlSession.selectList("admin.sitterInfoimage", pet_sitter_no);
	}
	// 펫시터 소개이미지 가지고 오기(1장씩 요청)
	@Override
	public InfoImageDto getInfoImage(int info_image_no) {	
		return sqlSession.selectOne("admin.getInfoImage",  info_image_no);
	}
	// 펫시터 소개이미지 실제로 가지고오기(1장씩 요청)
	@Override
	public byte[] physicalInfoImage(String savename) throws IOException {		
		File file = new File("D:/upload/kh2c/info", savename);
		byte[] data = FileUtils.readFileToByteArray(file);
		return data;
	}

	
	// 펫시터가 가진 신분증 정보 가지고오기
	@Override
	public IdCardFileDto sitterIdcardimg(int pet_sitter_no) {		
		return sqlSession.selectOne("admin.sitterIdcardimg", pet_sitter_no);
	}
	// 펫시터 가진 신분증 이미지 가지고 오기 (1장)
	@Override
	public IdCardFileDto getSitteridcardimage(int id_image_no) {	
		return sqlSession.selectOne("admin.getSitteridcardimage", id_image_no);
	}
	// 펫시터 신분증 실제로 가지고오기(1장씩 요청)
	@Override
	public byte[] physicalidcardimage(String savename) throws IOException {		
		File file = new File("D:/upload/kh2c/idCard", savename);
		byte[] data = FileUtils.readFileToByteArray(file);
		return data;
	}
	
	
	// 펫시터 가진 라이센스 정보 가지고 오기
	@Override
	public  LicenseFileDto sitterLicenseimge(int pet_sitter_no) {
		return sqlSession.selectOne("admin.sitterLicenseimge", pet_sitter_no);
	}
	// 펫시터 가진 라이센스 이미지 가지고 오기
	@Override
	public  LicenseFileDto getSitterlicenseimage(int license_image_no) {	
		return sqlSession.selectOne("admin.getSitterLicenseimge", license_image_no);
	}
	// 펫시터 신분증 실제로 가지고오기(1장씩 요청)
	@Override
	public byte[] physicallicenseimage(String savename) throws IOException {		
		File file = new File("D:/upload/kh2c/license", savename);
		byte[] data = FileUtils.readFileToByteArray(file);
		return data;
	}
	
	// 펫시터 가진 통장사본 정보 가지고 오기
	@Override
	public BankImageDto sitterBankimge(int pet_sitter_no) {	
		return sqlSession.selectOne("admin.sitterBankimge", pet_sitter_no);
	}
	// 펫시터 가진 통장사본 이미지 가지고 오기
	@Override
	public BankImageDto getSitterbankimage(int bank_image_no) {		
		return sqlSession.selectOne("admin.getSitterBankimge", bank_image_no);
	}
	// 펫시터 통장사본 실제로 가지고오기(1장씩 요청)
	@Override
	public byte[] physicallbankimage(String savename) throws IOException {
		File file = new File("D:/upload/kh2c/bank", savename);
		byte[] data = FileUtils.readFileToByteArray(file);
		return data;
	}
		
	
	// 회원 및 펫시터 복귀(블랙리스트에서 삭제)
	@Override
	public void gradeComback(String black_id) {
		sqlSession.update("admin.gradeComback", black_id);		
	}


	///////////////////////////////////////////////////////

	// 회원 페이징 리스트 (검색포함)
	@Override
	public List<MemberVO> memberListAll(int start, int end, String searchOption, String keyword) {
		// 검색옵션, 키워드 맵에 저장
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		param.put("start", start);
		param.put("end", end);		
		return sqlSession.selectList("admin.memberListAll", param);
	}
	// 회원 리스트 총 카운트 불러오기
	@Override
	public int countAricle(String searchOption, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		return sqlSession.selectOne("admin.countArticle", param);
	}

	// 펫시터 페이징 리스트(검색포함)
	@Override
	public List<PetsitterVO> petsitterListAll(int start, int end, String searchOption, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		param.put("start", start);
		param.put("end", end);		
		return sqlSession.selectList("admin.petsitterListAll", param);
	}
	// 펫시터 리스트 총 카운트 불러오기(페이징에 필요)
	@Override
	public int countAriclePetsitter(String searchOption, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		return sqlSession.selectOne("admin.countAriclePetsitter", param);
	}

	// 펫시터 승인 페이징 리스트(검색포함)
	@Override
	public List<PetsitterVO> petsitterApplyListAll(int start, int end, String searchOption, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		param.put("start", start);
		param.put("end", end);		
		return sqlSession.selectList("admin.petsitterApplyListAll", param);
	}
	// 펫시터 승인 페이징 리스트(검색포함)
	@Override
	public int countAriclePetsitterApply(String searchOption, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		return sqlSession.selectOne("admin.countAriclePetsitterApply", param);
	}

	// 휴면 펫시터 리스트
	@Override
	public List<PetsitterVO> petsitterSleepListAll(int start, int end, String searchOption, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		param.put("start", start);
		param.put("end", end);		
		return sqlSession.selectList("admin.petsitterSleepListAll", param);
	}
	// 휴면 펫시터 카운트
	@Override
	public int countAriclePetsitterSleep(String searchOption, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		return sqlSession.selectOne("admin.countAriclePetsitterSleep", param);
	}

	// 경고 회원 리스트
	@Override
	public List<PetsitterVO> blackMemberListAll(int start, int end, String searchOption, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		param.put("start", start);
		param.put("end", end);		
		return sqlSession.selectList("admin.blackMemberListAll", param);
	}
	// 경고 회원 카운트
	@Override
	public int countAricleBlackMember(String searchOption, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		return sqlSession.selectOne("admin.countAricleBlackMember", param);
	}

	// 경고  펫시터 리스트
	@Override
	public List<PetsitterVO> blackPetsitterListAll(int start, int end, String searchOption, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		param.put("start", start);
		param.put("end", end);		
		return sqlSession.selectList("admin.blackPetsitterListAll", param);
	}
	// 경고 펫시트 카운트
	@Override
	public int countAricleBlackPetsitter(String searchOption, String keyword) {
		System.out.println("searchOption = "+searchOption);
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		return sqlSession.selectOne("admin.countAricleBlackPetsitter", param);
	}

	// 가격 옵션 리스트 불러오기
	@Override
	public List<PayinfoDto> getAccountlist() {		
		return sqlSession.selectList("admin.getAccountlist");
	}
	// 수수료 옵션 리스트 불러오기
	@Override
	public List<PayinfoDto> getFeesList() {
		return sqlSession.selectList("admin.getFeesList");
	}
	// 가격 옵션 등록하기
	@Override
	public void accountOtionAdd(PayinfoDto payinfoDto) {		
		sqlSession.insert("admin.accountOtionAdd", payinfoDto);
	}
	// 가격 옵션 삭제
	@Override
	public void accountoptiondelete(int payinfo_no) {
		sqlSession.insert("admin.accountoptiondelete", payinfo_no);
		
	}
	// 가격 옵션 수정
	@Override
	public void accountoptionupdate(PayinfoDto payinfoDto) {
		sqlSession.update("admin.accountoptionupdate", payinfoDto);

	}

	// 신고게시판 불러오기
	@Override
	public List<QnaVO> getBlackreport(int start, int end, String searchOption, String keyword) {		
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		param.put("start", start);
		param.put("end", end);		
		return sqlSession.selectList("admin.getBlackreport", param);
	}
	
	@Override
	public int countAriclegetBlackreport(String searchOption, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		return sqlSession.selectOne("admin.countAriclegetBlackreport", param);
	}

	// 예약 게시판 리스트
	@Override
	public List<AccountVO> getAccountreservationList(int start, int end, String searchOption, String keyword) {	
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		param.put("start", start);
		param.put("end", end);			
		return sqlSession.selectList("admin.getAccountreservationList", param);
	}
	// 예약게시판 카운트
	@Override
	public int countAriclegetAccount(String searchOption, String keyword) {	
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		return sqlSession.selectOne("admin.countAriclegetAccount", param);
	}
	// 디테일 페이지
	@Override
	public AccountVO reservationstatusdetail(int reservation_no) {	
		return sqlSession.selectOne("admin.reservationstatusdetail", reservation_no) ;
	}
	// 서비스 결제 네임
	@Override
	public List<PayinfoDto> payinfoName(int reservation_no) {	
		return sqlSession.selectList("admin.payinfoName", reservation_no);
	}
	// 예약 디테일 페이지
	@Override
	public List<PayDto> paydetail(int reservation_no) {		
		return sqlSession.selectList("admin.paydetail", reservation_no);
	}
	// 결제 취소 버튼 유무 확인
	@Override
	public int paymentcanclecheck(int reservation_no) {
		return sqlSession.selectOne("admin.paymentcanclecheck", reservation_no);
	}

	@Override
	public CalculateVO getCalculateAllinfor(int type) {
		return  CalculateVO.builder()
					.totalPayment(sqlSession.selectOne("admin.getTotalPayment", type))
					.totalCancelPayment(sqlSession.selectOne("admin.getTotalCancelPayment", type))
					.reservatToTalCount(sqlSession.selectOne("admin.getReservatToTalCount", type))
					.reservatApplyToTalCount(sqlSession.selectOne("admin.getReservatApplyToTalCount", type))
					.reservatWaitToTalCount(sqlSession.selectOne("admin.getReservatWaitToTalCount", type))
					.totalPaymentCount(sqlSession.selectOne("admin.getTotalPaymentCount", type))
					.totalPaymentCancelCount(sqlSession.selectOne("admin.getTotalPaymentCancelCount", type))
					.build();			
	}

	// 정산 페이징 리스트
	@Override
	public List<AccountDto> getAccountList(int start, int end, String searchOption, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		param.put("start", start);
		param.put("end", end);		
		return sqlSession.selectList("admin.getAccountList", param);
	}

	// 정산 페이징 카운트
	@Override
	public int countAricleAccount(String searchOption, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("searchOption", searchOption);
		param.put("keyword", keyword);
		return sqlSession.selectOne("admin.countAricleAccount", param);
	}
	
	// account 테이블 정산 계산 후 인서트
	@Override
	public void setaccountPetsitter(AccountDto accountDto) {	
		sqlSession.insert("admin.setaccountPetsitter", accountDto);
	}	
	// 1. 펫시터 아이디 구해오기(전월에 결제금액이 있는 펫시터만 구해오기)
	@Override
	public List<AccountVO> findpetsitteraccount() {
		return sqlSession.selectList("admin.findpetsitteraccount");
		
	}
	// 2. 펫시터 넘버로 아이디 구해오기
	@Override
	public String getSitter_id(int sitter_no) {
		return sqlSession.selectOne("admin.getSitter_id", sitter_no);	
		
	}
	// 3. 펫시터 결제건수 구해오기
	@Override
	public int getCount(int sitter_no) {	
		return sqlSession.selectOne("admin.getCount",sitter_no);
	}
	// 4. 펫시터 최종 매출 금액 구해오기 (완료된 금액에 취소된 금액 더하기)	
	@Override
	public int getPaymentPlus(int sitter_no) {		
		return sqlSession.selectOne("admin.getPaymentPlus",sitter_no);
	}
	@Override
	public int getPaymentMin(int sitter_no) {
		return sqlSession.selectOne("admin.getPaymentMin",sitter_no);
	}

	@Override
	public double getFees() {		
		return sqlSession.selectOne("admin.getFees");
	}	
	@Override
	public double getFees2() {
		return sqlSession.selectOne("admin.getFees2");
	}
	// 개별 정산 입금 버튼
	@Override
	public void IndividualAccount(String sitter_id) {	
		sqlSession.update("admin.IndividualAccount", sitter_id);
	}
	// 일괄 정산 입금 버튼
	@Override
	public void batchAccount() {
		sqlSession.update("admin.batchAccount");
	}
	// 입금 대기 인원 수	
	@Override
	public int accountWcount() {		
		return sqlSession.selectOne("admin.accountWcount");
	}

	// 1시간당 요금
	@Override
	public int hourPayment() {		
		return sqlSession.selectOne("admin.hourPayment");
	}
	// 총 사용시간
	@Override
	public int totalTime(int reservation_no) {
		return sqlSession.selectOne("admin.totalTime", reservation_no);
	}
	// 시작 시간
	@Override
	public int startTime(int reservation_no) {
		return sqlSession.selectOne("admin.startTime", reservation_no);
	}

	// 인터셉터  경고 5회 이상 펫시터 기능 접근 금지 및 예약 금지
	@Override
	public int blackListc(String id) {
		return sqlSession.selectOne("admin.blackListc11", id);
	}

	// 펫 이미지 정보 가지고 오기
	@Override
	public List<PetDto> getPetImge(String member_id) {		
		return sqlSession.selectList("admin.getPetImge", member_id);
	}







	



	

}
