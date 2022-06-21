<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인</h2>
	<hr>
	<form action="login" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> <%-- 모든 (시큐리티)폼 태그에는 올바른 요청이 맞는지 반드시 현재 코드가 필요하다.(hidden) --%>
		아이디: <input type="text" name="username"><br> <%--아이디 --%>
		비밀번호: <input type="password" name="password"><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>