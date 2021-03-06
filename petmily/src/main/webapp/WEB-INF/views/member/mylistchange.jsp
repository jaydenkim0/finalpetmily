<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>

	<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=534248faec0557257f5c7cc9e504a2da&libraries=services"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    function sample6_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수
	
	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }
	
	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
// 	                    document.getElementById("sample6_extraAddress").value = extraAddr;
	                
	                } else {
// 	                    document.getElementById("sample6_extraAddress").value = '';
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('sample6_postcode').value = data.zonecode;
	                document.getElementById("sample6_address").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("sample6_detailAddress").focus();
	            }
	        }).open();
	    }
	</script>
	<script>
		$(function(){
			$(".memberdelete_btn").click(function(){
				var url = $(".memberdelete").attr("action");
				var method = $(".memberdelete").attr("method");
				var data = $(".memberdelete").serialize();
				
				$.ajax({
					url:url,
					type:method,
					data:data
				});
			});
		});
	</script>
	<script>
	function no_image2(){
		$("#2").attr("src", "/petmily/resources/img/기본프로필.jpeg");
// 		$(".member_noimage").hide();
	}
	</script>
	<style>
	
	th ,td{
	
	padding: 10px;
	text-align: center;
	width:600px;
	}
	.input{
	width:400px;
	height:35px;
	BORDER-BOTTOM: teal 1px solid;
		BORDER-LEFT: medium none;
		BORDER-RIGHT: medium none;
		BORDER-TOP: medium none;
		FONT-SIZE: 9pt;
		BORDER-STYLE:none;     
		border-bottom:solid 1px #cacaca;
		border-collapse:collapse;
		HEIGHT:40PX;
	
	
	}
	table{
   padding-top:50px;
	width:410px;
	border-color : #BDBDBD;
	
	}
	
	button {
	position:relative;
	display: white;
   width: 70px;
   height: 30px;
   line-height: 20px;
   border: 1px #3399dd solid;
   background-color: white;
   text-align: center;
   font-size: 12px;
   cursor: pointer;
   color: #1482e0;
   transition: all 0.9s, color 0.3;
   border-radius:10px;
   left:20px;
   }
  .out{
   background-color:white;
   color: red;
   width:65%;
   }
   .box {
    width: 150px;
    height: 150px; 
    border-radius: 30%;
    overflow: hidden;
}  
	.petchbtn {
	position:relative;
	display: white;
   width: 70px;
   height: 30px;
   line-height: 20px;
   border: 1px #3399dd solid;
   background-color: white;
   text-align: center;
   font-size: 12px;
   cursor: pointer;
   color: #1482e0;
   transition: all 0.9s, color 0.3;
   border-radius:10px;
   left:20px;
   }
   .petchbtn:hover{
   	background-color: #3399dd;
   	color: white;
   }
   .fail{
   	font-size:15px;
   	color:red;
   }
  
  
	</style>
	<!-- 
HEADER 이용 시 넣어야할 요소 
:   jquery js,
   header css, 
   header script
-->
  <!-- header css -->
  <link rel="stylesheet" href="${context}/resources/css/header.css">
   <!-- header script -->
   <script>
      $(function() {
          $('body').addClass('js');
          $('#masthead').addClass('color');
          
          var $hamburger = $('.hamburger'),
              $nav = $('#site-nav'),
              $masthead = $('#masthead');

          $hamburger.click(function() {
            $(this).toggleClass('is-active');
            $nav.toggleClass('is-active');
            $masthead.toggleClass('is-active');
            return false; 
          })
      });
    </script>
    <!-- header style -->
    <style>
   #masthead:after {
     content: '';
     position: absolute;
     top: 0;
     width: 100%;
     height: 130px;
     background-color: #fff;
     opacity: 100;
     transition: opacity 0.3s ease;
   }
   
   #masthead.is-active{
    background-color: #fff;
   }
   
   .section-content{
   padding-top:150px;
   }
   </style>
<!-- 
FOOTER 이용 시 넣어야할 요소 
:   jquery js,
   footer css, 
   Required meta tags, 
   Bootstrap CSS,
   아이콘을 사용하기 위해 추가로 불러오는 CSS
-->
     <!-- footer css -->
    <link rel="stylesheet" href="${context}/resources/css/footer.css"/>  
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- 아이콘을 사용하기 위해 추가로 불러오는 CSS -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
	</head>
	
	<body>
	<!--  header 불러오기 -->
   <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
   
	<section class="section-content">
		<div align="center">
			<c:if test="${not empty param.fail }">
				<p class="fail">비밀번호 불일치 - 탈퇴 실패</p>
			</c:if>
		</div>
		<div class="mychange" align="center">
			<form action="mylistchange?id=${member.id}" method="post" enctype="multipart/form-data">
			<input type="hidden" name="member_image_no" value="${member_image_no }">
			    <table>
			    	<tr>
			    		<th>Image</th> 	
			    		<td>
							<div class="box" style="background: #BDBDBD;">
				    			<img src="${pageContext.request.contextPath }/member/member/image?member_image_no=${member_image_no}"  style="width: 100%; height: 100%;" onerror="no_image2()" id="2" class="imagecss">
							</div>
<!-- 			    			<p class="member_noimage" align="left"> -->
<!-- 			    				<input type="checkbox" name="member_noimage" value="true"> -->
<!-- 			    				기본이미지로 변경 -->
<!-- 			    			</p> -->
			    			<input type="file" name="member_image" accept="image/*">
			    		</td>
			    	</tr>
			    	<tr>
			    		<th>Name</th>
			    		<td>
			    			<input type="text"  name="name" value="${member.name}" class="input">
			    		</td>
			    	</tr>
			    	<tr>
			    		<th>NickName</th>
			    		<td>
			    			<input type="text"  name="nick" value="${member.nick}" class="input">
			    		</td>
			    	</tr>
			    	<tr>
			    		<th>Email</th>
			    		<td>
			    			<input type="text" name="email" value="${member.email}" class="input">
			    		</td>
			    	</tr>
			    	<tr>
			    		<th>Phone</th>
			    		<td>
			    			<input type="text"  name="phone" value="${member.phone}" class="input">
			    		</td>
			    	</tr>
					<tr>
						<th rowspan="3">Address</th>
						<td>
							<input type="text" id="sample6_postcode" name="post" placeholder="우편번호" value="${member.post}" class="input">
							<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="input"><br> 
						</td>
					</tr>
					<tr>
						<td>
							<input type="text" id="sample6_address" name="basic_addr" size="50" placeholder="기본주소" value="${member.basic_addr}" class="input">
						</td>
					</tr>
					<tr>
						<td>
							<input type="text"  id="sample6_detailAddress" name="extra_addr" size="50" placeholder="상세주소"value="${member.extra_addr}" class="input">
						</td>
					</tr>
			    </table>
			 	<br>
				<button type="submit" class="petchbtn" >수정</button>&nbsp;&nbsp;&nbsp;
				<a href="mylist">
					<button type="button" class="petchbtn">취소</button>
				</a>
			</form>
			</div>
			<hr width="50%">
			<br>
			<div class="out" align="right">
				<a href="memberdelete?id=${member.id}"><button class="petchbtn">탈퇴</button></a>	
			</div>
	</section>
	
	  <br><!-- footer 불러오기 -->
   <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include> 
	</body>
	
</html>