package com.kh.petmily.repository;

import java.util.List;

import com.kh.petmily.entity.CareDto;

public interface CareDao {

	//게시글목록
	List<CareDto> list();

	//펫시터아이디로 펫시터번호 구하기
	int id_to_number(String care_sitter_id);

	//돌봄방 생성
	void write(CareDto careDto);
}
