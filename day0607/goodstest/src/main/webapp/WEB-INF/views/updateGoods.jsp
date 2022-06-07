<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>상품 수정</h2>
	<hr>
	<form action="updateGoods" method="post" enctype="multipart/form-data">
		상품번호: ${g.no }
		<input type="hidden" name="no" value="${g.no }"><br><br>
		상품이름: <input type="text" name="item" value="${g.item }"><br><br>
		상품수량: <input type="number" name="qty" value="${g.qty }"><br><br>
		상품가격: <input type="number" name="price" value="${g.price }"><br><br>
		<input type="hidden" name="fname" value="${g.fname }"><%--원래 파일 이름을 hidden으로 전달 --%>
		<img src="images/${g.fname }" width="50" height="50"><br>
		상품 이미지:
		<input type="file" name="uploadFile"><br><br>
		<input type="submit" value="상품수정">
		&nbsp;&nbsp;
		<input type="reset" value="취소">
	</form>
</body>
</html>