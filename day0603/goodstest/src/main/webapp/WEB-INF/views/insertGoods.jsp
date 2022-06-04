<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>상품 등록</h2>
	<hr>
	<form action="insertGoods" method="post" enctype="multipart/form-data">
		상품번호: <input type="number" name="no"><br><br>
		상품이름: <input type="text" name="item"><br><br>
		상품수량: <input type="number" name="qty"><br><br>
		상품가격: <input type="number" name="price"><br><br>
		상품 이미지: <input type="file" name="uploadFile"><br><br>
		<input type="submit" value="상품등록">
		&nbsp;&nbsp;
		<input type="reset" value="취소">
	</form>
</body>
</html>