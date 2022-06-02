<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>${title }</h2>
	<hr>
	<form action="insertBoard" method="post" enctype="multipart/form-data">
		<input type="hidden" name="no" value="${no }">
		제목: <input type="text" name="title"><br><br>
		작성자: <input type="text" name="writer"><br><br>
		비밀번호: <input type="password" name="pwd"><br><br>
		글내용 <br>
		<textarea rows="10" cols="80" name="content" placeholder="글을 작성해주세요."></textarea><br><br>
		파일: <input type="file" name="uploadFile"><br><br>
		<input type="submit" value="등록">
		&nbsp;&nbsp;
		<input type="reset" value="취소"> 
	</form>
</body>
</html>