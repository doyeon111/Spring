<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시물 상세보기</h2>
	<hr>
	글번호: ${b.no }<br>
	글제목: ${b.title }<br>
	작성자: ${b.writer }<br>
	글내용: <br> 
		<textarea rows="10" cols="80" readonly="readonly">${b.content }</textarea><br>
	등록일: ${b.regdate }<br>
	조회수: ${b.hit }<br>
	파일명: ${b.fname }<br>
	<!--<c:if test="${b.fname != null and b.fname != ''}"> <%-- fname이 null이 아니고 빈칸이 아니면 이미지 출력 --%>
		<img src="image/${b.fname }">
	</c:if>-->
	<hr>
	<a href="insertBoard.do">새 글 작성</a>
	&nbsp;&nbsp;
	<a href="listBoard.do">목록보기</a>
	&nbsp;&nbsp;
	<a href="updateBoard.do?no=${b.no }">수정</a>
	&nbsp;&nbsp;
	<a href="deleteBoard.do?no=${b.no }">삭제</a>
	&nbsp;&nbsp;
	<a href="insertBoard.do?no=${b.no }">답글 작성</a>
	
	
</body>
</html>