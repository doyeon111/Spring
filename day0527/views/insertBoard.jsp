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
	<form action="insertBoard.do" method="post"> <%-- 파일도 함께 업로드 하려면 enctype="multipart/form-data" 사용 --%>
	
		<input type="hidden" name="no" value="${no }">
	
		글 제목: <br>
		<input type="text" name="title"><br><br>
		
		글 작성자: <br>
		<input type="text" name="writer"><br><br>
		
		암호: <br>
		<input type="password" name="pwd"><br><br>
		
		글 내용: <br>
		<textarea rows="10" cols="80" name="content" placeholder="글을 작성해주세요."></textarea><br><br>
		
		파일: <br>
		<!--  <input type="file" name="fname"><br><br><%-- 파일의 글자가 아닌 내용을 받아와야함. --%>-->
		<input type="text" name="fname">
		
		<input type="submit" value="등록">
		&nbsp;&nbsp;
		<input type="reset" value="다시입력">
		
	</form>
</body>
</html>