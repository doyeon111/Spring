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
	제목: ${b.title }<br>
	작성자: ${b.writer }<br>
	글내용: <br>
		<textarea rows="10" cols="80" readonly="readonly">${b.content }</textarea><br>
	작성날짜: ${b.regdate }<br>
	조회수: ${b.hit }<br>
	
	<c:if test="${b.fname != null and b.fname != '' }"> <%--fname이 있다면 --%>
		첨부파일: <a href="upload/${b.fname }">${b.fname }</a><br> <%--업로드한 파일을 다운로드 하기 위해서 태그를 붙여준다. (압축파일 같은 것) --%>
	</c:if>
	
	<c:if test="${img == 'yes' }">  <%-- img 파일(이미지)이 yes일경우 파일을 출력--%>
		<img src="upload/${b.fname }">
	</c:if>
	
	<hr>
	<a href="listBoard">목록보기</a>
	&nbsp;&nbsp;
	<a href="updateBoard?no=${b.no }">수정</a>
	&nbsp;&nbsp;
	<a href="deleteBoard?no=${b.no }">삭제</a>
	&nbsp;&nbsp;
	<a href="insertBoard?no=${b.no }">답글작성</a>
</body>
</html>