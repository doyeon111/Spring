<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkDelete(no) {
		re = confirm("정말로 삭제할까요?");
		if(re == true) {
			location.href = "deleteGoods?no="+no;
		}
	}
</script>
</head>
<body>
	<h2>상품 상세보기</h2>
	<hr>
		상품번호: ${g.no }<br>
		상품이름: ${g.item }<br>
		상품수량: ${g.qty }<br>
		상품가격: ${g.price }<br>
		상품이미지: <br>
		<img src="images/${g.fname }"><br>
		<a href="listGoods">목록보기</a>
		&nbsp;&nbsp;
		<a href="updateGoods?no=${g.no }">수정</a>
		&nbsp;&nbsp;
		<a href="#" onclick="checkDelete(${g.no})">삭제</a>

</body>
</html>