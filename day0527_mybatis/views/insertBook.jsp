<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>도서 등록</h2>
	<hr>
	<form action="insertBook.do" method="post">
		도서번호: <input type="number" name="bookid"><br>
		도서이름: <input type="text" name="bookname"><br>
		출판사명: <input type="text" name="publisher"><br>
		도서가격: <input type="number" name="price"><br>
		<input type="submit" value="등록">
		&nbsp;&nbsp;
		<input type="reset" value="취소">
	</form>
</body>
</html>