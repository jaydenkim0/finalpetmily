<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<script>
    $(function () {
    		// 펫시터 거부 버튼 클릭시 발생
			$(".petnegative").submit(function(e) {
				// 이벤트 정지
				e.preventDefault();				
				// 버튼 속성 및 내용 변경
				$("#nega-btn").prop("disabled", true);
				$("#nega-btn").text("경고회원 등록중");				
				
				var url = $(this).attr("action"); 
				var method = $(this).attr("method");
				var data = $(this).serialize();
				
					$.ajax({
						url:url,
						type:"post",
						data:data,
						success:function(resp){
							console.log(resp);
							if(resp == "success"){
								alert("이메일 발송 완료");								
								location.href = '${pageContext.request.contextPath}/admin/blackList';
							}
							else{
								alert("이메일 발송 실패");								
								$("#nega-btn").prop("disabled", false);
								$("#nega-btn").text("등록하기");
							}
						}
					});
				});						
			});
   	 </script>
	
		 <style>
		 button{
		 	background: #146fbd;
		    padding: 7px 14px;
		    color: #fff;
		    border: 0;
		    font-weight: bold;
		    border-radius:3px;
		 }
		 button:hover{
		 	background: #10538c;
		 }
		 h4{
		 	padding:20px;
		 }	 
		 </style> 		 
	
	
	<a href="${pageContext.request.contextPath}/admin/"><button>메인으로</button></a>
	<br>		
	
	<h4>회원 경고 사유를 작성해주세요</h4>

	<h4> 신고하실 회원의 아이디는 ${id} 입니다 </h4>
	
	<form class="petnegative" action="member_blackListpage" method="post">				
		<input type="hidden" name="id" value="${id}">
		<textarea cols="150" rows="20" name="black_content"></textarea>
		<button style="display: block;" id="nega-btn"> 등록하기 </button>
	</form>