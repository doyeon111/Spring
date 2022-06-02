<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시물 수정</h2>
	<hr>
	<form action="updateBoard" method="post" enctype="multipart/form-data">
		<input type="hidden" name="no" value="${b.no }">
		제목: <input type="text" name="title" value="${b.title }"><br><br>
		작성자: ${b.writer } <br><br>
		비밀번호: <input type="password" name="pwd"><br><br>
		글내용 <br>
		<textarea rows="10" cols="80" name="content" placeholder="글을 작성해주세요.">${b.content }</textarea><br><br>
		<input type="hidden" name="fname" value="${b.fname }"><%--원래 파일 이름을 hidden으로 전달 --%>
		첨부파일: ${b.fname }
		<input type="file" name="uploadFile"><br><br>
		<input type="submit" value="수정">
		&nbsp;&nbsp;
		<input type="reset" value="취소"> 
	</form>
</body>
</html>