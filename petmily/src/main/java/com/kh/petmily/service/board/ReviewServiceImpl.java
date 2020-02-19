package com.kh.petmily.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.petmily.entity.ReviewDto;
import com.kh.petmily.repository.ReviewDao;
@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private ReviewDao reviewDao;
	
	// 게시글 리스트	
	@Override
	public List<ReviewDto> list()  {
		return reviewDao.list();
	}
   // 게시글 삭제
	@Override
	public void delete(int review_no) throws Exception {
	    reviewDao.delete(review_no);
		
	}
	// 게시글 조회
	@Override
	public List<ReviewDto> listSearch(int review_sitter_no) throws Exception {
//		System.out.println("service"+review_sitter_no);
		return reviewDao.listSearch(review_sitter_no);
	}
	// 리뷰 작성시 포인트 업
	@Override
	public void pointplus(ReviewDto reviewDto) {
		reviewDao.pointplus(reviewDto);	
	}
    // 리뷰 평균별점
	@Override
	public double star(int pet_sitter_no){
		return reviewDao.star(pet_sitter_no);
		
	}




	}


	
	

	
	

