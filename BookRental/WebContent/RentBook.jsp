<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 대여</title>
</head>
<body>
	<div style="margin-left: 50px">
		<h3>도서 대여</h3>
		<form action="/BookRental/book-rent" method="post">
			대여할 책 ID를 입력하세요 : <input type="text" name="ID" /> <input
				type="submit" value="저장" />
		</form>
	</div>
</body>
</html>