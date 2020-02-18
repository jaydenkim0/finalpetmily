package com.kh.petmily.repository.petsitter;

import java.util.List;

import com.kh.petmily.entity.ReservationDto;
import com.kh.petmily.entity.ReservationPayDto;

public interface ReservationDao {
	int getSequenceReservation();//예약 번호 구하기
	void registReservation(ReservationDto reservationDto);//예약 등록
	void registPay(List<Integer>payinfo_no, ReservationPayDto reservationPayDto);//예약 금액,사용 시간 등록
}
