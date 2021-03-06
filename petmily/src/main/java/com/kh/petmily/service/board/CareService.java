package com.kh.petmily.service.board;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.kh.petmily.entity.CareDto;
import com.kh.petmily.entity.CareImageDto;
import com.kh.petmily.entity.CarePetsitterDto;
import com.kh.petmily.entity.CareReplyDto;
import com.kh.petmily.entity.CareReplyImageDto;
import com.kh.petmily.entity.PetsitterDto;

public interface CareService {

	//게시글 목록
	List<CareDto> list();

	//게시글목록(펫시터아이디)
	List<CarePetsitterDto> pet_sitter_list(int start,int finish);

	
	//펫시터아이디로 펫시터번호 구하기
	int id_to_number(String care_sitter_id);

	//돌봄방 생성
	void write(CareDto careDto);

	//돌봄방 정보 불러오기
	CareDto list(String care_board_no);

	//펫시터번호로 펫시터아이디 구하기
	String number_to_id(int care_sitter_no);

	//돌봄 방 제목 변경
	void content_edit(CareDto careDto);

	//돌봄 방 삭제
	void delete(String care_board_no);

	//돌봄 방 댓글 목록
	List<CareReplyDto> replylist(String care_board_no);

	//돌봄 방 댓글 등록
	void reply_regist(CareReplyDto careReplyDto);

	//돌봄 방 댓글 수정
	void reply_change(CareReplyDto careReplyDto);

	//돌봄 방 비밀번호 검사
	int care_board_password(CareDto careDto);

	//돌봄 방 댓글 삭제
	void reply_delete(CareReplyDto careReplyDto);

	//파일업로드를 위한 댓글번호찾기
	int find_care_reply_no();

	//돌봄이미지 등록
	void care_image_regist(int care_reply_no, MultipartFile care_image) throws IllegalStateException, IOException;

	//돌봄댓글&이미지 목록
	List<CareReplyImageDto> replyimagelist(String care_board_no,int start, int finish);
	
	//돌봄이미지 가지고 오기(1장씩 요청)
	ResponseEntity<ByteArrayResource> image(int care_image_no) 
			throws UnsupportedEncodingException, IOException;

	//해당 돌봄 방의 돌봄댓글 개수구하기
	int getCount(String care_board_no);

	//검색X돌봄방개수
	int getListCount();

	//care_board_no 검색목록
	List<CarePetsitterDto> pet_sitter_list_care_board_no(String keyword, int start, int finish);

	//care_board_no 검색개수
	int getListCount_care_board_no(String keyword);

	//care_member_id 검색목록
	List<CarePetsitterDto> pet_sitter_list_care_member_id(String keyword, int start, int finish);

	//care_member_id 검색개수
	int getListCount_care_member_id(String keyword);

	//sitter_id 검색목록
	List<CarePetsitterDto> pet_sitter_list_sitter_id(String keyword, int start, int finish);

	//sitter_id 검색개수
	int getListCount_sitter_id(String keyword);

	//care_board_content 검색목록
	List<CarePetsitterDto> pet_sitter_list_care_board_content(String keyword, int start, int finish);

	//care_board_content 검색개수
	int getListCount_care_board_content(String keyword);

	//회원이미지 가지고 오기(1장씩 요청)
	ResponseEntity<ByteArrayResource> member_image(String member_image_member_id)
			throws UnsupportedEncodingException, IOException;
	
	//펫시터 아이디 존재 검사
	int userIdCheck(String user_id);

	//펫시터아이디제공
	List<String> offer_id();

	//작성자 아이디 갖고오기
	String get_writer(int care_board_no);

	//펫시터 아이디 갖고오기
	String get_petsitter(int care_board_no);


}
