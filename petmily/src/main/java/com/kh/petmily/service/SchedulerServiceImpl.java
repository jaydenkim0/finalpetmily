package com.kh.petmily.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kh.petmily.entity.AccountDto;
import com.kh.petmily.repository.AdminDao;
import com.kh.petmily.vo.AccountVO;

@Service
public class SchedulerServiceImpl implements SchedulerService{

	@Autowired
	private AdminDao adminDao;
	
	@Override
//	@Scheduled()
	public void accountPetsitter() {		
		// 1. 펫시터 아이디 구해오기(전월에 결제금액이 있는 펫시터만 구해오기)
		List<AccountVO> list = adminDao.findpetsitteraccount();
		for (AccountVO accountOne :list) {
				// 2. 펫시터 넘버로 아이디 구해오기
				int sitter_no = accountOne.getReservation_sitter_no();
				String sitter_id = adminDao.getSitter_id(sitter_no);
				// 3. 펫시터 결제건수 구해오기
				int count = adminDao.getCount(sitter_no);
				// 4. 펫시터 최종 매출 금액 구해오기 (완료된 금액에 취소된 금액 더하기)			
				int paymentP = adminDao.getPaymentPlus(sitter_no);
				int paymentM = adminDao.getPaymentMin(sitter_no);
				int total_pay = paymentP + paymentM;
				// 5. 펫시터 수수료 구해오기
				double fees = adminDao.getFees();		
				// 견적건수 20회 이상이면 수수료 인하 조건(슈퍼펫시터 일 경우)
//					if(count >20) {
//						
//					}else {
//						
//					}
				// 펫시터 별로 저장
				AccountDto accountDto = AccountDto.builder()
						.account_sitter_id(sitter_id)
						.account_count(count)
						.account_total_pay(total_pay)
						.account_fees(fees)
						.build();			
				// account에 저장하기
				adminDao.setaccountPetsitter(accountDto);
		}
	}
	

}
