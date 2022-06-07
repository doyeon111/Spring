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
	
	<form action="listGoods">
		<select name="searchColumn">
		<option value="item">상품명</option>
		<option value="price">가격</option>
		</select> : <input type="search" name="keyword">
		<input type="submit" value="검색">
	</form>
	
	<table border="1" width="80%"> 
		<tr>
			<th><a href="listGoods?sortColumn=no">상품번호</a></th>
			<th><a href="listGoods?sortColumn=item">상품이름</a></th>
			<th><a href="listGoods?sortColumn=price">가격</a></th>
		</tr>
		
		<c:forEach var="g" items="${list }">
			<tr>
				<td>${g.no }</td>
				<td><a href="detailGoods?no=${g.no }">${g.item }</a></td>
				<td>${g.price }</td>
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