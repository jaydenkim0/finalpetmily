<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
	<h3> 회원 관리 </h3>
	
	<a href="${pageContext.request.contextPath}/admin/member"><button>멤버 페이지로 이동</button></a>
	
	<h3>	회원	정보</h3>		
	<table>
		<tbody>			
			<tr>
				<tr>			
					<td>아이디 : ${member.id}</td>					
				</tr>
				<tr>			
					<td>닉네임 : ${member.nick}</td>					
				</tr>
				<tr>			
					<td>이메일 : ${member.email}</td>					
				</tr>
				<tr>			
					<td>우편번호 : ${member.post}</td>
					<td>주소 : ${member.basic_addr}, ${member.extra_addr}</td>						
				</tr>
				<tr>			
					<td> 반려동물 경험 유무   : ${member.pets}</td>					
				</tr>
				<tr>			
					<td> 펫시터 가입일   : ${member.joindate}</td>					
				</tr>
				<tr>			
					<td> 펫시터 마지막로그인  : ${member.lastlogin}</td>					
				</tr>

				<tr>			
					<td>등급 : ${member.grade}</td>					
				</tr>			
				
				<tr>			
					<td>포인트 : ${member.point}</td>					
				</tr>		
		</tbody>	
	</table>
	
	
	<h3>반려동물 정보</h3>
	<c:forEach var="pets" items="${pets}">
		<table>
			<tbody>
				<tr>
					<td>이름 : ${pets.name}</td>
				</tr>
				<tr>
					<td>나이 : ${pets.age}</td>
				</tr>
				<tr>
					<td>종류 : ${pets.age}</td>
				</tr>
				<tr>
					<td>추가정보 : ${pets.ect}</td>
				</tr>			
			</tbody>				
		</table>	
	</c:forEach>
	

	
	<!-- 블랙리스트 등록된 회원여부 -->
	<c:choose>			
	<c:when test="${member.grade eq 'petsitter' && member.black_count > 0}">
		<div style="color:red;">
			<h3>※경고를 받은 펫시터입니다.  경고 내용은 블랙리스트 세부사항에서 확인하세요</h3>
			<h3>경고 횟수 : ${member.black_count}</h3>					
			<!-- 블랙리스트 등록 회원은 삭제 버튼 노출 -->
			<form action="${pageContext.request.contextPath}/admin/sitter_delete" method="get">			
					<input type="hidden" name="sitter_id" value="${member.id}">	
					<input type="hidden" name="sitter_no" value="${member.pet_sitter_no}">				
					<button type="submit" >경고 펫시터 탈퇴</button>						
			</form>	
			<!-- 블랙리스트 디테일 페이지로 이동 -->
			<form action="${pageContext.request.contextPath}/admin/blackListdetail" method="get">			
					<input type="hidden" name="id" value="${member.id}">									
					<button type="submit" >블랙리스트 세부사항으로 이동</button>						
			</form>		
		</div>
	</c:when>	
	<c:when test="${member.grade eq 'member' && member.black_count > 0}">
		<div style="color:#ff8d00;">
			<h3>※경고를 받은 회원입니다.  경고 내용은 블랙리스트 세부사항에서 확인하세요</h3>
			<h3>경고 횟수 : ${member.black_count}</h3>				
			<!-- 블랙리스트 등록 회원은 삭제 버튼 노출 -->
				<form action="${pageContext.request.contextPath}/admin/member_delete" method="get">			
					<input type="hidden" name="id" value="${member.id}">				
					<button type="submit" >경고 회원 탈퇴</button>						
			</form>	
			<!-- 블랙리스트 디테일 페이지로 이동 -->
			<form action="${pageContext.request.contextPath}/admin/blackListdetail" method="get">		
					<input type="hidden" name="id" value="${member.id}">						
					<button type="submit" >블랙리스트 세부사항으로 이동</button>						
			</form>		
		</div>
	</c:when>	
	<c:otherwise>
			<!-- 펫시터 경고 등록 버튼 -->	
			<form action="${pageContext.request.contextPath}/admin/member_blacklist_content" method="get">			
					<input type="hidden" name="id" value="${member.id}">				
					<button type="submit" >경고 회원 등록</button>						
			</form>
	</c:otherwise>
	</c:choose>	 
			
			
			