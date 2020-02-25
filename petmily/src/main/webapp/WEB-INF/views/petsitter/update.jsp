<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>    
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>    
<!-- naver toast ui editor를 쓰기 위해 필요한 준비물 -->
<link rel="stylesheet" type="text/css"
	href="${context}/resources/lib/toast/css/codemirror.min.css">
<link rel="stylesheet" type="text/css"
	href="${context}/resources/lib/toast/css/github.min.css">
<link rel="stylesheet" type="text/css"
	href="${context}/resources/lib/toast/css/tui-color-picker.min.css">
<link rel="stylesheet" type="text/css"
	href="${context}/resources/lib/toast/dist/tui-editor.min.css">
<link rel="stylesheet" type="text/css"
	href="${context}/resources/lib/toast/dist/tui-editor-contents.min.css">

<script src="https://code.jquery.com/jquery-latest.js"></script>
<script
	src="${context}/resources/lib/toast/dist/tui-editor-Editor-full.min.js"></script>
<!-- 네이버 토스트에디터 종료 -->	
	
	<script>
	 $(function(){
//다중 선택 출력 스크립트 		 
         $("input[type=checkbox]").change(function() {
             //배열 선언
             var skillArray = [];
             var carePetTypeArray = [];
             var careConditionArray = [];

             //체크박스에 체크된 경우
             var skillCheck = $('.skill > input[type=checkbox]:checked');
             var typeCheck = $('.type > input[type=checkbox]:checked');
             var conditionCheck = $('.condition > input[type=checkbox]:checked');

             //반복해서 데이터를 배열에 저장해라
             $(skillCheck).each(function(i){ //스킬
                 skillArray.push($(this).data("skills"));
             });

             $(typeCheck).each(function(i){ //동물종류
                 carePetTypeArray.push($(this).data("animal"));
             });

             $(conditionCheck).each(function(i){ //돌봄환경
                 careConditionArray.push($(this).data("condition"));
             });

                 $("#skills_text").empty();
                 for (var i in skillArray)
                 {
                     $("<span>").text(skillArray[i]+"/").appendTo("#skills_text");
                 }
  
                 $("#care_pet_type_text").empty();
                 for (var i in carePetTypeArray)
                 {
                     $("<span>").text(carePetTypeArray[i]+"/").appendTo("#care_pet_type_text");
                 }

                 $("#care_condition_text").empty();
                 for (var i in careConditionArray)
                 {
                     $("<span>").text(careConditionArray[i]+"/").appendTo("#care_condition_text");
                 }               
         });
         
//지역 관리 스크립트
         $.ajax({
             url:"../resources/json/petmily_location.json", 
             type:"get",             
             dataType:"json",       
             success:function(resp){ 
             for(var i in resp){
                 $("<option>").text(i).appendTo(".region");
                 }    
             }
         });

         $(".region").change(function(){
             var region_text = $(this).children("option:selected").text();
             console.log("시 : "+ region_text);
             var city=$(this).val();

             $.ajax({
                     url:"../resources/json/petmily_location.json",  
                     type:"get",             
                     dataType:"json",       
                     success:function(resp){ 
                         $(".section").empty();
                         $(resp[city]).each(function(){
                             $("<option>").text(this).appendTo(".section");
                             });
                     }
                 });
         });

         $(".section").change(function(){
             var section_text = $(this).children("option:selected").text();
             console.log("군,구 : "+section_text);
         });  

//-----------------지역 추가 코드-----------------
         $("#add-btn").click(function(e){
             e.preventDefault();

             var region_len = $(".region").length; 
             console.log(region_len);

             var section_len = $(".region").length; 
             console.log(section_len);

             var test = $("<div>");

             var region = $("<select>");
                 region.addClass("region");
                 region.attr("name","location_name["+region_len+"].city");

             var section = $("<select>");
                 section.addClass("section");
                 section.attr("name","location_name["+section_len+"].area");

                 var test11 = region.attr("name");
                 console.log(test11);
                 var test22 = section.attr("name");
                 console.log(test22);


             var button = $("<button>").text("삭제");  
                   
             $.ajax({
                 url:"../resources/json/petmily_location.json",   
                 type:"get",             
                 dataType:"json",       
                 success:function(resp){ 
                 for(var n in resp){
                     // console.log(n);
                     $("<option>").text(n).appendTo(region);
                     region.appendTo(test);   
                 }

             $(region).change(function(){
                 var add_region = $(this).children("option:selected").text();
                 console.log("추가_시 : "+add_region);

                 var city=$(this).val();

                 $.ajax({
                     url:"../resources/json/petmily_location.json", 
                     type:"get",             
                     dataType:"json",       
                     success:function(resp){ 

                         $(section).empty();

                         $(resp[city]).each(function(){
                             // console.log(this);
                             $("<option>").text(this).appendTo(section);
                                 section.appendTo(test);
                                 button.appendTo(test);
                         });
                     } 
                 });
             });
             test.appendTo("#result"); 
                 }
             });     
             $(section).change(function(){
                 var add_section = $(this).children("option:selected").text();
                 console.log("추가_군,구 : "+add_section);

             $(button).click(function(){
                 test.remove();
             });    
             });  
         });
     });
	</script>
<!-- 네이버 토스트 에디터 스크립트 -->
<script>
        $(function(){
            //생성은 항상 옵션 먼저 + 나중에 생성
            var options = {
                //대상
                el:document.querySelector(".naver-editor"),
                //미리보기 스타일(vertical / horizontal)
                previewStyle:"horizontal",
                //입력 스타일
                initialEditType:"wysiwyg",
                //높이
                height:"300px",
                
                hooks: {
                    'addImageBlobHook': function(blob, callback) {
                        //이미지 블롭을 이용해 서버 연동 후 콜백실행
                        //callback('이미지URL');
                        console.log("이미지 업로드");
                    }
                }
            };

            var editor = tui.Editor.factory(options);

            //에디터의 값이 변하면 뒤에 있는 input[type=hidden]의 값이 변경되도록 처리
            editor.on("change", function(){
                var text = editor.getValue();//에디터에 입력된 값을 불러온다
                document.querySelector(".naver-editor + input[type=hidden]").value = text;  
            });
            var text = document.querySelector(".naver-editor + input[type=hidden]").value;
            editor.setValue(text);//값 설정
        });
    </script>
<!-- 네이버 토스트 에디터 스크립트 종료 -->	

    
<h1>펫시터 정보 수정</h1>

<form action="update" method="post" enctype="multipart/form-data">

<!-- 회원 아이디 -->
	<input type="hidden" name="sitter_id" value="${id}">
<!-- 펫시터 번호 -->
	<input type="hidden" name="pet_sitter_no" value="${pet_sitter_no}">
	<h1>${pet_sitter_no}</h1>
	
<!-- 소개 이미지 파일 -->	
	<label for="info_image">소개 이미지</label>
	<input type="file" id="info_image" name="info_image" multiple accept="image/*">
	
<!-- 통장 사본 이미지 파일 -->	
	<label for="id_card_file">통장 사본 이미지</label>
	<input type="file" id="bank_image" name="bank_image" multiple accept="image/*" required>
	
<!-- 통장 계좌 -->
	<div>
		<span>계좌번호는 - 제외한 번호만 입력해주세요.</span>
		<label for="bankName">은행</label>
			<select id="bankName" name="sitter_bankname"> 
				<option value="" selected disabled hidden >은행선택</option>
				<option>기업은행</option>
				<option>국민은행</option>
				<option>우리은행</option>
				<option>신한은행</option>
				<option>KEB하나은행</option>
				<option>농협은행</option>
				<option>SC제일은행</option>
				<option>한국씨티은행</option>
				<option>우체국</option>
				<option>경남은행</option>
				<option>광주은행</option>
				<option>대구은행</option>
				<option>산업은행</option>
				<option>새마을금고</option>
				<option>수협</option>
				<option>신협</option>
				<option>전북은행</option>
				<option>제주은행</option>
				<option>카카오뱅크</option>
				<option>케이뱅크</option>
			</select>	
			<label for="bank_account">계좌 번호</label>
			<input type="text" id="bank_account" name="sitter_bank_account" required>	
	</div>
	
<!-- 펫시터 소개글 -->	
	<div>
		<table width="60%">
			<tr>
				<td><label for="info-text">펫밀리 기본 정보</label></td>
			</tr>
			<tr>
					<td>
					<!-- value 수정 -->
					<div class="naver-editor"></div>
					 <input type="hidden" id="info-text" name="info" value=""></td>
			</tr>
			</table>
	</div>
<!--반려동물 경험 -->
	<div>
		<label for="yn">반려동물 키워본 경험 유무</label>
		<select id="yn" name="sitter_pets">
			<option>예</option>
			<option>아니오</option>
		</select>
	</div>
	
<!-- 매칭(돌봄) 종류 -->
	<div>
		<label for="mt">가능한 돌봄 종류</label>
		<select id="mt" name="sitter_matching_type">
			<option>방문서비스</option>
			<option>위탁서비스</option>
			<option>둘다</option>
		</select>
	</div>
	
<!-- 스킬 -->
	<div class="skill">
	<h5>기존의 스킬에 상관 없이 지금 가능한 스킬을 선택해주세요.</h5>
        <input type="checkbox" id="sick" value="1" name="skills_name" data-skills="투약">
        <label for="sick" id="nameTo1">투약</label>
        
        <input type="checkbox" id="old" value="2" name="skills_name" data-skills="노령견테어">
        <label for="old" id="nameTo2">노령견케어</label>
        
        <input type="checkbox" id="kitten"  value="3" name="skills_name" data-skills="키튼케어">
        <label for="kitten" id="nameTo3">키튼케어</label>
        
        <input type="checkbox" id="walking"  value="4" name="skills_name" data-skills="도그워킹">
        <label for="walking" id="nameTo4">도그워킹</label>
        
        <div id="skills_text"></div>
    </div>

<!-- 돌봄 가능 동물 종류 -->
    <div class="type">
    <h5>기존의 돌봄 가능한 동물에 상관 없이 지금 돌봄 가능한 동물을 선택해주세요.</h5>
        <input type="checkbox" id="dog" value="1" name="care_name" data-animal="강아지">
        <label for="dog">강아지</label>
        
        <input type="checkbox" id="cat" value="2" name="care_name" data-animal="고양이">
        <label for="cat">고양이</label>
        
        <input type="checkbox" id="fish"  value="3" name="care_name" data-animal="물고기">
        <label for="fish">물고기</label>
        
        <input type="checkbox" id="rabbit"  value="4" name="care_name" data-animal="토끼">
        <label for="rabbit">토끼</label>
        
        <input type="checkbox" id="hamster" value="5" name="care_name" data-animal="햄스터">
        <label for="hamster">햄스터</label>
        
        <input type="checkbox" id="reptiles" value="6" name="care_name" data-animal="파충류">
        <label for="reptiles">파충류</label>        
        
        <div id="care_pet_type_text"></div>
    </div>

<!-- 돌봄 환경 -->
    <div class="condition">
    <h5>기존의 돌봄 환경에 상관 없이 지금 돌봄 환경을 선택해주세요.</h5>
        <input type="checkbox" id="apt" value="1" name="care_condition_name" data-condition="아파트">
        <label for="apt">아파트</label>
        
        <input type="checkbox" id="villa" value="2" name="care_condition_name" data-condition="빌라">
        <label for="villa">빌라</label>
        
        <input type="checkbox" id="oneroom"  value="3" name="care_condition_name" data-condition="원룸">
        <label for="oneroom">원룸</label>
        
        <input type="checkbox" id="housing"  value="4" name="care_condition_name" data-condition="주택">
        <label for="housing">주택</label>
        
        <input type="checkbox" id="baby" value="5" name="care_condition_name" data-condition="아기있음">
        <label for="baby">아기있음</label>
        
        <input type="checkbox" id="smoking" value="6" name="care_condition_name" data-condition="흡연자">
        <label for="smoking">흡연자</label>   
        
        <input type="checkbox" id="x" value="7" name="care_condition_name" data-condition="해당사항없음">
        <label for="x">해당사항없음</label>   
        
        <div id="care_condition_text"></div>
    </div>
	
<!-- 활동 지역 -->
	<div class="location">
	    <h5>기존의 활동 지역에 상관 없이 지금 활동 지역을 선택해주세요.</h5>
	    <div class="template">
	        <select class="region" name="location_name[0].city">
	            <option>지역을 선택하세요</option>
	        </select>
	        
	        <select class="section" name="location_name[0].area">
	            <option>구를 선택하세요</option>
	        </select>
		<button id="add-btn">추가</button>
	    </div>
	    <div id="result"></div>
	</div>
		
	<div>
		<input type="submit" value="수정">
	</div>
</form>