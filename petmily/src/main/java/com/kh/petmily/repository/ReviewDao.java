package com.kh.petmily.repository;

import java.util.List;

import com.kh.petmily.entity.ReviewDto;


public interface ReviewDao {
   //게시글 작성
	void insert(ReviewDto reviewDto)throws Exception;
   //게시글 조회
	List<ReviewDto> list();
	List<ReviewDto> listSearch(int review_sitter_no);
    //게시글 삭제
	void delete(int review_no) throws Exception;
	//게시글 수정정보 구해오기
	ReviewDto get(int review_no);
    //게시글 수정
	void update(ReviewDto reviewDto) throws Exception;
	//게시글 상세보기	
	
	
	

	
	


}
