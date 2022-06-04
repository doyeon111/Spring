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
	<h2>상품 목록</h2>
	<hr>
	<a href="insertGoods">상품 등록</a>
	<table border="1" width="80%"> 
		<tr>
			<th>상품번호</th>
			<th>상품이름</th>
		</tr>
		
		<c:forEach var="g" items="${list }">
			<tr>
				<td>${g.no }</td>
				<td><a href="detailGoods?no=${g.no }">${g.item }</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<hr>
	<!--  
	<c:forEach var="i" begin="1" end="${totalPage }">
		<a href="listGoods?pageNUM=${i }">${i }&nbsp;</a>
	</c:forEach> -->
	
	${pageStr }
</body>
</html>