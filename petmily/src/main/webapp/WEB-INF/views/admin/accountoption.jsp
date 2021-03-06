<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  
	
	 <style>
 		a button{
	 	background: #146fbd;
	    padding: 7px 14px;
	    color: #fff;
	    border: 0;
	    font-weight: bold;
	    border-radius:3px;
		 }
		  a:hover button{
		 	background: #10538c;
		 }
		 h3{
		 	padding:20px;
		 }	 
		 	 /***form search****/
	 form{
	 	margin-top:16px;
	 } 
	 form select{
	 	height:30px;
	 	line-height:30px;
	 	vertical-align:middle;
	 }
	  form input[name="keyword"],
	  form input{
	  	height:30px;
	  	line-height:30px;
	  	color:#333;
	  }
	 form input[type="submit"]{
	     background: #146fbd;
	    color: #fff;
	    /* padding: 7px 14px; */
	    border: 0;
	    border-radius: 3px;
	    height: 30px;
	    line-height: 30px;
	    box-sizing: border-box;
	    padding: 0 16px;
	}
	 </style>
	
									
		<script>
		    $(document).ready(function () {
		    	 $('.modalfade').hide();
		            
		        $('.optionupdate').click(function () {						         
		        	$(this).parent().next().next().fadeToggle();
		        });						
		
		        
		        $('.updateform').submit(function() {
		        	var url = $(this).attr("action"); 
		      		var data = $(this).serialize();
		            $.ajax({
		                type: "post",
		                url: url,
		                data: data,					   
		                success: function(resp) {
		                	if (resp == "success"){						          						                    
		                    location.reload();
		                	}
		                }
		            });
		        });					
		    });				
		</script>

     					
	
	
	<a href="${pageContext.request.contextPath}/admin/account"><button> 정산 페이지로 이동</button></a>
	
	<h4>가격 옵션 설정</h4>
	
	<h4> 가격 리스트 </h4>
	<hr>
		<c:forEach var="accountList" items="${accountlist}" >
			
			<h1> </h1>
			<h5>가격명 : ${accountList.payname}</h5>
			<h5>가격 : ${accountList.payment} 원 </h5>	
				<a>
				<button class="optionupdate">수정</button>
				</a>		
				<a href="${pageContext.request.contextPath}/admin/accountoptiondelete?payinfo_no=${accountList.payinfo_no}">
				<button> 삭제</button>
				</a>
						<!-- 모달 -->
							<div class="modalfade" >			
								<form class="updateform" action="${pageContext.request.contextPath}/admin/accountoptionupdate" method="post">			
				                <h5>가격 정보 수정</h5><br>
				                <input type="hidden"  class="payinfo_no" name="payinfo_no" value=" ${accountList.payinfo_no}" >    							  
				                <label for="login-pw">금액</label>
				                <input type="number" class="payment" name="payment" placeholder="${accountList.payment}" required>
				                <br/>
				                <input type="submit" id="update-btn"  class="update" value="변경">
				                </form>
				            </div>
					
			<hr>			
			
		</c:forEach>
		
		<br><br>
		
		
	<h3> 수수료 리스트 </h3>
	<hr>
		<c:forEach var="accountList" items="${feesList}" >
			<div style="background: #bbbbbb;">
			<h5 >수수료명 : ${accountList.payname}</h5>
			<h5>수수료 : ${accountList.payment} % </h5>	
				<a href="${pageContext.request.contextPath}/admin/accountoptiondelete?payinfo_no=${accountList.payinfo_no}">
				<button> 삭제</button>
				</a>
			</div>
			<hr>	
		</c:forEach>
	
	
	<h3>금액 정보 추가</h3>
	
	<form action="${pageContext.request.contextPath}/admin/accountoption" method="post">
		<select name="paystatus">
			<option>가격</option>
			<option>수수료</option>
		</select>
		<input type="text" name="payname" placeholder="가격이름" required>
		<input type="number" name="payment" placeholder="가격" required step="any">
		<input type="submit" value="등록">
	</form>
	
	


