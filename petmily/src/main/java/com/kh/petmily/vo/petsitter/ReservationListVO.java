package com.kh.petmily.vo.petsitter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ReservationListVO {
	//예약
	private int reservation_no;
	private int reservation_sitter_no; 
	private String member_id; 
	private String matching_time; 
	private String pet_name; 
	private String ect; 
	private String TotalMatchingTime;
	
	//예약 내역
	private String payname; // 금액 이름

	private int usage_time;//사용 시간
	private int start_time;//시작 시간
	
	//펫 정보
	private int pet_no;
	private int age; // 반려동물 나이
	private String type; // 반려동물의 종류
	private String pet_ect; // 반려동물의 기타 정보
	
	//펫 프로필 이미지
	private int pet_image_no;
	private int pet_image_pet_no;
	
	//매칭타임 시간 합치기
	public String getTotalMatchingTimeWithFormat()throws Exception{
		SimpleDateFormat read = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date date = read.parse(matching_time);
		SimpleDateFormat write = new SimpleDateFormat("y년 M월 d일");
		String time = write.format(date);
		return time;
		
	}
	
	List<ReservationAllVO> list;
	
}
